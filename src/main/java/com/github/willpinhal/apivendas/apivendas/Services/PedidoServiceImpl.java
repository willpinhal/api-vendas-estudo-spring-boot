package com.github.willpinhal.apivendas.apivendas.Services;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Cliente;
import com.github.willpinhal.apivendas.apivendas.domain.entities.ItemPedido;
import com.github.willpinhal.apivendas.apivendas.domain.entities.Pedido;
import com.github.willpinhal.apivendas.apivendas.domain.entities.Produto;
import com.github.willpinhal.apivendas.apivendas.domain.enums.StatusPedido;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.ClienteRepository;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.ItemPedidoRepository;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.PedidoRepository;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.ProdutoRepository;
import com.github.willpinhal.apivendas.apivendas.dto.ItemPedidoDTO;
import com.github.willpinhal.apivendas.apivendas.dto.PedidoDTO;
import com.github.willpinhal.apivendas.apivendas.exceptions.PedidoNaoEncontradoException;
import com.github.willpinhal.apivendas.apivendas.exceptions.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;

    private final ClienteRepository clienteRepository;

    private final ProdutoRepository produtoRepository;

    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO pedidoDTO) {

        Integer idCliente = pedidoDTO.getCliente();

        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setStatus(StatusPedido.REALIZADO);
        pedido.setTotalPedido(pedidoDTO.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itemPedidoList = converterItemsPedidos(pedido, pedidoDTO.getItens());

        pedidoRepository.save(pedido);
        itemPedidoRepository.saveAll(itemPedidoList);

        pedido.setItems(itemPedidoList);

        return pedido;
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(int id) {
        return pedidoRepository.findById(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(int id, StatusPedido statusPedido) {

        pedidoRepository.findById(id).map(p ->
        {
            p.setStatus(statusPedido);
            return pedidoRepository.save(p);
        }).orElseThrow(() ->new PedidoNaoEncontradoException());

    }

    private List<ItemPedido> converterItemsPedidos(Pedido pedido, List<ItemPedidoDTO> pedidoDTOList){

        if (pedidoDTOList.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem itens.");
        }

        return pedidoDTOList.stream().map(dto ->
        {
            Integer idProduto = dto.getProduto();
            Produto produto = produtoRepository.findById(idProduto).orElseThrow(
                    () -> new RegraNegocioException("Código de produto inválido: " + idProduto)
            );

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());

    }
}
