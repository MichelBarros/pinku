package com.pinku.repository;

import com.pinku.model.CicloPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CicloPedidoRepository extends JpaRepository<CicloPedido, Long> {

}
