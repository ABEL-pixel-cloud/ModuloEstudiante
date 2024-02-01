package Modulo.Resultados.Entity;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.List;

@Entity
@Table(name = "Cohorte")
@Builder
public class Cohorte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsignacionDeCohorte;

    @Column
    private String cohorte;

    @Column
    private String fechaDeAsignacion;

    @OneToMany(mappedBy = "cohorte")
    List<Estudiante> Cohorte;

    public Long getIdAsignacionDeCohorte() {
        return idAsignacionDeCohorte;
    }

    public String getCohorte() {
        return cohorte;
    }
}
