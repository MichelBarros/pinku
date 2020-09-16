package com.pinku.controller;

import com.pinku.model.Talla;
import com.pinku.repository.TallaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tallas")
public class TallaController {

    @Autowired
    private TallaRepository tallaRepository;

    @GetMapping
    public ResponseEntity<List<Talla>> getTalla(){
        List<Talla> entity = (List<Talla>) tallaRepository.findAll();
        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "{tallaId}")
    public ResponseEntity<Talla> getTallaById(@PathVariable("tallaId") Talla talla){
        if(talla != null){
            Optional<Talla> tallas = tallaRepository.findById(talla.getId());
            return ResponseEntity.ok(tallas.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Talla> createTalla(@RequestBody Talla talla){
        Talla newTalla = tallaRepository.save(talla);
        return ResponseEntity.ok(newTalla);
    }

    @DeleteMapping(value = "{tallaId}")
    public ResponseEntity<Void> deleteTalla(@PathVariable("tallaId") Long tallaId){
        tallaRepository.deleteById(tallaId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Talla> updateProducto(@RequestBody Talla talla){
        if(talla != null){
            Optional<Talla> tallas = tallaRepository.findById(talla.getId());
            Talla updateTalla = tallas.get();
            updateTalla.setTalla(talla.getTalla());
            tallaRepository.save(updateTalla);
            return ResponseEntity.ok(updateTalla);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
