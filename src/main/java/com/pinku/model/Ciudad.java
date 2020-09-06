package com.pinku.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pinku_ciudad")
public class Ciudad {

    @Id
    private Long id;

    @NotNull
    private String departamento;

    @NotNull
    private String ciudad;

    public Ciudad() {

    }

    public Ciudad(Long id, @NotNull String departamento, @NotNull String ciudad) {
        this.id = id;
        this.departamento = departamento;
        this.ciudad = ciudad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
}
