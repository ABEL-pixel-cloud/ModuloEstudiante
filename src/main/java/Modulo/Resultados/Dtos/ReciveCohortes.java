package Modulo.Resultados.Dtos;




import Modulo.Resultados.Entity.Estudiante;

import java.util.List;

public class ReciveCohortes {
    private List<Estudiante> idEstudiante;

    private String cohorte;

    public List<Estudiante> getIdEstudiante() {
        return idEstudiante;
    }

    public String getCohorte() {
        return cohorte;
    }
}
