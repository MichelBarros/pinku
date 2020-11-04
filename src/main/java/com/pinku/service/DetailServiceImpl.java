package com.pinku.service;

import com.pinku.api.dto.ItemInsertDTO;
import com.pinku.api.services.IDetailService;
import com.pinku.model.*;
import com.pinku.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetailServiceImpl implements IDetailService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private PredisenioRepository predisenioRepository;

    @Autowired
    private TallaRepository tallaRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public void SaveItems(List<ItemInsertDTO> items, Pedido pedido) {
        // Crear lista para agregar items y no guardarlos uno a uno
        for(ItemInsertDTO item: items){
            DetallePedido detallePedido = new DetallePedido();
            Optional<Producto> productoOptional = productoRepository.findById(item.getIdProducto());
            Optional<Predisenio> predisenioOptional = predisenioRepository.findById(item.getIdPredisenio());
            detallePedido.setCantidad(item.getCantidad());
            detallePedido.setTotal(item.getTotal());
            detallePedido.setPedido(pedido);
            detallePedido.setProducto(productoOptional.get());
            detallePedido.setPredisenio(predisenioOptional.get());
            if(item.getIdTalla() != null){
                Optional<Talla> tallaOptional = tallaRepository.findById(item.getIdTalla());
                detallePedido.setTalla(tallaOptional.get());
            }
            if(item.getIdColor() != null){
                Optional<Color> colorOptional = colorRepository.findById(item.getIdColor());
                detallePedido.setColor(colorOptional.get());
            }

            detallePedidoRepository.save(detallePedido);
        }

    }

}
