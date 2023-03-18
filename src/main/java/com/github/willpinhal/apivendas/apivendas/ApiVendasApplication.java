package com.github.willpinhal.apivendas.apivendas;

import com.github.willpinhal.apivendas.apivendas.domain.repositories.ClienteRepository;
import com.github.willpinhal.apivendas.apivendas.model.Cliente;
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
			clientesRepository.salvar(new Cliente(null, "Maria Clara"));
			clientesRepository.salvar(new Cliente(null, "William Lucio de Souza"));
			clientesRepository.salvar(new Cliente(null, "Lucas"));
			clientesRepository.salvar(new Cliente(null, "Polyana"));

			System.out.println("Obtendo todos clientes");
			List<Cliente> clienteList =clientesRepository.obterTodos();
			clienteList.forEach(System.out::println);

			System.out.println("Atualizando todos clientes");
			clienteList.forEach(c -> {
				c.setNome(c.getNome() + " - atualizado");
				clientesRepository.atualizar(c);
			});

			clienteList.forEach(System.out::println);

			System.out.println("Obetendo clientes por filtro");
			clientesRepository.buscarPorNome("Lu").forEach(System.out::println);

			System.out.println("Deletando clientes");
			clientesRepository.obterTodos().forEach(c -> clientesRepository.deletar(c));

			System.out.println("Obtendo todos clientes");
			clienteList = clientesRepository.obterTodos();

			if (clienteList.size() == 0){
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
