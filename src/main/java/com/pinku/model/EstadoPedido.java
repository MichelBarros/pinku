package com.pinku.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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
