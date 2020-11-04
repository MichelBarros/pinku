package com.pinku.api.dto;

import com.pinku.model.Pedido;

import java.io.Serializable;

public class ItemInsertDTO implements Serializable {

    private int cantidad;

    private double total;

    private Pedido pedido;

    private String idProducto;

    private String idPredisenio;

    private Long idTalla;

    private Long idColor;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getIdPredisenio() {
        return idPredisenio;
    }

    public void setIdPredisenio(String idPredisenio) {
        this.idPredisenio = idPredisenio;
    }

    public Long getIdTalla() {
        return idTalla;
    }

    public void setIdTalla(Long idTalla) {
        this.idTalla = idTalla;
    }

    public Long getIdColor() {
        return idColor;
    }

    public void setIdColor(Long idColor) {
        this.idColor = idColor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
