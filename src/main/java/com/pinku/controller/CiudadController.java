package com.pinku.controller;

import com.pinku.model.Ciudad;
import com.pinku.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ciudades")
public class CiudadController {

    @Autowired
    private CiudadRepository ciudadRepository;

    @GetMapping
    public ResponseEntity<List<Ciudad>> getCiudad(){
        List<Ciudad> ciudad = (List<Ciudad>) ciudadRepository.findAll();
        return ResponseEntity.ok(ciudad);
    }

    @RequestMapping(value = "{ciudadId}")
    public ResponseEntity<Ciudad> getCiudadById(@PathVariable("ciudadId") Ciudad ciudad){
        if(ciudad != null){
            Optional<Ciudad> ciudades = ciudadRepository.findById(ciudad.getId());
            return ResponseEntity.ok(ciudades.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Ciudad> createCiudad(@RequestBody Ciudad ciudad){
        Ciudad newCiudad = ciudadRepository.save(ciudad);
        return ResponseEntity.ok(newCiudad);
    }

    @DeleteMapping(value = "{ciudadId}")
    public ResponseEntity<Void> deleteCiudad(@PathVariable("ciudadId") Long ciudadId){
        ciudadRepository.deleteById(ciudadId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Ciudad> updateCiudad(@RequestBody Ciudad ciudad){
        if(ciudad != null){
            Optional<Ciudad> ciudades = ciudadRepository.findById(ciudad.getId());
            Ciudad updateCiudad = ciudades.get();
            updateCiudad.setId(ciudad.getId());
            updateCiudad.setCiudad(ciudad.getCiudad());
            updateCiudad.setDepartamento(ciudad.getDepartamento());
            updateCiudad.setRegion(ciudad.getRegion());
            ciudadRepository.save(updateCiudad);
            return ResponseEntity.ok(updateCiudad);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
