package com.pinku.repository;

import com.pinku.model.CicloPedido;
import com.pinku.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CicloPedidoRepository extends JpaRepository<CicloPedido, Long> {

    @Query("from CicloPedido where pedido = ?1")
    List<CicloPedido> findAllByPedido(Pedido pedido);

}
