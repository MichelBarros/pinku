package com.pinku.service;

import com.pinku.api.services.IDetailService;
import com.pinku.model.DetallePedido;
import com.pinku.model.Pedido;
import com.pinku.repository.DetallePedidoRepository;
import com.pinku.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements IDetailService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Override
    public void SaveItems(List<DetallePedido> items, Pedido pedido) {
        //Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        for(DetallePedido item: items){
            item.setPedido(pedido);
            //detallePedidoRepository.save(item);
        }
        detallePedidoRepository.saveAll(items);
    }

}
