package com.pinku.api.dto;

import java.io.Serializable;

public class EstadoPedidoDTO implements Serializable {

    private Long id;

    private String descripcion;

    private boolean cierra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCierra() {
        return cierra;
    }

    public void setCierra(boolean cierra) {
        this.cierra = cierra;
    }

}
