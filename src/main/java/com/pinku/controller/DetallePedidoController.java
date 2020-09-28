package com.pinku.controller;

import com.pinku.model.*;
import com.pinku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/detallepedidos")
public class DetallePedidoController {

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PredisenioRepository predisenioRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private ColorRepository colorRepository;

    @GetMapping
    public ResponseEntity<List<DetallePedido>> getDetallePedido(){
        List<DetallePedido> detallePedidos = detallePedidoRepository.findAll();
        return ResponseEntity.ok(detallePedidos);
    }

    @PostMapping(value = "/{pedidoId}/{productoId}/{predisenioId}/{tallaId}/{colorId}")
    public ResponseEntity<DetallePedido> createDetallePedido(@PathVariable("pedidoId") String pedidoId,
                                                             @PathVariable("productoId") String productoId,
                                                             @PathVariable("predisenioId") String predisenioId,
                                                             @PathVariable("tallaId") Long tallaId,
                                                             @PathVariable("colorId") Long colorId,
                                                             @RequestBody DetallePedido detallePedido){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        Optional<Producto> productoOptional = productoRepository.findById(productoId);
        Optional<Predisenio> predisenioOptional = predisenioRepository.findById(predisenioId);
        Optional<Talla> tallaOptional = tallaRepository.findById(tallaId);
        Optional<Color> colorOptional = colorRepository.findById(colorId);
        Pedido pedido = pedidoOptional.get();
        Producto producto = productoOptional.get();
        Predisenio predisenio = predisenioOptional.get();
        Talla talla = tallaOptional.get();
        Color color = colorOptional.get();
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        detallePedido.setPredisenio(predisenio);
        detallePedido.setTalla(talla);
        detallePedido.setColor(color);
        DetallePedido newDetallePedido = detallePedidoRepository.save(detallePedido);
        // Implementar recalculo para el total del pedido
        return ResponseEntity.ok(newDetallePedido);
    }

    @PostMapping(value = "/{pedidoId}/{productoId}/{predisenioId}")
    public ResponseEntity<DetallePedido> createDetallePedido(@PathVariable("pedidoId") String pedidoId,
                                                             @PathVariable("productoId") String productoId,
                                                             @PathVariable("predisenioId") String predisenioId,
                                                             @RequestBody DetallePedido detallePedido){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        Optional<Producto> productoOptional = productoRepository.findById(productoId);
        Optional<Predisenio> predisenioOptional = predisenioRepository.findById(predisenioId);
        Pedido pedido = pedidoOptional.get();
        Producto producto = productoOptional.get();
        Predisenio predisenio = predisenioOptional.get();
        detallePedido.setPedido(pedido);
        detallePedido.setProducto(producto);
        detallePedido.setPredisenio(predisenio);
        DetallePedido newDetallePedido = detallePedidoRepository.save(detallePedido);
        // Implementar recalculo para el total del pedido
        return ResponseEntity.ok(newDetallePedido);
    }

