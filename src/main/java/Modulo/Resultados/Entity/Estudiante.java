package Modulo.Resultados.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estudiante")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEstudiante;
    @Column
    private String nombre;
    @Column
    private String cedula;
    @Column
    private String programa;


    public Estudiante(long l, String s) {
    }


    @ManyToOne
    Cohorte cohorte;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAspirante")
    Aspirante aspirante;



}
