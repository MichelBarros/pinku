package com.pinku.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pinku_color")
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String codigo;

    @Column(name = "nombre_color")
    private String nombreColor;

    public Color() {

    }

    public Color(Long id, @NotNull String codigo, String nombreColor) {
        this.id = id;
        this.codigo = codigo;
        this.nombreColor = nombreColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreColor() {
        return nombreColor;
    }

    public void setNombreColor(String nombreColor) {
        this.nombreColor = nombreColor;
    }
}
