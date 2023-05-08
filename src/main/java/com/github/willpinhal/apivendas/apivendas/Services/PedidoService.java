package com.github.willpinhal.apivendas.apivendas.Services;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Pedido;
import com.github.willpinhal.apivendas.apivendas.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO pedidoDTO);
}
