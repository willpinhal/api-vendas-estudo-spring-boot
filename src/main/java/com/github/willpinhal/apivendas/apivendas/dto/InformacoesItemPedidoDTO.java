package com.github.willpinhal.apivendas.apivendas.dto;

import com.github.willpinhal.apivendas.apivendas.domain.entities.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class InformacoesItemPedidoDTO {

    private String descricaoProduto;

    private BigDecimal precoUnitario;

    private Integer quantidade;

    public static List<InformacoesItemPedidoDTO> converteListItensPedidosEmDTOs(List<ItemPedido> itemPedidoList){
        if(CollectionUtils.isEmpty(itemPedidoList)){
            return Collections.emptyList();
        }

        return itemPedidoList.stream().map(item ->
                InformacoesItemPedidoDTO.builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
        ).collect(Collectors.toList());
    }

}
