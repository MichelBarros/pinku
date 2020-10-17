package com.pinku.controller;

import com.pinku.model.Ciudad;
import com.pinku.model.TipoUsuario;
import com.pinku.model.Usuario;
import com.pinku.repository.CiudadRepository;
import com.pinku.repository.TipoUsuarioRepository;
import com.pinku.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuario(){
        List<Usuario> usuario = (List<Usuario>) usuarioRepository.findAll();
        return ResponseEntity.ok(usuario);
    }

    @RequestMapping(value = "{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("usuarioId") Usuario usuario){
        if(usuario != null){
            Optional<Usuario> usuarios = usuarioRepository.findById(usuario.getId());
            return ResponseEntity.ok(usuarios.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("/{tipoUsuarioId}/{ciudadId}")
    public ResponseEntity<Usuario> createUsuario(@PathVariable("tipoUsuarioId") Long tipoUsuarioId,
                                                 @PathVariable("ciudadId") Long ciudadId,
                                                 @RequestBody Usuario usuario){
        Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(tipoUsuarioId);
        Optional<Ciudad> ciudadOptional = ciudadRepository.findById(ciudadId);
        TipoUsuario tipoUsuario = tipoUsuarioOptional.get();
        Ciudad ciudad = ciudadOptional.get();
        usuario.setTipoUsuario(tipoUsuario);
        usuario.setCiudad(ciudad);
        Usuario newUsuario = usuarioRepository.save(usuario);
        return ResponseEntity.ok(newUsuario);
    }

    @DeleteMapping(value = "{usuarioId}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioRepository.deleteById(usuarioId);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{tipoUsuarioId}/{ciudadId}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable("tipoUsuarioId") Long tipoUsuarioId,
                                                  @PathVariable("ciudadId") Long ciudadId,
                                                  @RequestBody Usuario usuario){
        if(usuario != null){
            Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(tipoUsuarioId);
            Optional<Ciudad> ciudadOptional = ciudadRepository.findById(ciudadId);
            Optional<Usuario> usuarios = usuarioRepository.findById(usuario.getId());
            TipoUsuario tipoUsuario = tipoUsuarioOptional.get();
            Ciudad ciudad = ciudadOptional.get();
            Usuario updateUsuario = usuarios.get();
            updateUsuario.setId(usuario.getId());
            updateUsuario.setPrimerNombre(usuario.getPrimerNombre());
            updateUsuario.setOtrosNombres(usuario.getOtrosNombres());
            updateUsuario.setApellidos(usuario.getApellidos());
            updateUsuario.setCelular(usuario.getCelular());
            updateUsuario.setEmail(usuario.getEmail());
            updateUsuario.setClave(usuario.getClave());
            updateUsuario.setDireccion(usuario.getDireccion());
            updateUsuario.setFechaNacimiento(usuario.getFechaNacimiento());
            updateUsuario.setTipoUsuario(tipoUsuario);
            updateUsuario.setCiudad(ciudad);
            usuarioRepository.save(updateUsuario);
            return ResponseEntity.ok(updateUsuario);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "{usuarioId}/{tipoUsuarioId}/{ciudadId}")
    public ResponseEntity<Usuario> partialUpdateUsuario(@PathVariable("tipoUsuarioId") Long tipoUsuarioId,
                                                          @PathVariable("ciudadId") Long ciudadId,
                                                           @PathVariable("usuarioId") Long usuarioId,
                                                          @RequestBody Usuario usuario){
        Optional<TipoUsuario> tipoUsuarioOptional = tipoUsuarioRepository.findById(tipoUsuarioId);
        Optional<Ciudad> ciudadOptional = ciudadRepository.findById(ciudadId);
        Optional<Usuario> usuarios = usuarioRepository.findById(usuarioId);
        TipoUsuario tipoUsuario = tipoUsuarioOptional.get();
        Ciudad ciudad = ciudadOptional.get();
        if(usuarios.isPresent()){
            Usuario partialUpdateUsuario = usuarios.get();
            if(usuario.getId() != null){
                partialUpdateUsuario.setId(usuario.getId());
            }

            if(usuario.getPrimerNombre() != null){
                partialUpdateUsuario.setPrimerNombre(usuario.getPrimerNombre());
            }

            if(usuario.getOtrosNombres() != null){
                partialUpdateUsuario.setOtrosNombres(usuario.getOtrosNombres());
            }

            if(usuario.getApellidos() != null){
                partialUpdateUsuario.setApellidos(usuario.getApellidos());
            }

            if((usuario.getCelular() != null) && (usuario.getCelular() != 0)){
                partialUpdateUsuario.setCelular(usuario.getCelular());
            }

            if(usuario.getEmail() != null){
                partialUpdateUsuario.setEmail(usuario.getEmail());
            }

            if(usuario.getClave() != null){
                partialUpdateUsuario.setClave(usuario.getClave());
            }

            if(usuario.getDireccion() != null){
                partialUpdateUsuario.setDireccion(usuario.getDireccion());
            }

            if(usuario.getFechaNacimiento() != null){
                partialUpdateUsuario.setFechaNacimiento(usuario.getFechaNacimiento());
            }

            if(usuario.getTipoUsuario() != null){
                partialUpdateUsuario.setTipoUsuario(tipoUsuario);
            }

            if(usuario.getCiudad() != null){
                partialUpdateUsuario.setCiudad(ciudad);
            }

            usuarioRepository.save(partialUpdateUsuario);
            return ResponseEntity.ok(partialUpdateUsuario);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
