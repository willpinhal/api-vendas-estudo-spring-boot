package com.github.willpinhal.apivendas.apivendas;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Cliente;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiVendasApplication extends SpringBootServletInitializer {

//	@Bean
//	public CommandLineRunner commandLineRunner(@Autowired ClienteRepository clienteRepository){
//		return args ->  {
//			Cliente c = new Cliente(null, "Fulano da Silva");
//			clienteRepository.save(c);
//		};
//	}

	public static void main(String[] args) {
		SpringApplication.run(ApiVendasApplication.class, args);
	}
}
