package com.github.willpinhal.apivendas.apivendas.controllers.validations.config;

import com.github.willpinhal.apivendas.apivendas.Services.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Bean
    public PasswordEncoder passwordEncoder() {

        //        PasswordEncoder encoder = new PasswordEncoder() {
//
//            // Método para codificar a senha do usuário e retornar a senha codificada
//            @Override
//            public String encode(CharSequence charSequence) {
//                return null;
//            }
//
//            // Método para decodificar a senha.
//            // Recebe dois parâmetros, o primeiro é senha do usuário e o segundo é a senha criptografada.
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return false;
//            }
//        }

        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder())
//                .withUser("fulano")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER","ADMIN");
        //super.configure(auth);

        auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/clientes/**").hasRole("USER")
                .antMatchers("/produtos/**").hasRole("ADMIN")
                .antMatchers("/pedidos/**").hasRole("USER")
                .antMatchers(HttpMethod.POST,"/usuarios/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        http.headers().frameOptions().disable();
    }

//    @Bean
//    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
//
//        http.csrf(csrf -> csrf.disable());
//
//        http.authorizeHttpRequests(authz -> authz
//                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
//                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
//                        .anyRequest().authenticated())
//                .addFilter(new AuthenticationFilter(authenticationManager()))
//                .addFilter(new AuthorizationFilter(authenticationManager()))
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//
//        http.headers().frameOptions().disable();
//
//        return http.build();
//    }
}
