package com.github.willpinhal.apivendas.apivendas;

import com.github.willpinhal.apivendas.apivendas.Services.UsuarioServiceImpl;
import com.github.willpinhal.apivendas.apivendas.domain.entities.Usuario;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.UsuarioRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApiVendasApplicationTests {

	@Autowired
	private UsuarioServiceImpl usuarioService;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testService(){

		Usuario usuario = new Usuario();
		usuario.setLogin("user01");
		usuario.setSenha("temp123");
		usuario.setAdmin(false);

		usuarioService.salvar(usuario);

		Assert.assertNotNull(usuarioRepository.findByLogin(usuario.getLogin()).stream().findFirst());
	}
}
