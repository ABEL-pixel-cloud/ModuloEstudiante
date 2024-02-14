package Modulo.Resultados.Configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Configuration
@OpenAPIDefinition(
        info=@Info(
                title = "Modulo Estudiante ", description = ""
        ),
        servers = {

                @Server(url ="https://moduloestudiante-production.up.railway.app", description = "Servidor de producción"),


                @Server(url ="http://localhost:8080/", description = "Servidor local")
        }
)

public class SwaggerConfig {
        @Bean
        public Docket api() {
                return new Docket(DocumentationType.SWAGGER_2)
                        .select()
                        .apis(RequestHandlerSelectors.any())
                        .paths(PathSelectors.any())
                        .build();
        }

}