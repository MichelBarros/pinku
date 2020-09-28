package com.pinku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pinku.generator.StringPrefixedSequenceIdProductGenerator;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "pinku_pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    @GenericGenerator(name = "order_seq",
            strategy = "com.pinku.generator.StringPrefixedSequenceIdProductGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdProductGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdProductGenerator.VALUE_PREFIX_PARAMETER, value = "Pe-Pinku-"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdProductGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d")
            })
    @Column(name = "id", updatable = false, nullable = false)
    private String id;

    private String direccionEntrega;

    @Column(name = "nombre_destinatario")
    private String nombreDestinatario;

    private String observaciones;

    @NotNull
    private double total;

    @ManyToOne
    @JoinColumn(name = "id_ciudad", nullable = true)
    @JsonIgnore
    private Ciudad ciudad;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = true)
    @JsonIgnore
    private Usuario usuario;

    @Transient
    private List<DetallePedido> items;

    public Pedido() {

    }

    public Pedido(String id, String direccionEntrega, String nombreDestinatario, String observaciones,
                  @NotNull double total, Ciudad ciudad, Usuario usuario) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.nombreDestinatario = nombreDestinatario;
        this.observaciones = observaciones;
        this.total = total;
        this.ciudad = ciudad;
        this.usuario = usuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getNombreDestinatario() {
        return nombreDestinatario;
    }

    public void setNombreDestinatario(String nombreDestinatario) {
        this.nombreDestinatario = nombreDestinatario;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<DetallePedido> getItems() {
        return items;
    }

    public void setItems(List<DetallePedido> items) {
        this.items = items;
    }
}
