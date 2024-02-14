package Modulo.Resultados.Controllers;


import Modulo.Resultados.Dtos.DocumentosDto;
import Modulo.Resultados.Dtos.EstadoDocumentosDto;
import Modulo.Resultados.Dtos.ReciveCohortesDto;
import Modulo.Resultados.Dtos.ReciveDocumentosDto;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Exceptions.ResponseMessage;
import Modulo.Resultados.Services.CredencialesEstudiante;
import Modulo.Resultados.Services.DocumentoService;
import Modulo.Resultados.Services.EstudianteService;
import Modulo.Resultados.Services.CohorteService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/documentos")
public class DocumentosController {


    private DocumentoService documentoService;
    private CredencialesEstudiante aspirante;

    private EstudianteService estudianteService;
    private CohorteService serviceCohorte;
    @Autowired
    public DocumentosController(DocumentoService documentoService,
                                CredencialesEstudiante aspirante,
                                EstudianteService estudianteService,
                                CohorteService serviceCohorte) {
        this.documentoService = documentoService;
        this.aspirante = aspirante;
        this.estudianteService = estudianteService;
        this.serviceCohorte=serviceCohorte;}








    @PostMapping(value = "/cargarArchivos", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseMessage> cargarArchivo(@RequestPart("acta") @Schema(description = "Archivo del acta a cargar", format = "binary") MultipartFile acta,
                                                         @RequestPart("documento") @Schema(description = "Archivo del documento a cargar", format = "binary") MultipartFile documento,
                                                         @RequestParam("cedulaAspirante") @Schema(description = "Cédula del aspirante", example = "1234567890") Long cedulaAspirante)
            throws IOException {

        documentoService.store(acta,documento,cedulaAspirante);
        return ResponseEntity.status(HttpStatus.OK).
                body(new ResponseMessage("archivo subido correctamente"));

    }

    @GetMapping("/listarDocumentos")
    public ResponseEntity<List<DocumentosDto>> listarArchivos(){
        List<DocumentosDto> documento=documentoService.getAllFiles();
        if (documento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Devuelve 204 si no hay contenido
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(documento);
        }
    }



    @PostMapping("/agregarEstadoDocumento")
    public ResponseEntity<ResponseMessage> estadoDocumento(@RequestParam("idaspirante")
                                                               @Schema(description = "numero del aspirante", example = "1 o 2 o 3") Long id,
                                                           @RequestParam("estado")  @Schema(description = "Estado del documento", example = "true o false") Boolean estado) throws IOException {


        documentoService.estadoDocumentacion(id, estado);
        estudianteService.crearEstudiantes(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage("estado agregado"));
    }

    @PostMapping("/asignarCohorte")
    public ResponseEntity<ResponseMessage> cohorte(@RequestParam("idestudiante") @Schema(description = "numero del estudiante", example = "1 o 2 o 3") Long id, @RequestParam("Cohorte") @Schema(description = "grupo a pertenecer", example = "1 o 2 o 3") String cohorte)  {
          serviceCohorte.creacionDeCohorte(id,cohorte);
          aspirante.enviarCredencialesEstudiante(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseMessage(" cohorte asignada correctamente"));

    }


    @GetMapping("descargarCedula/{id}")
    public ResponseEntity<byte[]> descargarArchivo(@PathVariable  @Schema(description = "cadena de 32 digitos hexadecimal  ", example = "550e8400-e29b-41d4-a716-446655440000") UUID id) throws FileNotFoundException {
        Documentacion documentacion = documentoService.getfile(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, documentacion.getTipoDocumentocedula())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentacion.getDocumentoCedula() + "\"")
                .body(documentacion.getDataDocumentoCedula());

    }
    @GetMapping("descargarActa/{id}")
    public ResponseEntity<byte[]> descargarArchivoacta(@PathVariable  @Schema(description = "cadena de 32 digitos hexadecimal  ", example = "550e8400-e29b-41d4-a716-446655440000") UUID id) throws FileNotFoundException {
            Documentacion documentacion = documentoService.getfile(id).get();
            return ResponseEntity.status(HttpStatus.OK)
                    .header(HttpHeaders.CONTENT_TYPE, documentacion.getTipoDocumentoacta())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentacion.getDocumentoActa() + "\"")
                    .body(documentacion.getDataDocumentoActa());


        }



}
