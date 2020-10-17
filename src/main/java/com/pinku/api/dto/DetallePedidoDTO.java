package com.pinku.api.dto;

import com.pinku.model.*;

import java.io.Serializable;

public class DetallePedidoDTO implements Serializable {

    private Long id;

    private int cantidad;

    private double total;

    private Pedido pedido;

    private Producto producto;

    private Predisenio predisenio;

    private Talla talla;

    private Color color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Predisenio getPredisenio() {
        return predisenio;
    }

    public void setPredisenio(Predisenio predisenio) {
        this.predisenio = predisenio;
    }

    public Talla getTalla() {
        return talla;
    }

    public void setTalla(Talla talla) {
        this.talla = talla;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
