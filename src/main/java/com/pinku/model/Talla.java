package com.pinku.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pinku_talla")
public class Talla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String talla;

    public Talla() {

    }

    public Talla(Long id, @NotNull String talla) {
        this.id = id;
        this.talla = talla;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTalla() {
        return talla;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }
}
