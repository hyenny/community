package org.example.community.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(title = "Community API", version = "v1")
)
@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI() {
    SecurityScheme securityScheme = new SecurityScheme()
        .type(SecurityScheme.Type.HTTP)
        .scheme("bearer")
        .bearerFormat("JWT")
        .in(SecurityScheme.In.HEADER).name("Authorization");

    SecurityRequirement securityRequirement = new SecurityRequirement().addList("bearerAuth");

    return new OpenAPI()
        .components(new Components().addSecuritySchemes("bearerAuth", securityScheme))
        .security(Collections.singletonList(securityRequirement));
  }

}
