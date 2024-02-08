package Modulo.Resultados.Configuration;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info=@Info(
                title = "Modulo Resultado De Aspirantes "
        ),
        servers = {

               /* @Server(url ="https://evaluacion-y-perfilamiento.up.railway.app", description = "Servidor de producción"),

                */
                @Server(url ="http://localhost:8080/", description = "Servidor local")
        }
)

public class SwaggerConfig {
}
