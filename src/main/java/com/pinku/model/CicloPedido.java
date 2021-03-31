package com.pinku.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;

@Data
@Entity
@Table(name = "pinku_ciclo_pedido")
public class CicloPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar fecha;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    @JsonIgnore
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_estado_pedido", nullable = false)
    private EstadoPedido estadoPedido;

    public CicloPedido() {

    }

    public CicloPedido(Long id, @NotNull Calendar fecha, Pedido pedido, EstadoPedido estadoPedido) {
        this.id = id;
        this.fecha = fecha;
        this.pedido = pedido;
        this.estadoPedido = estadoPedido;
    }

}
