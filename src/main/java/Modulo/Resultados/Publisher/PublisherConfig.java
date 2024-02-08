package Modulo.Resultados.Publisher;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfig {
    @Value("${resultados.rabbit.queue.crearAspirante}")
    private String name;


    @Bean
    public Queue emailAspirante(){

        return new Queue(this.name);

    }
}