    @DeleteMapping(value = "{detallePedidoId}")
    public ResponseEntity<Void> deleteDetallePedido(@PathVariable("detallePedidoId") Long detallePedidoId){
        detallePedidoRepository.deleteById(detallePedidoId);
        // Implementar recalculo para el total del pedido
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{detallePedidoId}/{pedidoId}/{productoId}/{predisenioId}/{tallaId}/{colorId}")
    public ResponseEntity<DetallePedido> updateDetallePedido(@PathVariable("detallePedidoId") Long detallePedidoId,
                                                             @PathVariable("pedidoId") String pedidoId,
                                                             @PathVariable("productoId") String productoId,
                                                             @PathVariable("predisenioId") String predisenioId,
                                                             @PathVariable("tallaId") Long tallaId,
                                                             @PathVariable("colorId") Long colorId,
                                                             @RequestBody DetallePedido detallePedido){
        if(detallePedido != null){
            Optional<DetallePedido> detallePedidoOptional = detallePedidoRepository.findById(detallePedidoId);
            Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
            Optional<Producto> productoOptional = productoRepository.findById(productoId);
            Optional<Predisenio> predisenioOptional = predisenioRepository.findById(predisenioId);
            Optional<Talla> tallaOptional = tallaRepository.findById(tallaId);
            Optional<Color> colorOptional = colorRepository.findById(colorId);
            DetallePedido updateDetallePedido = detallePedidoOptional.get();
            Pedido pedido = pedidoOptional.get();
            Producto producto = productoOptional.get();
            Predisenio predisenio = predisenioOptional.get();
            Talla talla = tallaOptional.get();
            Color color = colorOptional.get();
            updateDetallePedido.setPedido(pedido);
            updateDetallePedido.setProducto(producto);
            updateDetallePedido.setPredisenio(predisenio);
            updateDetallePedido.setTalla(talla);
            updateDetallePedido.setColor(color);
            updateDetallePedido.setCantidad(detallePedido.getCantidad());
            updateDetallePedido.setTotal(detallePedido.getTotal());
            detallePedidoRepository.save(updateDetallePedido);
            // Implementar recalculo para el total del pedido
            return ResponseEntity.ok(updateDetallePedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{detallePedidoId}/{pedidoId}/{productoId}/{predisenioId}")
    public ResponseEntity<DetallePedido> updateDetallePedido(@PathVariable("detallePedidoId") Long detallePedidoId,
                                                             @PathVariable("pedidoId") String pedidoId,
                                                             @PathVariable("productoId") String productoId,
                                                             @PathVariable("predisenioId") String predisenioId,
                                                             @RequestBody DetallePedido detallePedido){
        if(detallePedido != null){
            Optional<DetallePedido> detallePedidoOptional = detallePedidoRepository.findById(detallePedidoId);
            Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
            Optional<Producto> productoOptional = productoRepository.findById(productoId);
            Optional<Predisenio> predisenioOptional = predisenioRepository.findById(predisenioId);
            DetallePedido updateDetallePedido = detallePedidoOptional.get();
            Pedido pedido = pedidoOptional.get();
            Producto producto = productoOptional.get();
            Predisenio predisenio = predisenioOptional.get();
            updateDetallePedido.setPedido(pedido);
            updateDetallePedido.setProducto(producto);
            updateDetallePedido.setPredisenio(predisenio);
            updateDetallePedido.setCantidad(detallePedido.getCantidad());
            updateDetallePedido.setTotal(detallePedido.getTotal());
            detallePedidoRepository.save(updateDetallePedido);
            // Implementar recalculo para el total del pedido
            return ResponseEntity.ok(updateDetallePedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{detallePedidoId}/{pedidoId}/{productoId}/{predisenioId}/{tallaId}/{colorId}")
    public ResponseEntity<DetallePedido> partialUpdateDetallePedido(@PathVariable("detallePedidoId") Long detallePedidoId,
                                                                    @PathVariable("pedidoId") String pedidoId,
                                                                    @PathVariable("productoId") String productoId,
                                                                    @PathVariable("predisenioId") String predisenioId,
                                                                    @PathVariable("tallaId") Long tallaId,
                                                                    @PathVariable("colorId") Long colorId,
                                                                    @RequestBody DetallePedido detallePedido){
        Optional<DetallePedido> detallePedidoOptional = detallePedidoRepository.findById(detallePedidoId);
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        Optional<Producto> productoOptional = productoRepository.findById(productoId);
        Optional<Predisenio> predisenioOptional = predisenioRepository.findById(predisenioId);
        Optional<Talla> tallaOptional = tallaRepository.findById(tallaId);
        Optional<Color> colorOptional = colorRepository.findById(colorId);
        Pedido pedido = pedidoOptional.get();
        Producto producto = productoOptional.get();
        Predisenio predisenio = predisenioOptional.get();
        Talla talla = tallaOptional.get();
        Color color = colorOptional.get();
        if(detallePedidoOptional.isPresent()){
            DetallePedido updateDetallePedido = detallePedidoOptional.get();
            if((detallePedido.getCantidad() != updateDetallePedido.getCantidad()) && (detallePedido.getCantidad() != 0)){
                updateDetallePedido.setCantidad(detallePedido.getCantidad());
            }

            if((detallePedido.getTotal() != updateDetallePedido.getTotal()) && (detallePedido.getTotal() != 0.0)){
                updateDetallePedido.setTotal(detallePedido.getTotal());
            }

            if(detallePedido.getProducto() != null){
                updateDetallePedido.setPedido(pedido);
            }

            if(detallePedido.getProducto() != null){
                updateDetallePedido.setProducto(producto);
            }

            if(detallePedido.getPredisenio() != null){
                updateDetallePedido.setPredisenio(predisenio);
            }

            if(detallePedido.getTalla() != null){
                updateDetallePedido.setTalla(talla);
            }

            if(detallePedido.getColor() != null){
                updateDetallePedido.setColor(color);
            }

            // Implementar recalculo para el total del pedido
            detallePedidoRepository.save(updateDetallePedido);
            return ResponseEntity.ok(updateDetallePedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{detallePedidoId}/{pedidoId}/{productoId}/{predisenioId}")
    public ResponseEntity<DetallePedido> partialUpdateDetallePedido(@PathVariable("detallePedidoId") Long detallePedidoId,
                                                                    @PathVariable("pedidoId") String pedidoId,
                                                                    @PathVariable("productoId") String productoId,
                                                                    @PathVariable("predisenioId") String predisenioId,
                                                                    @RequestBody DetallePedido detallePedido){
        Optional<DetallePedido> detallePedidoOptional = detallePedidoRepository.findById(detallePedidoId);
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        Optional<Producto> productoOptional = productoRepository.findById(productoId);
        Optional<Predisenio> predisenioOptional = predisenioRepository.findById(predisenioId);
        Pedido pedido = pedidoOptional.get();
        Producto producto = productoOptional.get();
        Predisenio predisenio = predisenioOptional.get();
        if(detallePedidoOptional.isPresent()){
            DetallePedido updateDetallePedido = detallePedidoOptional.get();
            if((detallePedido.getCantidad() != updateDetallePedido.getCantidad()) && (detallePedido.getCantidad() != 0)){
                updateDetallePedido.setCantidad(detallePedido.getCantidad());
            }

            if((detallePedido.getTotal() != updateDetallePedido.getTotal()) && (detallePedido.getTotal() != 0.0)){
                updateDetallePedido.setTotal(detallePedido.getTotal());
            }

            if(detallePedido.getProducto() != null){
                updateDetallePedido.setPedido(pedido);
            }

            if(detallePedido.getProducto() != null){
                updateDetallePedido.setProducto(producto);
            }

            if(detallePedido.getPredisenio() != null){
                updateDetallePedido.setPredisenio(predisenio);
            }

            // Implementar recalculo para el total del pedido
            detallePedidoRepository.save(updateDetallePedido);
            return ResponseEntity.ok(updateDetallePedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
