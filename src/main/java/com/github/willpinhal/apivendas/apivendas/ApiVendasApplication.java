package com.github.willpinhal.apivendas.apivendas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ApiVendasApplication {

	@Autowired
	@Qualifier("applicationName")
	private String nomeAplicao;

	@Value("${application.name}")
	private String nomeAplicacaoByProperties;

	@GetMapping("/hello")
	public String helloWorld(){
		return "Hello World! <br> Bem vindo à aplicação " +  nomeAplicao + "<br><br>" + nomeAplicacaoByProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiVendasApplication.class, args);
	}

}
