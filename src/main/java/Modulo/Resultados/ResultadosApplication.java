package Modulo.Resultados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ResultadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResultadosApplication.class, args);
	}

}
