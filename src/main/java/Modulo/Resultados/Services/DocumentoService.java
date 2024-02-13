package Modulo.Resultados.Services;

import Modulo.Resultados.Dtos.DocumentosDto;
import Modulo.Resultados.Dtos.EstadoDocumentosDto;
import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Repositories.IAspiranteRepository;
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

    private IAspiranteRepository aspiranteRepository;
    @Autowired
    public DocumentoService(IDocumentacionRepository documentacionRepository,
                            AspiranteService aspiranteService,IAspiranteRepository aspiranteRepository) {
        this.documentacionRepository = documentacionRepository;
        this.aspiranteService = aspiranteService;
        this.aspiranteRepository=aspiranteRepository;
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

            String urlActa = null;
            if (dbFile.getIddocumentacion() != null) {
                urlActa = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("api/documentos/descargar-acta/")
                        .path(dbFile.getIddocumentacion().toString())
                        .toUriString();
            }

            String urldocumento = null;
            if (dbFile.getIddocumentacion() != null) {
                urldocumento = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("api/documentos/descargar-cedula/")
                        .path(dbFile.getIddocumentacion().toString())
                        .toUriString();
            }

            // se hicieron correciones en id, nombre aspirante y cedulaaspirante
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
                    //(condición ? valorSiVerdadero : valorSiFalso)
                    .nombreAspirante((dbFile.getAspirante() != null ? dbFile.getAspirante().getNombresCompletos() : null))
                    .cedulaAspirante((dbFile.getAspirante() != null ? dbFile.getAspirante().getDocumento() : null))
                    .build();

        }).collect(Collectors.toList());

        return files;
    }


    public void estadoDocumentacion(Long idAspirante, Boolean estado) {


        // Buscar el aspirante por ID
        Optional<Aspirante> aspiranteOptional = aspiranteRepository.findById(idAspirante);
        if (aspiranteOptional.isPresent()) {
            Aspirante aspirante = aspiranteOptional.get();

            // Buscar la documentación asociada al aspirante
            Optional<Documentacion> documentacionOptional = documentacionRepository.findByAspirante(aspirante);
            if (documentacionOptional.isPresent()) {
                Documentacion documentacionEncontrada = documentacionOptional.get();
                // Establecer el nuevo estado de la documentación
                documentacionEncontrada.setEstadoDocumentos(estado);
                documentacionEncontrada.setAspirante(aspirante);
                documentacionRepository.save(documentacionEncontrada);

            } else {
                throw new RuntimeException("No se encontró documentación para el aspirante con ID: " + idAspirante);
            }
        } else {
            throw new RuntimeException("No se encontró aspirante con ID: " + idAspirante);
        }
    }




}
