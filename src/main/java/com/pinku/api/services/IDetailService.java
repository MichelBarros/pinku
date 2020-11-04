package com.pinku.api.services;

import com.pinku.api.dto.ItemInsertDTO;
import com.pinku.model.Pedido;

import java.util.List;

public interface IDetailService {

    void SaveItems(List<ItemInsertDTO> items, Pedido pedido);

}
