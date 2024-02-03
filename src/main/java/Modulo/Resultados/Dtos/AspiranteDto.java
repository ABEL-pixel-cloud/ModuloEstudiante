package Modulo.Resultados.Dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter // genera metodos getter para todos los metodos de la clase
@Setter
@AllArgsConstructor // constructor con todos los parametros
public class AspiranteDto {
    private Long idAspirante;
    private String tipo_De_Documento;
    private Long documento;
    private String nombres_Completos;
    private Long telefono;
    private String correo;
    private String tipo_De_Perfil;
    private String estado_De_Proceso;
    private Double resultado_Prueba_Gorilla;
    private String link_De_Prueba;
    private Boolean admitido;
    private String financiador;
    private String programa;
    private String observacion;

    public AspiranteDto() {
    }

    public AspiranteDto(String tipo_De_Documento, Long documento, String nombres_Completos,
                        Long telefono, String correo, String tipo_De_Perfil, String estado_De_Proceso,
                        Double resultado_Prueba_Gorilla, String link_De_Prueba, Boolean admitido,
                        String financiador, String programa, String observacion) {
        this.tipo_De_Documento = tipo_De_Documento;
        this.documento = documento;
        this.nombres_Completos = nombres_Completos;
        this.telefono = telefono;
        this.correo = correo;
        this.tipo_De_Perfil = tipo_De_Perfil;
        this.estado_De_Proceso = estado_De_Proceso;
        this.resultado_Prueba_Gorilla = resultado_Prueba_Gorilla;
        this.link_De_Prueba = link_De_Prueba;
        this.admitido = admitido;
        this.financiador = financiador;
        this.programa = programa;
        this.observacion = observacion;
    }

    public Long getIdAspirante() {
        return idAspirante;
    }

    public String getTipo_De_Documento() {
        return tipo_De_Documento;
    }

    public Long getDocumento() {
        return documento;
    }

    public String getNombres_Completos() {
        return nombres_Completos;
    }

    public Long getTelefono() {
        return telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTipo_De_Perfil() {
        return tipo_De_Perfil;
    }

    public String getEstado_De_Proceso() {
        return estado_De_Proceso;
    }

    public Double getResultado_Prueba_Gorilla() {
        return resultado_Prueba_Gorilla;
    }

    public String getLink_De_Prueba() {
        return link_De_Prueba;
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

    public void setIdAspirante(Long idAspirante) {
        this.idAspirante = idAspirante;
    }

    public void setTipo_De_Documento(String tipo_De_Documento) {
        this.tipo_De_Documento = tipo_De_Documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public void setNombres_Completos(String nombres_Completos) {
        this.nombres_Completos = nombres_Completos;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setTipo_De_Perfil(String tipo_De_Perfil) {
        this.tipo_De_Perfil = tipo_De_Perfil;
    }

    public void setEstado_De_Proceso(String estado_De_Proceso) {
        this.estado_De_Proceso = estado_De_Proceso;
    }

    public void setResultado_Prueba_Gorilla(Double resultado_Prueba_Gorilla) {
        this.resultado_Prueba_Gorilla = resultado_Prueba_Gorilla;
    }

    public void setLink_De_Prueba(String link_De_Prueba) {
        this.link_De_Prueba = link_De_Prueba;
    }

    public void setAdmitido(Boolean admitido) {
        this.admitido = admitido;
    }

    public void setFinanciador(String financiador) {
        this.financiador = financiador;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
