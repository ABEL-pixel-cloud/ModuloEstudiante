package Modulo.Resultados.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Cohorte")
@Data
@NoArgsConstructor
@AllArgsConstructor
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


}
