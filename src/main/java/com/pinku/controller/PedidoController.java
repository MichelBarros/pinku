package com.pinku.controller;

import com.pinku.api.dto.PedidoDTO;
import com.pinku.api.dto.PedidoInsertDTO;
import com.pinku.api.services.IDetailService;
import com.pinku.model.Ciudad;
import com.pinku.model.DetallePedido;
import com.pinku.model.Pedido;
import com.pinku.model.Usuario;
import com.pinku.repository.*;
import com.pinku.service.CycleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private CicloPedidoRepository cicloPedidoRepository;

    @Autowired
    private CycleServiceImpl cycleService;

    @Autowired
    private IDetailService iDetailService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> getPedido(){
        List<Pedido> pedidos = (List<Pedido>) pedidoRepository.findAll();
        List<PedidoDTO> pedidosDTO =  new ArrayList<PedidoDTO>();
        for(Pedido p:pedidos){
            PedidoDTO pedidoDTO = new PedidoDTO();
            pedidoDTO.setId(p.getId());
            pedidoDTO.setDireccionEntrega(p.getDireccionEntrega());
            pedidoDTO.setNombreDestinatario(p.getNombreDestinatario());
            pedidoDTO.setObservaciones(p.getObservaciones());
            pedidoDTO.setTotal(p.getTotal());
            pedidoDTO.setItems(detallePedidoRepository.findAllByPedido(p));
            pedidoDTO.setCiclo(cicloPedidoRepository.findAllByPedido(p));
            pedidosDTO.add(pedidoDTO);
        }
        return ResponseEntity.ok(pedidosDTO);
    }

    @RequestMapping(value = "{pedidoId}")
    public ResponseEntity<PedidoDTO> getPedidoById(@PathVariable("pedidoId") Pedido pedido){
        if(pedido != null){
            Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedido.getId());
            PedidoDTO pedidoDTO = new PedidoDTO();

            pedidoDTO.setId(pedidoOptional.get().getId());
            pedidoDTO.setDireccionEntrega(pedidoOptional.get().getDireccionEntrega());
            pedidoDTO.setNombreDestinatario(pedidoOptional.get().getNombreDestinatario());
            pedidoDTO.setObservaciones(pedidoOptional.get().getObservaciones());
            pedidoDTO.setTotal(pedidoOptional.get().getTotal());
            pedidoDTO.setItems(detallePedidoRepository.findAllByPedido(pedidoOptional.get()));
            pedidoDTO.setCiclo(cicloPedidoRepository.findAllByPedido(pedidoOptional.get()));
            return ResponseEntity.ok(pedidoDTO);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping(value = "/{usuarioId}/{ciudadId}")
    public ResponseEntity<Pedido> createPedido(@PathVariable("usuarioId") Long usuarioId,
                                               @PathVariable("ciudadId") Long ciudadId,
                                               @RequestBody @Valid PedidoInsertDTO pedidoInsertDTO){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        Optional<Ciudad> ciudadOptional = ciudadRepository.findById(ciudadId);
        Usuario usuario = usuarioOptional.get();
        Ciudad ciudad = ciudadOptional.get();
        Pedido pedido = new Pedido();
        pedido.setDireccionEntrega(pedidoInsertDTO.getDireccionEntrega());
        pedido.setNombreDestinatario(pedidoInsertDTO.getNombreDestinatario());
        pedido.setObservaciones(pedidoInsertDTO.getObservaciones());
        pedido.setTotal(pedidoInsertDTO.getTotal());
        pedido.setUsuario(usuario);
        pedido.setCiudad(ciudad);
        Pedido newPedido = pedidoRepository.save(pedido);
        iDetailService.SaveItems(pedidoInsertDTO.getItems(), newPedido);
        cycleService.SetCycle(newPedido);
        return ResponseEntity.ok(newPedido);
    }

    @PostMapping(value = "/{usuarioId}")
    public ResponseEntity<Pedido> createPedido(@PathVariable("usuarioId") Long usuarioId,
                                               @RequestBody PedidoInsertDTO pedidoInsertDTO){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioId);
        Usuario usuario = usuarioOptional.get();
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setTotal(pedidoInsertDTO.getTotal());
        pedido.setDireccionEntrega(usuario.getDireccion());
        pedido.setNombreDestinatario(usuario.getPrimerNombre() + " " + usuario.getApellidos());
        pedido.setCiudad(usuario.getCiudad());
        Pedido newPedido = pedidoRepository.save(pedido);
        iDetailService.SaveItems(pedidoInsertDTO.getItems(), newPedido);
        cycleService.SetCycle(newPedido);
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

            if((pedido.getTotal() != partialUpdatePedido.getTotal()) && (pedido.getTotal() != 0.0)){
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
