package org.example.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http){
        http.authorizeExchange(exchages -> {
            exchages.pathMatchers("eazybank/accounts/**").authenticated()
                    .pathMatchers("eazybank/loans/**").authenticated()
                    .pathMatchers("eazybank/cards/**").authenticated();
        })
                .oauth2ResourceServer(oAuth2ResourceServerSpec -> oAuth2ResourceServerSpec.jwt());
        http.csrf(csrfSpec -> csrfSpec.disable());

        return http.build();

    }
}
