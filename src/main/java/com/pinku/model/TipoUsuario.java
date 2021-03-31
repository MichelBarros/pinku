package com.pinku.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
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

}
