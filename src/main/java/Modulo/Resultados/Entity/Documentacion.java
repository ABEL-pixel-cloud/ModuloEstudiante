package Modulo.Resultados.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "documentacion")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Documentacion {



    @Id
    @GeneratedValue
    private UUID iddocumentacion;
    @Column
    private String documentoActa;
    @Column
    private String documentoCedula;
    @Column
    private String tipoDocumentoacta;
    @Column
    private String tipoDocumentocedula;
    @Column
    private String nombreAspirante;
    @Column
    private Long cedulaAspirante;
    @Column
    private Boolean estadoDocumentos;


    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] dataDocumentoActa;

    // convertir el archivo en un dato binario

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] dataDocumentoCedula;

    @ManyToOne
    private Aspirante aspirante;


}
