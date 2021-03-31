package com.pinku.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pinku_color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String codigo;

    @Column(name = "nombre_color", unique = true)
    private String nombreColor;

    public Color() {

    }

    public Color(Long id, @NotNull String codigo, String nombreColor) {
        this.id = id;
        this.codigo = codigo;
        this.nombreColor = nombreColor;
    }

}
