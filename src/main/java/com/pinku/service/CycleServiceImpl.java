package com.pinku.service;

import com.pinku.api.services.ICycleService;
import com.pinku.model.CicloPedido;
import com.pinku.model.EstadoPedido;
import com.pinku.model.Pedido;
import com.pinku.repository.CicloPedidoRepository;
import com.pinku.repository.EstadoPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class CycleServiceImpl implements ICycleService {

    @Autowired
    private CicloPedidoRepository cicloPedidoRepository;

    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;

    @Override
    public void SetCycle(Pedido pedido) {
        Calendar today = Calendar.getInstance();
        EstadoPedido estadoPedidoFirst = estadoPedidoRepository.findAll().stream().findFirst().get();
        CicloPedido newCicloPedido = new CicloPedido();
        newCicloPedido.setFecha(today);
        newCicloPedido.setPedido(pedido);
        newCicloPedido.setEstadoPedido(estadoPedidoFirst);
        cicloPedidoRepository.save(newCicloPedido);
    }

}
