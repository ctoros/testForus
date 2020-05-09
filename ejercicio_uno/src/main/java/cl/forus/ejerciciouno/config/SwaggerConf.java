package cl.forus.ejerciciouno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConf {

    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage(
                        "cl.forus.ejerciciouno.controller"))
                .build().useDefaultResponseMessages(false).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Ejercicio_UNO ",
                "Ejercicio_uno backend api rest service product.",
                "1.0",
                "Terms of service",
                new Contact("Forus", "http://localhost:8181/api/v1/", "forus@forus.com"),
                "License of API", "API license URL", Collections.emptyList());
    }
}