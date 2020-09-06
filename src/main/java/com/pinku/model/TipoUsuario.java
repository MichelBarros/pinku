package com.pinku.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pinku_tipo_usuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String tipo;

    public TipoUsuario() {

    }

    public TipoUsuario(Long id, @NotNull String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
