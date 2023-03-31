package com.github.willpinhal.apivendas.apivendas;

import com.github.willpinhal.apivendas.apivendas.domain.entity.Cliente;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.ClienteRepository;
import com.github.willpinhal.apivendas.apivendas.model.ClienteModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ApiVendasApplication {

//	@Autowired
//	@Qualifier("applicationName")
//	private String nomeAplicao;

	@Value("${application.name}")
	private String nomeAplicacaoByProperties;

	@Autowired
	ClienteRepository clientesRepository;

	@Gato
	private Animal animal;

	@Bean(name = "executarAnimal")
	public CommandLineRunner executar(){
		return args -> {
			this.animal.fazerBarulho();

			System.out.println("Salvando clientes");
			clientesRepository.save(new Cliente(null, "Maria Clara"));
			clientesRepository.save((new Cliente(null, "William Lucio de Souza")));
			clientesRepository.save((new Cliente(null, "Lucas")));
			clientesRepository.save((new Cliente(null, "Polyana")));

			String nome = "Polyana";

			System.out.println(String.format("Existe cliente com o nome %s?", nome));
			if (clientesRepository.existsByNome(nome)){
				System.out.println("Cliente encontrado!");
			}
			else {
				System.out.println("Nenhum cliente encontrado!");
			}
		};
	}

	@GetMapping("/hello")
	public String helloWorld(){
		return "Hello World! <br> Bem vindo à aplicação " +  nomeAplicacaoByProperties;
	}

	public static void main(String[] args) {
		SpringApplication.run(ApiVendasApplication.class, args);
	}

}
