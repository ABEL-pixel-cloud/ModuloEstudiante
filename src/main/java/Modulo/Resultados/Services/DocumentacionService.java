package Modulo.Resultados.Services;


import Modulo.Resultados.Dtos.DocumentosDto;
import Modulo.Resultados.Entity.Documentacion;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DocumentacionService {

    // permite almacenar o cargar archivos a la base de datos

    Documentacion store(MultipartFile file1, MultipartFile file2, Long cedulaAspirante) throws IOException;


    // permite descargar archivos de la base de datos
    Optional<Documentacion> getfile(UUID id) throws FileNotFoundException;

    // permite consultar la lista de archivos cargados a nuestra base de datos
    List<DocumentosDto> getAllFiles();





}
