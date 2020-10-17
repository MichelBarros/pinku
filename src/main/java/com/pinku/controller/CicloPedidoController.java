package com.pinku.controller;

import com.pinku.model.CicloPedido;
import com.pinku.model.EstadoPedido;
import com.pinku.model.Pedido;
import com.pinku.repository.CicloPedidoRepository;
import com.pinku.repository.EstadoPedidoRepository;
import com.pinku.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ciclopedidos")
public class CicloPedidoController {

    @Autowired
    private CicloPedidoRepository cicloPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;

    @GetMapping
    public ResponseEntity<List<CicloPedido>> getCicloPedido(){
        List<CicloPedido> cicloPedidos = (List<CicloPedido>) cicloPedidoRepository.findAll();
        return ResponseEntity.ok(cicloPedidos);
    }

    @RequestMapping(value = "{ciclopedidoId}")
    public ResponseEntity<CicloPedido> getCicloPedidoById(@PathVariable("ciclopedidoId") CicloPedido cicloPedido){
        if(cicloPedido != null){
            Optional<CicloPedido> cicloPedidoOptional = cicloPedidoRepository.findById(cicloPedido.getId());
            return ResponseEntity.ok(cicloPedidoOptional.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/{pedidoId}/{estadoPedidoId}")
    public ResponseEntity<CicloPedido> createCicloPedido(@PathVariable("pedidoId") String pedidoId,
                                                         @PathVariable("estadoPedidoId") Long estadoPedidoId,
                                                         @RequestBody CicloPedido cicloPedido){
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        Optional<EstadoPedido> estadoPedidoOptional = estadoPedidoRepository.findById(estadoPedidoId);
        Pedido pedido = pedidoOptional.get();
        EstadoPedido estadoPedido = estadoPedidoOptional.get();
        cicloPedido.setPedido(pedido);
        cicloPedido.setEstadoPedido(estadoPedido);
        CicloPedido newCicloPedido = cicloPedidoRepository.save(cicloPedido);
        return ResponseEntity.ok(newCicloPedido);
    }

    @DeleteMapping(value = "{ciclopedidoId}")
    public ResponseEntity<Void> deleteCicloPedido(@PathVariable("ciclopedidoId") Long ciclopedidoId){
        cicloPedidoRepository.deleteById(ciclopedidoId);
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "/{ciclopedidoId}/{pedidoId}/{estadoPedidoId}")
    public ResponseEntity<CicloPedido> updateCicloPedido(@PathVariable("ciclopedidoId") Long ciclopedidoId,
                                                         @PathVariable("pedidoId") String pedidoId,
                                                         @PathVariable("estadoPedidoId") Long estadoPedidoId,
                                                         @RequestBody CicloPedido cicloPedido){
        if(cicloPedido != null && pedidoRepository.existsById(pedidoId)){
            Optional<CicloPedido> cicloPedidoOptional = cicloPedidoRepository.findById(ciclopedidoId);
            Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
            Optional<EstadoPedido> estadoPedidoOptional = estadoPedidoRepository.findById(estadoPedidoId);
            CicloPedido updateCicloPedido = cicloPedidoOptional.get();
            Pedido pedido = pedidoOptional.get();
            EstadoPedido estadoPedido = estadoPedidoOptional.get();
            updateCicloPedido.setFecha(cicloPedido.getFecha());
            updateCicloPedido.setPedido(pedido);
            updateCicloPedido.setEstadoPedido(estadoPedido);
            cicloPedidoRepository.save(updateCicloPedido);
            return ResponseEntity.ok(updateCicloPedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
