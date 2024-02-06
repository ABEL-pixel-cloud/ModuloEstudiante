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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/documentos/")
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
        this.serviceCohorte=serviceCohorte;
    }


    @PostMapping("/cargar")
    public ResponseEntity<ResponseMessage> cargarArchivo(@RequestParam("acta") MultipartFile acta, @RequestParam("documento") MultipartFile documento,
                                                         @RequestParam("cedulaAspirante") Long cedulaAspirante)
            throws IOException {

        documentoService.store(acta,documento,cedulaAspirante);
        return ResponseEntity.status(HttpStatus.OK).
                body(new ResponseMessage("archivo subido correctamente"));

    }

    @PostMapping("/estadoDocumento")
    public ResponseEntity<List<EstadoDocumentosDto>> estadoDocumento(@RequestBody ReciveDocumentosDto request)  {
        List<EstadoDocumentosDto> estadoDocumentacionList = documentoService.estadoDocumentacion(request.getIdAspirante(), request.getEstado());
       /* aspirante.enviarCredenciales(request.getIdAspirante()); */
        estudianteService.crearEstudiantes(request.getIdAspirante());
        return ResponseEntity.ok(estadoDocumentacionList);
    }
    @PostMapping("/asignacionCohorte")
    public void cohorte(@RequestBody ReciveCohortesDto request)  {
          serviceCohorte.creacionDeCohorte(request.getIdEstudiante(),request.getCohorte());
          aspirante.enviarCredencialesEstudiante(request.getIdEstudiante());

    }

    @GetMapping("cedula/{id}")
    public ResponseEntity<byte[]> descargarArchivo(@PathVariable UUID id) throws FileNotFoundException {
        Documentacion documentacion=documentoService.getfile(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE,  documentacion.getTipoDocumentocedula())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" +  documentacion.getDocumentoCedula() + "\"")
                .body(documentacion.getDataDocumentoCedula());

    }
    @GetMapping("acta/{id}")
    public ResponseEntity<byte[]> descargarArchivoacta(@PathVariable UUID id) throws FileNotFoundException {
        Documentacion documentacion=documentoService.getfile(id).get();
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.CONTENT_TYPE, documentacion.getTipoDocumentoacta())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documentacion.getDocumentoActa() + "\"")
                .body(documentacion.getDataDocumentoActa());

    }




    @GetMapping("/documento")
    public ResponseEntity<List<DocumentosDto>> listarArchivos(){
        List<DocumentosDto> documento=documentoService.getAllFiles();
        if (documento.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Devuelve 204 si no hay contenido
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(documento);
        }
    }


}
