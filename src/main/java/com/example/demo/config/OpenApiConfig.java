package com.example.demo.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server; // Import the Server model
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        // Define the server URL (local or production)
        Server localServer = new Server();
        localServer.setUrl("http://localhost:8080");
        localServer.setDescription("Local Development Server");

        final String securitySchemeName = "bearerAuth";
        
        return new OpenAPI()
                .info(new Info()
                        .title("Real Estate Rating Engine API")
                        .version("1.0")
                        .description("API for managing properties, scores, and ratings."))
                .servers(List.of(localServer)) // Add the server link here
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}