package com.github.willpinhal.apivendas.apivendas;

import com.github.willpinhal.apivendas.apivendas.domain.entity.Cliente;
import com.github.willpinhal.apivendas.apivendas.domain.entity.Pedido;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.ClienteRepository;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootApplication
@RestController
public class ApiVendasApplication {

	@Value("${application.name}")
	private String nomeAplicacaoByProperties;

	@Autowired
	ClienteRepository clientesRepository;

	@GetMapping("/hello")
	public String helloWorld(){
		return "Hello World! <br> Bem vindo à aplicação " +  nomeAplicacaoByProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiVendasApplication.class, args);
	}

}
