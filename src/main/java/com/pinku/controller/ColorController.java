package com.pinku.controller;

import com.pinku.model.Color;
import com.pinku.repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/colores")
public class ColorController {

    @Autowired
    private ColorRepository colorRepository;

    @GetMapping
    public ResponseEntity<List<Color>> getColor(){
        List<Color> color = (List<Color>) colorRepository.findAll();
        return ResponseEntity.ok(color);
    }

    @RequestMapping(value = "{colorId}")
    public ResponseEntity<Color> getColorById(@PathVariable("colorId") Color color){
        if(color != null){
            Optional<Color> colores = colorRepository.findById(color.getId());
            return ResponseEntity.ok(colores.get());
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<Color> createColor(@RequestBody Color color){
        Color newColor = colorRepository.save(color);
        return ResponseEntity.ok(newColor);
    }

    @DeleteMapping(value = "{colorId}")
    public ResponseEntity<Void> deleteColor(@PathVariable("colorId") Long colorId){
        colorRepository.deleteById(colorId);
        return ResponseEntity.ok(null);
    }

    @PutMapping
    public ResponseEntity<Color> updateProducto(@RequestBody Color color){
        if(color != null){
            Optional<Color> colores = colorRepository.findById(color.getId());
            Color updateColor = colores.get();
            updateColor.setCodigo(color.getCodigo());
            updateColor.setNombreColor(color.getNombreColor());
            colorRepository.save(updateColor);
            return ResponseEntity.ok(updateColor);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
