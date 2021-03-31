package com.pinku.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "pinku_estado_pedido")
public class EstadoPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String descripcion;

    private boolean cierra;

    public EstadoPedido() {

    }

    public EstadoPedido(Long id, @NotNull String descripcion, boolean cierra) {
        this.id = id;
        this.descripcion = descripcion;
        this.cierra = cierra;
    }

}
