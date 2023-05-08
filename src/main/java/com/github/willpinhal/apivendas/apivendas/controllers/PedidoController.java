package com.github.willpinhal.apivendas.apivendas.controllers;

import com.github.willpinhal.apivendas.apivendas.Services.PedidoService;
import com.github.willpinhal.apivendas.apivendas.domain.entities.Pedido;
import com.github.willpinhal.apivendas.apivendas.dto.PedidoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
}
