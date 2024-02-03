package Modulo.Resultados.Dtos;

import lombok.*;

@Data
@Getter // genera metodos getter para todos los metodos de la clase
@Setter
@NoArgsConstructor // genera un constructor sin argumentos
@AllArgsConstructor // constructor con todos los parametros
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
