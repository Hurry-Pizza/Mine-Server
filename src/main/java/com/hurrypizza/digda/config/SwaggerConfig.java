package com.hurrypizza.digda.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.List;

@Configuration
public class SwaggerConfig {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                       .useDefaultResponseMessages(false)
                       .select()
                       .apis(RequestHandlerSelectors.basePackage("com.hurrypizza.digda.api.v1"))
                       .paths(PathSelectors.any())
                       .build()
                       .apiInfo(apiInfo())
                       .securityContexts(List.of(securityContext()))
                       .securitySchemes(List.of(apiKey()));
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                       .title("Digda Swagger")
                       .version("1.0")
                       .build();
    }

    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_HEADER, AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                       .securityReferences(defaultAuth())
                       .build();
    }

    private List<SecurityReference> defaultAuth() {
        var authorizationScope = new AuthorizationScope("global", "accessEverything");
        var authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return List.of(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
    }

}