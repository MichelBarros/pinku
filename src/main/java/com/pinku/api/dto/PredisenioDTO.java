package com.pinku.api.dto;

import com.pinku.model.Producto;

import java.io.Serializable;

public class PredisenioDTO implements Serializable {

    private String id;

    private String ruta;

    private Producto producto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

}
