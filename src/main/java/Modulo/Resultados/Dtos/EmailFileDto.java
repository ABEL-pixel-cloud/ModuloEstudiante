package Modulo.Resultados.Dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmailFileDto {


    @Schema(description = "Destinatarios del correo", example = "[\"user1@example.com\"]")
    private String[] toUser;

    @Schema(description = "Asunto del correo", example = "Reporte de resultados")
    private String subject;

    @Schema(description = "Cuerpo del correo", example = "Adjunto encontrarás el reporte solicitado.")
    private String message;

    @Schema(description = "Archivo adjunto", format = "binary")
    private MultipartFile file;




}
