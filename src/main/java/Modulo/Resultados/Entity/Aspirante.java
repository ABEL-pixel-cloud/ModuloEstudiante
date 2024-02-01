package Modulo.Resultados.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="aspirante")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Aspirante  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idaspirante;

    @Column(length = 100)
    private String tipo_De_Documento;

    @Column
    private Long documento;

    @Column(length = 100)
    private String nombresCompletos;

    @Column
    private Long telefono;

    @Column
    private String correo;

    @Column(length = 100)
    private String tipoDePerfil;

    @Column(length = 100)
    private String estadoDeProceso;

    @Column(length = 100)
    private Double resultadoPruebaGorilla;

    @Column(length = 100)
    private String linkDePrueba;

    @Column
    private Boolean admitido;

    @Column(length = 100)
    private String financiador;

    @Column(length = 100)
    private String programa;

    @Column(length = 100)
    private String observacion;




    @OneToOne(mappedBy = "aspirante",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Estudiante estudiante;

    @OneToMany(mappedBy = "aspirante",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    List<Documentacion> documentacion;




}
