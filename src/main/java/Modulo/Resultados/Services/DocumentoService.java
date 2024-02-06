package Modulo.Resultados.Services;

import Modulo.Resultados.Dtos.DocumentosDto;
import Modulo.Resultados.Dtos.EstadoDocumentosDto;
import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class DocumentoService implements IDocumentacionService {
    private IDocumentacionRepository documentacionRepository;
    private AspiranteService aspiranteService;
    @Autowired
    public DocumentoService(IDocumentacionRepository documentacionRepository,
                            AspiranteService aspiranteService) {
        this.documentacionRepository = documentacionRepository;

        this.aspiranteService = aspiranteService;
    }


    @Override
    public Documentacion store(MultipartFile file, MultipartFile documento, Long cedulaAspirante) throws IOException {
        Aspirante aspirante = aspiranteService.verificarAspirantesyValidarDocumentacion(cedulaAspirante);

        // Verificar si ya existe una documentación para este aspirante
        Optional<Documentacion> documentacionExistente = documentacionRepository.findByAspirante(aspirante);

        if (documentacionExistente.isPresent()) {
            // si Ya hay una documentación existente, entonces vamos a sobrescribir
            Documentacion documentacionAnterior = documentacionExistente.get();

            // Eliminamos la documentación anterior
            documentacionRepository.delete(documentacionAnterior);

            // Creamos la nueva documentación
            String filenameDocumento = StringUtils.cleanPath(Objects.requireNonNull(documento.getOriginalFilename()));
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Documentacion nuevaDocumentacion = Documentacion.builder()
                    .documentoActa(filename)
                    .documentoCedula(filenameDocumento)
                    .tipoDocumentoacta(file.getContentType())
                    .tipoDocumentocedula(documento.getContentType())
                    .nombreAspirante(aspirante.getNombresCompletos())
                    .cedulaAspirante(aspirante.getDocumento())
                    .estadoDocumentos(false)
                    .dataDocumentoActa(file.getBytes())
                    .dataDocumentoCedula(documento.getBytes())
                    .aspirante(aspirante)
                    .build();


            // Guardamos la nueva documentación
            return documentacionRepository.save(nuevaDocumentacion);
        } else {
            // No hay documentación existente, simplemente creamos una nueva
            String filenameDocumento = StringUtils.cleanPath(Objects.requireNonNull(documento.getOriginalFilename()));
            String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            Documentacion documentacion = Documentacion.builder()
                    .documentoActa(filename)
                    .documentoCedula(filenameDocumento)
                    .tipoDocumentoacta(file.getContentType())
                    .tipoDocumentocedula(documento.getContentType())
                    .nombreAspirante(aspirante.getNombresCompletos())
                    .cedulaAspirante(aspirante.getDocumento())
                    .estadoDocumentos(false)
                    .dataDocumentoActa(file.getBytes())
                    .dataDocumentoCedula(documento.getBytes())
                    .aspirante(aspirante)
                    .build();

            // Guardamos la nueva documentación
            return documentacionRepository.save(documentacion);
        }

    }



    @Override
    public Optional<Documentacion> getfile(UUID id) throws FileNotFoundException {
        Optional<Documentacion> file= documentacionRepository.findById(id);
        if (file.isPresent()){
            return file;
        }
        throw new FileNotFoundException();

    }

    @Override
    public List<DocumentosDto> getAllFiles() {
        // Mapear la lista de registros a una lista de objetos DocumentosDto
        List<DocumentosDto> files = documentacionRepository.findAll().stream().map(dbFile -> {
            /*
            // Construir la URL para descargar el archivo usando Spring ServletUriComponentsBuilder
            String urlActa = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/documentos/acta/")
                    .path(dbFile.getIddocumentacion().toString())
                    .toUriString();

             */
            String urlActa = null;
            if (dbFile.getIddocumentacion() != null) {
                urlActa = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("api/documentos/acta/")
                        .path(dbFile.getIddocumentacion().toString())
                        .toUriString();
            }

            String urldocumento = null;
            if (dbFile.getIddocumentacion() != null) {
                urldocumento = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("api/documentos/cedula/")
                        .path(dbFile.getIddocumentacion().toString())
                        .toUriString();
            }
            /*

            String urldocumento = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("api/documentos/cedula/")
                    .path(dbFile.getIddocumentacion().toString())
                    .toUriString();

             */


            // Construir un objeto DocumentosDto con la información del archivo
            return DocumentosDto.builder()
                    .id(dbFile.getAspirante() != null ? dbFile.getAspirante().getIdaspirante() : null)
                    .nombreacta(dbFile.getDocumentoActa())
                    .urlacta(urlActa)
                    .tipoDocumentoacta(dbFile.getTipoDocumentoacta())
                    .tamanoacta(Long.valueOf(dbFile.getDataDocumentoActa().length))

                    .nombredocumentocedula(dbFile.getDocumentoCedula())
                    .urldocumentocedula(urldocumento)
                    .tipoDocumentocedula(dbFile.getTipoDocumentocedula())
                    .tamanodocumentocedula(Long.valueOf((dbFile.getDataDocumentoCedula().length)))

                    .nombreAspirante((dbFile.getAspirante() != null ? dbFile.getAspirante().getNombresCompletos() : null))
                    .cedulaAspirante((dbFile.getAspirante() != null ? dbFile.getAspirante().getDocumento() : null))
                    .build();

        }).collect(Collectors.toList());

        return files;
    }

    public List<EstadoDocumentosDto> estadoDocumentacion(List<Aspirante> idAspirante, Boolean estado) {

        List<EstadoDocumentosDto> estadoDocumentacionList = new ArrayList<>();

        for (Aspirante aspirante : idAspirante) {

            Long idAspiranteActual = aspirante.getIdaspirante(); // Acceder al ID del aspirante actual

                // Buscar la documentación asociada al aspirante
                Optional<Documentacion> documentacion = documentacionRepository.findByAspirante(aspirante);

                if (documentacion.isPresent()) {
                    Documentacion documentacionEncontrada = documentacion.get();
                    // Establecer el nuevo estado de la documentación
                    documentacionEncontrada.setEstadoDocumentos(estado);
                    documentacionEncontrada.setAspirante(aspirante);
                    documentacionRepository.save(documentacionEncontrada);

                    // Crear un DTO de estado de documentos y agregarlo a la lista
                    EstadoDocumentosDto documentosDto = new EstadoDocumentosDto(
                            (idAspiranteActual),
                            documentacionEncontrada.getEstadoDocumentos());
                    estadoDocumentacionList.add(documentosDto);
                } else {

                     throw new RuntimeException("No se encontró documentación para el aspirante con ID:");
                }

        }
        return estadoDocumentacionList;
    }


}
