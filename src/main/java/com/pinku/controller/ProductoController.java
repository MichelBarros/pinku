package com.pinku.controller;

import com.pinku.model.Producto;
import com.pinku.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<Producto>> getProducto(){
        List<Producto> productos = (List<Producto>) productoRepository.findAll();
        return ResponseEntity.ok(productos);
    }

    @RequestMapping(value = "{productoId}")
    public ResponseEntity<Producto> getProductoById(@PathVariable("productoId") Producto producto){
        if(producto != null){
            Optional<Producto> productos = productoRepository.findById(producto.getId());
            return ResponseEntity.ok(productos.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    //Implementar paginacion y filtros

    @PostMapping
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto){
        Producto newProducto = productoRepository.save(producto);
        return ResponseEntity.ok(newProducto);
    }

    @DeleteMapping(value = "{productoId}")
    public ResponseEntity<Void> deleteProducto(@PathVariable("productoId") String productoId){
        productoRepository.deleteById(productoId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto){
        if(producto != null){
            Optional<Producto> productos = productoRepository.findById(producto.getId());
            Producto updateProducto = productos.get();
            updateProducto.setTipo(producto.getTipo());
            updateProducto.setTalla(producto.getTalla());
            updateProducto.setColor(producto.getColor());
            updateProducto.setPrecio(producto.getPrecio());
            productoRepository.save(updateProducto);
            return ResponseEntity.ok(updateProducto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "{productoId}")
    public ResponseEntity<Producto> partialUpdateProducto(@PathVariable("productoId") String productoId, @RequestBody Producto producto){
        Optional<Producto> productos = productoRepository.findById(productoId);
        if(productos.isPresent()){
            Producto partialUpdateProducto = productos.get();
            if(producto.getTipo() != null){
                partialUpdateProducto.setTipo(producto.getTipo());
            }

            if(producto.getTalla() != null){
                partialUpdateProducto.setTalla(producto.getTalla());
            }

            if(producto.getColor() != null){
                partialUpdateProducto.setColor(producto.getColor());
            }

            if(producto.getPrecio() != partialUpdateProducto.getPrecio()){
                partialUpdateProducto.setPrecio(producto.getPrecio());
            }

            productoRepository.save(partialUpdateProducto);
            return ResponseEntity.ok(partialUpdateProducto);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
