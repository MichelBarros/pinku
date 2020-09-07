package com.pinku.controller;

import com.pinku.model.TipoUsuario;
import com.pinku.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipousuarios")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @GetMapping
    public ResponseEntity<List<TipoUsuario>> getTipoUsuario(){
        List<TipoUsuario> tipoUsuarios = (List<TipoUsuario>) tipoUsuarioRepository.findAll();
        return ResponseEntity.ok(tipoUsuarios);
    }

    @RequestMapping(value = "{tipousuarios}")
    public ResponseEntity<TipoUsuario> getTipoUsuarioById(@PathVariable("tipousuarios") TipoUsuario tipoUsuario){
        if(tipoUsuario != null){
            Optional<TipoUsuario> tipoUsuarios = tipoUsuarioRepository.findById(tipoUsuario.getId());
            return ResponseEntity.ok(tipoUsuarios.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<TipoUsuario> createTipoUsuario(@RequestBody TipoUsuario tipoUsuario){
        TipoUsuario newTipoUsuario = tipoUsuarioRepository.save(tipoUsuario);
        return ResponseEntity.ok(newTipoUsuario);
    }

}
