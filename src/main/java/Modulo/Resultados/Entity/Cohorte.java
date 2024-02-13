package Modulo.Resultados.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Cohorte")
public class Cohorte {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAsignacionDeCohorte;

    @Column
    private String cohorte;


    @OneToMany(mappedBy = "cohorte")
    List<Estudiante> Cohorte;

    public Cohorte() {
    }



    public String getCohorte() {
        return cohorte;
    }

    public void setCohorte(String cohorte) {
        this.cohorte = cohorte;
    }
}
