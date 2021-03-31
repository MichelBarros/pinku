package com.pinku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
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

}
