package com.pinku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pinku.generator.StringPrefixedSequenceIdProductGenerator;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pinku_predisenio")
public class Predisenio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "predesign_seq")
    @GenericGenerator(name = "predesign_seq",
            strategy = "com.pinku.generator.StringPrefixedSequenceIdProductGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdProductGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdProductGenerator.VALUE_PREFIX_PARAMETER, value = "Dis-Pinku-"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdProductGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d")
            })
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    @NotNull
    private String ruta;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = true)
    @JsonIgnore
    private Producto producto;

    public Predisenio() {

    }

    public Predisenio(String id, @NotNull String ruta, Producto producto) {
        this.id = id;
        this.ruta = ruta;
        this.producto = producto;
    }

}
