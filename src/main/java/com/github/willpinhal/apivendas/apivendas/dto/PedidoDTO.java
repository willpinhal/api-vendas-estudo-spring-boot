package com.github.willpinhal.apivendas.apivendas.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class PedidoDTO {

    private Integer cliente;
    private BigDecimal total;
    private String status;
    private List<ItemPedidoDTO> itens;
}
