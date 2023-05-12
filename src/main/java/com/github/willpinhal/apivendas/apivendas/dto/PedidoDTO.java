package com.github.willpinhal.apivendas.apivendas.dto;

import com.github.willpinhal.apivendas.apivendas.controllers.validations.NotEmptyList;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    private String status;

    @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDTO> itens;
}
