package com.pinku.api.dto;

import java.io.Serializable;

public class TallaDTO implements Serializable {

    private Long id;

    private String talla;

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
