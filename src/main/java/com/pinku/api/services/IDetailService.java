package com.pinku.api.services;

import com.pinku.model.DetallePedido;
import com.pinku.model.Pedido;

import java.util.List;

public interface IDetailService {

    void SaveItems(List<DetallePedido> items, Pedido pedido);

}
