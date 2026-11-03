package br.unipar.seguranca.config;

import java.util.List;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    OpenApiCustomizer bearerAuthentication() {
        return openApi -> {
            openApi.addSecurityItem(new SecurityRequirement().addList("bearerAuth"));
            openApi.getPaths().get("/auth/login").getPost().setSecurity(List.of());
            openApi.getPaths().get("/api/publico").getGet().setSecurity(List.of());
        };
    }
}
