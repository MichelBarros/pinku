package com.pinku.model;

import com.pinku.generator.StringPrefixedSequenceIdProductGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pinku_producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @GenericGenerator(name = "product_seq",
            strategy = "com.pinku.generator.StringPrefixedSequenceIdProductGenerator",
            parameters = {
                @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdProductGenerator.INCREMENT_PARAM, value = "1"),
                @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdProductGenerator.VALUE_PREFIX_PARAMETER, value = "Pro-Pinku-"),
                @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdProductGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d")
    })
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotNull
    private String tipo;

    @NotNull
    private double precio;

    public Producto() {

    }

    public Producto(String id, @NotNull String tipo, @NotNull double precio) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
    }

}
