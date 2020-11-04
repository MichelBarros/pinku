package com.pinku.api.dto;

import com.pinku.model.CicloPedido;
import com.pinku.model.DetallePedido;

import java.io.Serializable;
import java.util.List;

public class PedidoInsertDTO implements Serializable {

    private String direccionEntrega;

    private String nombreDestinatario;

    private String observaciones;

    private double total;

    private List<ItemInsertDTO> items;


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

    public List<ItemInsertDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemInsertDTO> items) {
        this.items = items;
    }

}
