package com.github.willpinhal.apivendas.apivendas.Services;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Pedido;
import com.github.willpinhal.apivendas.apivendas.dto.InformacoesPedidoDTO;
import com.github.willpinhal.apivendas.apivendas.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);

    Optional<Pedido> obterPedidoCompleto(int idPedido);
}
