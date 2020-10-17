package com.pinku.api.dto;

import com.pinku.model.CicloPedido;
import com.pinku.model.DetallePedido;

import java.io.Serializable;
import java.util.List;

public class PedidoDTO implements Serializable {

    private String id;

    private String direccionEntrega;

    private String nombreDestinatario;

    private String observaciones;

    private double total;

    private List<DetallePedido> items;

    private List<CicloPedido> ciclo;

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

    public List<DetallePedido> getItems() {
        return items;
    }

    public void setItems(List<DetallePedido> items) {
        this.items = items;
    }

    public List<CicloPedido> getCiclo() {
        return ciclo;
    }

    public void setCiclo(List<CicloPedido> ciclo) {
        this.ciclo = ciclo;
    }

}
