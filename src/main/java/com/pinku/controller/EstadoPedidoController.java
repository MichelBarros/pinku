package com.pinku.controller;

import com.pinku.model.EstadoPedido;
import com.pinku.repository.EstadoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estadopedidos")
public class EstadoPedidoController {

    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;

    @GetMapping
    public ResponseEntity<List<EstadoPedido>> getEstadoPedido(){
        List<EstadoPedido> estadoPedido = (List<EstadoPedido>) estadoPedidoRepository.findAll();
        return ResponseEntity.ok(estadoPedido);
    }

    @RequestMapping(value = "{estadoPedidoId}")
    public ResponseEntity<EstadoPedido> getEstadoPedidoById(@PathVariable("estadoPedidoId") EstadoPedido estadoPedido){
        if(estadoPedido != null){
            Optional<EstadoPedido> estadoPedidos = estadoPedidoRepository.findById(estadoPedido.getId());
            return ResponseEntity.ok(estadoPedidos.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<EstadoPedido> createEstadoPedido(@RequestBody EstadoPedido estadoPedido){
        EstadoPedido newEstadoPedido = estadoPedidoRepository.save(estadoPedido);
        return ResponseEntity.ok(newEstadoPedido);
    }

    @DeleteMapping(value = "{estadoPedidoId}")
    public ResponseEntity<Void> deleteEstadoPedido(@PathVariable("estadoPedidoId") Long estadoPedidoId){
        estadoPedidoRepository.deleteById(estadoPedidoId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<EstadoPedido> updateEstadoPedido(@RequestBody EstadoPedido estadoPedido){
        if(estadoPedido != null){
            Optional<EstadoPedido> estadoPedidos = estadoPedidoRepository.findById(estadoPedido.getId());
            EstadoPedido updateEstadoPedido = estadoPedidos.get();
            updateEstadoPedido.setDescripcion(estadoPedido.getDescripcion());
            updateEstadoPedido.setCierra(estadoPedido.isCierra());
            estadoPedidoRepository.save(updateEstadoPedido);
            return ResponseEntity.ok(updateEstadoPedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "{estadoPedidoId}")
    public ResponseEntity<EstadoPedido> partialUpdateEstadoPedido(@PathVariable("estadoPedidoId") Long estadoPedidoId, @RequestBody EstadoPedido estadoPedido){
        Optional<EstadoPedido> estadoPedidos = estadoPedidoRepository.findById(estadoPedidoId);
        if(estadoPedidos.isPresent()){
            EstadoPedido partialUpdateEstadoPedidos = estadoPedidos.get();
            if(estadoPedido.getDescripcion() != null){
                partialUpdateEstadoPedidos.setDescripcion(estadoPedido.getDescripcion());
            }

            if(estadoPedido.isCierra() != partialUpdateEstadoPedidos.isCierra()){
                partialUpdateEstadoPedidos.setCierra(estadoPedido.isCierra());
            }

            estadoPedidoRepository.save(partialUpdateEstadoPedidos);
            return ResponseEntity.ok(partialUpdateEstadoPedidos);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
