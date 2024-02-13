package Modulo.Resultados.Dtos;




import Modulo.Resultados.Entity.Aspirante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;
/*
@Getter // genera metodos getter para todos los metodos de la clase
@Setter
@NoArgsConstructor // genera un constructor sin argumentos
@AllArgsConstructor // constructor con todos los parametros
public class ReciveDocumentosDto {


    private List<Aspirante> idAspirante;

    private Boolean estado;







}

 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReciveDocumentosDto {

    private Long idAspirante;
    private Boolean estado;

}
