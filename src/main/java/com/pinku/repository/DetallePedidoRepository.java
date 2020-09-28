package com.pinku.repository;

import com.pinku.model.DetallePedido;
import com.pinku.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

    @Query("from DetallePedido where pedido = ?1")
    List<DetallePedido> findAllByPedido(Pedido pedido);

}
