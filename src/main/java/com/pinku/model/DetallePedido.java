package com.pinku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pinku_detalle_pedido")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int cantidad;

    @NotNull
    private double total;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_predisenio", nullable = false)
    private Predisenio predisenio;

    @ManyToOne
    @JoinColumn(name = "id_talla", nullable = true)
    private Talla talla;

    @ManyToOne
    @JoinColumn(name = "id_color", nullable = true)
    private Color color;

    public DetallePedido() {

    }

    public DetallePedido(Long id, @NotNull int cantidad, @NotNull double total, Pedido pedido,
                         Producto producto, Predisenio predisenio, Talla talla, Color color) {
        this.id = id;
        this.cantidad = cantidad;
        this.total = total;
        this.pedido = pedido;
        this.producto = producto;
        this.predisenio = predisenio;
        this.talla = talla;
        this.color = color;
    }

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
