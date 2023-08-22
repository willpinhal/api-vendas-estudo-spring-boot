package com.github.willpinhal.apivendas.apivendas.dto;

import com.github.willpinhal.apivendas.apivendas.controllers.validations.NotEmptyList;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoDTO {

    @NotEmpty(message = "Informe o código do cliente.")
    private Integer cliente;

    @NotEmpty(message = "Informe o total do pedido.")
    private BigDecimal total;

    @NotEmpty(message = "Informe o status do pedido.")
    private String status;

    @NotEmptyList(message = "Pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDTO> itens;
}
