package br.com.fiap.revisaoapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "revisaoapi com swagger e openapi", version = "0.0.1", description = "API desenvolvida para revisão da turma 2TDSPM"))
public class RevisaoapiApplication {

    public static void main(String[] args) {
        SpringApplication.run(RevisaoapiApplication.class, args);
    }

}
