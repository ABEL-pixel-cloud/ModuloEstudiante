package Modulo.Resultados.Dtos;



public class CrearAspiranteDto {

    private String tipo_De_Documento;


    private Long documento;


    private String nombres_Completos;

    private Long telefono;

    private String correo;

    private String tipo_De_Perfil;

    private String estado_De_Proceso;

    private Float resultado_Prueba_Gorilla;

    private String link_De_Prueba;

    private Boolean admitido;

    private String financiador;

    private String programa;

    private String observacion;


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

    public Float getResultado_Prueba_Gorilla() {
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
}
