package Modulo.Resultados.Dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DocumentosDto {
    private Long id;
    private String nombreacta;
    private String urlacta;
    private String tipoDocumentoacta;
    private Long tamanoacta;
    private String nombredocumentocedula;
    private String urldocumentocedula;
    private String tipoDocumentocedula;
    private Long tamanodocumentocedula;
    private String nombreAspirante;
    private Long cedulaAspirante;



}
