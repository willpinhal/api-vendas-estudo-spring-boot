package com.github.willpinhal.apivendas.apivendas;

import com.github.willpinhal.apivendas.apivendas.domain.entity.Cliente;
import com.github.willpinhal.apivendas.apivendas.domain.entity.Pedido;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.ClienteRepository;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ConfiguracaoDesenvolvimento
public class MinhaConfiguration {

    @Bean
    public String applicationName() {
        return "Sistema de Vendas";
    }

    @Bean
    public CommandLineRunner executar(){
        return args -> {System.out.println("Rodando configuração de desenvolvimento");};
    }

    @Bean
    public CommandLineRunner executar(
            @Autowired ClienteRepository clientesRepository,
            @Autowired PedidoRepository pedidoRepository
    ){

        return args -> {

            System.out.println("Salvando clientes");
            clientesRepository.save(new Cliente(null, "Maria Clara"));
            clientesRepository.save((new Cliente(null, "William Lucio de Souza")));
            clientesRepository.save((new Cliente(null, "Lucas")));
            clientesRepository.save((new Cliente(null, "Polyana Q. de Souza")));

            Cliente fulano = new Cliente(null, "Fulano Silva");
            clientesRepository.save(fulano);

            Pedido p = new Pedido();
            p.setCliente(fulano);
            p.setDataPedido(LocalDate.now());
            p.setTotalPedido(BigDecimal.valueOf(100));
            pedidoRepository.save(p);

            //Cliente clienteComPedidos = clientesRepository.findClienteFetchPedidos(fulano.getId());
            //System.out.println(clienteComPedidos.getPedidos());

            List<Pedido> clienteComPedidos = pedidoRepository.findByCliente(fulano);
            System.out.println(clienteComPedidos);
            clienteComPedidos.stream().forEach(System.out::println);


            String nome = "Polyana";

            System.out.println(String.format("Existe cliente com o nome %s?", nome));
            nome = nome + "%";
            if (clientesRepository.existsByNomeLike(nome)){
                System.out.println("Cliente encontrado!");
                System.out.println(clientesRepository.findByNomeLike(nome).get(0).getNome());
            }
            else {
                System.out.println("Nenhum cliente encontrado!");
            }
        };
    }
}
