package com.pinku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getOtrosNombres() {
        return otrosNombres;
    }

    public void setOtrosNombres(String otrosNombres) {
        this.otrosNombres = otrosNombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Calendar getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

}
