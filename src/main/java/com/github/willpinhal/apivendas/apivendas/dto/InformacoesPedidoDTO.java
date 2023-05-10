package com.github.willpinhal.apivendas.apivendas.dto;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Pedido;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Builder
public class InformacoesPedidoDTO {

    private Integer codigo;

    private String nomeCliente;

    private String cpf;

    private BigDecimal total;

    private String status;

    private String dataPedido;

    private List<InformacoesItemPedidoDTO> items;

    public static InformacoesPedidoDTO convertePedidosEmDto(Pedido pedido){

        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotalPedido())
                .status(pedido.getStatus().name())
                .items(InformacoesItemPedidoDTO.converteListItensPedidosEmDTOs(pedido.getItems()))
                .build();
    }
}
