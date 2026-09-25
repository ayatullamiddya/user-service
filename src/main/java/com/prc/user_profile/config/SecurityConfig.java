package com.prc.user_profile.config;


import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.connection.R2dbcTransactionManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
       return http.csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange->
                        exchange.pathMatchers( "/swagger-ui.html",
                                        "/swagger-ui/**",
                                        "/v3/api-docs/**",
                                        "/webjars/**", "/v3/api-docs/**","/v1/studentapi/students","/v1/studentapi/signup").permitAll()
                                .anyExchange().authenticated())
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .build();


    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
