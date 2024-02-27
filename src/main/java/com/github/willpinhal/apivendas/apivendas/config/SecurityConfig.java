package com.github.willpinhal.apivendas.apivendas.config;

import com.github.willpinhal.apivendas.apivendas.Services.UsuarioServiceImpl;
import com.github.willpinhal.apivendas.apivendas.security.jwt.JwtAuthFilter;
import com.github.willpinhal.apivendas.apivendas.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
// Classe usada para configurar o SpringSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private JwtService jwtService;


    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtService, usuarioService);
    }

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
    // Método que realiza a autenticação e insere dentro do SpringSecurity
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
    // Método que configura as autorizações
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/clientes/**").hasRole("USER")
                .antMatchers("/produtos/**").hasRole("ADMIN")
                .antMatchers("/pedidos/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/usuarios/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

        http.headers().frameOptions().disable();
    }

    public void configure(WebSecurity security) throws Exception {
        security.ignoring()
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configutario/security",
                        "/swagger-ui.html",
                        "/webjars/**");
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
