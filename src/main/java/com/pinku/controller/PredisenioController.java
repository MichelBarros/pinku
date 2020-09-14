package com.pinku.controller;

import com.pinku.model.Predisenio;
import com.pinku.exception.ResourceNotFoundException;
import com.pinku.model.Producto;
import com.pinku.repository.PredisenioRepository;
import com.pinku.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/predisenios")
public class PredisenioController {

    @Autowired
    private PredisenioRepository predisenioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public ResponseEntity<List<Predisenio>> getPredisenio(){
        List<Predisenio> predisenios = (List<Predisenio>) predisenioRepository.findAll();
        return ResponseEntity.ok(predisenios);
    }

    @RequestMapping(value = "{predisenioId}")
    public ResponseEntity<Predisenio> getPredisenioById(@PathVariable("predisenioId") Predisenio predisenio){
        if(predisenio != null){
            Optional<Predisenio> predisenios = predisenioRepository.findById(predisenio.getId());
            return ResponseEntity.ok(predisenios.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "{productoId}")
    public ResponseEntity<Predisenio> createPredisenio(@PathVariable("productoId") String productoId,
                                                       @RequestBody Predisenio predisenio) throws ResourceNotFoundException {
        return productoRepository.findById(productoId).map(producto -> {
            predisenio.setProducto(producto);
            Predisenio newPredisenio = predisenioRepository.save(predisenio);
            return ResponseEntity.ok(newPredisenio);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductoId " + productoId + " not found"));
    }

    @DeleteMapping(value = "{predisenioId}")
    public ResponseEntity<Void> deletePredisenio(@PathVariable("predisenioId") String predisenioId){
        predisenioRepository.deleteById(predisenioId);
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "{productoId}")
    public ResponseEntity<Predisenio> updatePredisenio(@PathVariable("productoId") String productoId,
                                                       @RequestBody Predisenio predisenio){
        if((predisenio != null) && productoRepository.existsById(productoId)){
            Optional<Predisenio> predisenioOptional = predisenioRepository.findById(predisenio.getId());
            Optional<Producto> productoOptional = productoRepository.findById(productoId);
            Predisenio updatePredisenio = predisenioOptional.get();
            Producto producto = productoOptional.get();
            updatePredisenio.setRuta(predisenio.getRuta());
            updatePredisenio.setProducto(producto);
            predisenioRepository.save(updatePredisenio);
            return ResponseEntity.ok(updatePredisenio);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
