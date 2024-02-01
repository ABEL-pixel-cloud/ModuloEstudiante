package Modulo.Resultados.Dtos;


import lombok.Builder;

@Builder
public class EstadoDocumentosDto {
    private Long idAspirante;

    private Boolean estado;



    public EstadoDocumentosDto(Long idAspirante, Boolean estado) {
        this.idAspirante = idAspirante;
        this.estado = estado;
    }

    public Long getIdAspirante() {
        return idAspirante;
    }

    public Boolean getEstado() {
        return estado;
    }
}
