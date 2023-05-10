package com.github.willpinhal.apivendas.apivendas.controllers;

import com.github.willpinhal.apivendas.apivendas.Services.PedidoService;
import com.github.willpinhal.apivendas.apivendas.domain.entities.Pedido;
import com.github.willpinhal.apivendas.apivendas.domain.enums.StatusPedido;
import com.github.willpinhal.apivendas.apivendas.dto.AtualizacaoStatusPedidoDTO;
import com.github.willpinhal.apivendas.apivendas.dto.InformacoesPedidoDTO;
import com.github.willpinhal.apivendas.apivendas.dto.PedidoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoService.salvar(pedidoDTO);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable int id){
        return pedidoService
                .obterPedidoCompleto(id)
                .map(p -> InformacoesPedidoDTO.convertePedidosEmDto(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado."));
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable int id, @RequestBody AtualizacaoStatusPedidoDTO statusPedidoDTO){
        pedidoService.atualizaStatus(id, StatusPedido.valueOf(statusPedidoDTO.getNovoStatus()));
    }
}
