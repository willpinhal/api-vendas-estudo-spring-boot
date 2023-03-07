package com.github.willpinhal.apivendas.apivendas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

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
}
