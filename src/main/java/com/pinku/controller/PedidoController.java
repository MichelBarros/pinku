package com.pinku.controller;

import com.pinku.model.Ciudad;
import com.pinku.model.Pedido;
import com.pinku.model.Usuario;
import com.pinku.repository.CiudadRepository;
import com.pinku.repository.PedidoRepository;
import com.pinku.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public ResponseEntity<List<Pedido>> getPedido(){
        List<Pedido> pedidos = (List<Pedido>) pedidoRepository.findAll();
        return ResponseEntity.ok(pedidos);
    }

    @RequestMapping(value = "{pedidoId}")
    public ResponseEntity<Pedido> getPedidoById(@PathVariable("pedidoId") Pedido pedido){
        if(pedido != null){
            Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedido.getId());
            return ResponseEntity.ok(pedidoOptional.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/{usuarioId}/{ciudadId}")
    public ResponseEntity<Pedido> createPedido(@PathVariable("usuarioId") Long usuarioId,
                                               @PathVariable("ciudadId") Long ciudadId,
                                               @RequestBody Pedido pedido){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        Optional<Ciudad> ciudadOptional = ciudadRepository.findById(ciudadId);
        Usuario usuario = usuarioOptional.get();
        Ciudad ciudad = ciudadOptional.get();
        pedido.setUsuario(usuario);
        pedido.setCiudad(ciudad);
        Pedido newPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok(newPedido);
    }

    @PostMapping(value = "/{usuarioId}")
    public ResponseEntity<Pedido> createPedido(@PathVariable("usuarioId") Long usuarioId,
                                               @RequestBody Pedido pedido){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        Usuario usuario = usuarioOptional.get();
        pedido.setUsuario(usuario);
        pedido.setDireccionEntrega(usuario.getDireccion());
        pedido.setNombreDestinatario(usuario.getPrimerNombre() + " " + usuario.getApellidos());
        pedido.setCiudad(usuario.getCiudad());
        Pedido newPedido = pedidoRepository.save(pedido);
        return ResponseEntity.ok(newPedido);
    }

    @DeleteMapping(value = "{pedidoId}")
    public ResponseEntity<Void> deletePedido(@PathVariable("pedidoId") String pedidoId){
        pedidoRepository.deleteById(pedidoId);
        return ResponseEntity.ok(null);
    }

    @PutMapping("{pedidoId}/{usuarioId}/{ciudadId}")
    public ResponseEntity<Pedido> updatePedido(@PathVariable("pedidoId") String pedidoId,
                                               @PathVariable("usuarioId") Long usuarioId,
                                               @PathVariable("ciudadId") Long ciudadId,
                                               @RequestBody Pedido pedido){
        if(pedido != null){
            Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
            Optional<Ciudad> ciudadOptional = ciudadRepository.findById(ciudadId);
            Optional<Pedido> pedidos = pedidoRepository.findById(pedidoId);
            Usuario usuario = usuarioOptional.get();
            Ciudad ciudad = ciudadOptional.get();
            Pedido updatePedido = pedidos.get();
            updatePedido.setDireccionEntrega(pedido.getDireccionEntrega());
            updatePedido.setNombreDestinatario(pedido.getNombreDestinatario());
            updatePedido.setObservaciones(pedido.getObservaciones());
            updatePedido.setTotal(pedido.getTotal());
            updatePedido.setUsuario(usuario);
            updatePedido.setCiudad(ciudad);
            pedidoRepository.save(updatePedido);
            return ResponseEntity.ok(updatePedido);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("{pedidoId}/{usuarioId}/{ciudadId}")
    public ResponseEntity<Pedido> partialUpdatePedido(@PathVariable("usuarioId") Long usuarioId,
                                                      @PathVariable("ciudadId") Long ciudadId,
                                                      @PathVariable("pedidoId") String pedidoId,
                                                      @RequestBody Pedido pedido){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        Optional<Ciudad> ciudadOptional = ciudadRepository.findById(ciudadId);
        Optional<Pedido> pedidos = pedidoRepository.findById(pedidoId);
        Usuario usuario = usuarioOptional.get();
        Ciudad ciudad = ciudadOptional.get();
        if(pedidos.isPresent()){
            Pedido partialUpdatePedido = pedidos.get();
            if(pedido.getDireccionEntrega() != null){
                partialUpdatePedido.setDireccionEntrega(pedido.getDireccionEntrega());
            }

            if(pedido.getNombreDestinatario() != null){
                partialUpdatePedido.setNombreDestinatario(pedido.getNombreDestinatario());
            }

            if(pedido.getObservaciones() != null){
                partialUpdatePedido.setObservaciones(pedido.getObservaciones());
            }

            if(pedido.getTotal() != partialUpdatePedido.getTotal()){
                partialUpdatePedido.setTotal(pedido.getTotal());
            }

            if(pedido.getUsuario() != null){
                partialUpdatePedido.setUsuario(usuario);
            }

            if(pedido.getCiudad() != null){
                partialUpdatePedido.setCiudad(ciudad);
            }

            pedidoRepository.save(partialUpdatePedido);
            return ResponseEntity.ok(partialUpdatePedido);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PatchMapping("{pedidoId}")
    public ResponseEntity<Pedido> partialUpdatePedido(@PathVariable("pedidoId") String pedidoId,
                                                      @RequestBody Pedido pedido){
        Optional<Pedido> pedidos = pedidoRepository.findById(pedidoId);
        if(pedidos.isPresent()){
            Pedido partialUpdatePedido = pedidos.get();
            if(pedido.getDireccionEntrega() != null){
                partialUpdatePedido.setDireccionEntrega(pedido.getDireccionEntrega());
            }

            if(pedido.getNombreDestinatario() != null){
                partialUpdatePedido.setNombreDestinatario(pedido.getNombreDestinatario());
            }

            if(pedido.getObservaciones() != null){
                partialUpdatePedido.setObservaciones(pedido.getObservaciones());
            }

            if(pedido.getTotal() != partialUpdatePedido.getTotal()){
                partialUpdatePedido.setTotal(pedido.getTotal());
            }

            pedidoRepository.save(partialUpdatePedido);
            return ResponseEntity.ok(partialUpdatePedido);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

}
