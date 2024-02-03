package Modulo.Resultados.Dtos;


import lombok.*;

@Getter // genera metodos getter para todos los metodos de la clase
@Setter
@NoArgsConstructor // genera un constructor sin argumentos
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
