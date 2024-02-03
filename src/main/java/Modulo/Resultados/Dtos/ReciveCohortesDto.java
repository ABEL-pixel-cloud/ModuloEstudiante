package Modulo.Resultados.Dtos;




import Modulo.Resultados.Entity.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter // genera metodos getter para todos los metodos de la clase
@Setter
@NoArgsConstructor // genera un constructor sin argumentos
@AllArgsConstructor // constructor con todos los parametros
public class ReciveCohortesDto {
    private List<Estudiante> idEstudiante;

    private String cohorte;

    public List<Estudiante> getIdEstudiante() {
        return idEstudiante;
    }

    public String getCohorte() {
        return cohorte;
    }
}
