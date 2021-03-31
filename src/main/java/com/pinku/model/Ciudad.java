package com.pinku.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pinku_ciudad")
public class Ciudad {

    @Id
    private Long id;

    @NotNull
    private String departamento;

    @NotNull
    private String ciudad;

    @NotNull
    private String region;

    public Ciudad() {

    }

    public Ciudad(Long id, @NotNull String departamento, @NotNull String ciudad, @NotNull String region) {
        this.id = id;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.region = region;
    }

}
