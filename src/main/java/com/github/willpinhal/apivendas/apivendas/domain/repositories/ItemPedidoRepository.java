package com.github.willpinhal.apivendas.apivendas.domain.repositories;

import com.github.willpinhal.apivendas.apivendas.domain.entities.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
}