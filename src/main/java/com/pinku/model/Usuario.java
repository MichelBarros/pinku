package com.pinku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@Entity
@Table(name = "pinku_usuario")
public class Usuario {

    @Id
    private Long id;

    @NotNull
    private String primerNombre;

    private String otrosNombres;

    @NotNull
    private String apellidos;

    @NotNull
    private Long celular;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String clave;

    @NotNull
    private String direccion;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nacimiento")
    private Calendar fechaNacimiento;

    @ManyToOne
    @JoinColumn(name = "id_tipo_usuario", nullable = true)
    @JsonIgnore
    private TipoUsuario tipoUsuario;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", nullable = true)
    @JsonIgnore
    private Ciudad ciudad;

    public Usuario() {

    }

    public Usuario(Long id, @NotNull String primerNombre, String otrosNombres, String apellidos,
                   @NotNull Long celular, @NotNull @Email String email, @NotNull String clave,
                   @NotNull String direccion, @NotNull Calendar fechaNacimiento,
                   TipoUsuario tipoUsuario, Ciudad ciudad) {
        this.id = id;
        this.primerNombre = primerNombre;
        this.otrosNombres = otrosNombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.email = email;
        this.clave = clave;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoUsuario = tipoUsuario;
        this.ciudad = ciudad;
    }

}
