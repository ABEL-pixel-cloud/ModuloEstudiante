package Modulo.Resultados.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="aspirante")
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

    public Aspirante() {
    }

    public Aspirante(String tipo_De_Documento, Long documento,
                     String nombresCompletos, Long telefono,
                     String correo, String tipoDePerfil,
                     String estadoDeProceso,
                     Double resultadoPruebaGorilla,
                     String linkDePrueba,
                     Boolean admitido,
                     String financiador,
                     String programa,
                     String observacion) {
        this.tipo_De_Documento = tipo_De_Documento;
        this.documento = documento;
        this.nombresCompletos = nombresCompletos;
        this.telefono = telefono;
        this.correo = correo;
        this.tipoDePerfil = tipoDePerfil;
        this.estadoDeProceso = estadoDeProceso;
        this.resultadoPruebaGorilla = resultadoPruebaGorilla;
        this.linkDePrueba = linkDePrueba;
        this.admitido = admitido;
        this.financiador = financiador;
        this.programa = programa;
        this.observacion = observacion;
    }

    public Long getIdaspirante() {
        return idaspirante;
    }

    public String getTipo_De_Documento() {
        return tipo_De_Documento;
    }

    public Long getDocumento() {
        return documento;
    }

    public String getNombresCompletos() {
        return nombresCompletos;
    }

    public Long getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipoDePerfil() {
        return tipoDePerfil;
    }

    public String getEstadoDeProceso() {
        return estadoDeProceso;
    }

    public Double getResultadoPruebaGorilla() {
        return resultadoPruebaGorilla;
    }

    public String getLinkDePrueba() {
        return linkDePrueba;
    }

    public Boolean getAdmitido() {
        return admitido;
    }

    public String getFinanciador() {
        return financiador;
    }

    public String getPrograma() {
        return programa;
    }

    public String getObservacion() {
        return observacion;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    @OneToOne(mappedBy = "aspirante",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Estudiante estudiante;

    @OneToMany(mappedBy = "aspirante",cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    List<Documentacion> documentacion;




}
