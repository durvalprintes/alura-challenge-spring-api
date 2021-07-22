package br.com.alura.challenge.spring.api.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.Contact;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.alura.challenge.spring.api.rest"))
                .paths(PathSelectors.any()).build().apiInfo(info());
    }

    private ApiInfo info() {
        return new ApiInfoBuilder().title("Alura Challenge #2 - Back-End").description("API REST com Spring Boot")
                .version("0.0.1").contact(new Contact("Durval Printes",
                        "https://www.linkedin.com/in/durval-printes-51880359/", "durvalprintes@gmail.com"))
                .build();
    }
}
