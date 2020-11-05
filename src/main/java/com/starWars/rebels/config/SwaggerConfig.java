package com.starWars.rebels.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.starWars.rebels.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(apiKey()));

    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Rebel Service API",
                "API de apoyo a los rebeldes, contra la lucha del imperio",
                "1.0",
                "https://opensource.org/ToS",
                new Contact("Gabrieleitor", "https://github.com/Gabrieleitor", "josegabriel_ang_79@hotmail.com"),
                "License of API",
                "https://opensource.org/licenses/CDDL-1.0",
                Collections.emptyList());
        return apiInfo;
    }



    private ApiKey apiKey() {
        return new ApiKey("Bearer", "Authorization", "header");
    }

}

