package com.github.willpinhal.apivendas.apivendas.Services;

import com.github.willpinhal.apivendas.apivendas.domain.entities.Usuario;
import com.github.willpinhal.apivendas.apivendas.domain.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario salvar(Usuario usuario){

        String senhaCriptografada = passwordEncoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);

        return usuarioRepository.save(usuario);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

//        if (userName.equals("cicrano") == false){
//            throw new UsernameNotFoundException("Usuário não encontrado na base.");
//        }
//
//        return User
//                .builder()
//                .username("cicrano")
//                .password(passwordEncoder.encode("123"))
//                .roles("USER", "ADMIN")
//                .build();

        Usuario usuario = usuarioRepository.findByLogin(userName).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado na base de dados."));

        String[] roles = usuario.isAdmin() ? new String[]{"ADMIN", "USER"} : new String[] {"USER"};

        return User
                .builder()
                .username(usuario.getLogin())
                .password(usuario.getSenha())
                .roles(roles)
                .build();
    }
}
