package com.pinku.api.dto;

import com.pinku.model.EstadoPedido;
import com.pinku.model.Pedido;

import java.io.Serializable;
import java.util.Calendar;

public class CicloPedidoDTO implements Serializable {

    private Long id;

    private Calendar fecha;

    private Pedido pedido;

    private EstadoPedido estadoPedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getFecha() {
        return fecha;
    }

    public void setFecha(Calendar fecha) {
        this.fecha = fecha;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

}
