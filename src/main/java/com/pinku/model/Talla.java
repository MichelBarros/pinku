package com.pinku.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
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

}
