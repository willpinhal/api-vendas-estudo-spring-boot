package com.github.willpinhal.apivendas.apivendas.domain.repositories;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Cliente;
import com.github.willpinhal.apivendas.apivendas.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
