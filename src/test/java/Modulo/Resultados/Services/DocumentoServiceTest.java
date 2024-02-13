package Modulo.Resultados.Services;

import Modulo.Resultados.Dtos.DocumentosDto;
import Modulo.Resultados.Dtos.EstadoDocumentosDto;
import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Repositories.IAspiranteRepository;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DocumentoServiceTest {

    @Mock
    private IDocumentacionRepository documentacionRepository;

    @Mock
    private AspiranteService aspiranteService;
    @Mock
    private IAspiranteRepository aspiranteRepository;

    @InjectMocks
    private DocumentoService documentoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

   @Test
    public void testStore_DocumentacionExistente() throws IOException {
        // Arrange
        Long cedulaAspirante = 12345L;
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());
        MockMultipartFile documento = new MockMultipartFile("documento", "documento.txt", "text/plain", "documento data".getBytes());
        Aspirante aspiranteMock = new Aspirante();

        when(aspiranteService.verificarAspirantesyValidarDocumentacion(cedulaAspirante)).thenReturn(aspiranteMock);

        Documentacion documentacionMock = new Documentacion();
        when(documentacionRepository.findByAspirante(aspiranteMock)).thenReturn(Optional.of(documentacionMock));
        when(documentacionRepository.save(any(Documentacion.class))).thenReturn(documentacionMock);

        // Act
        Documentacion resultado = documentoService.store(file, documento, cedulaAspirante);

        // Assert
        assertEquals(documentacionMock, resultado);


    }

    @Test
    public void testGetfile_FileFound() throws FileNotFoundException {
        // Arrange
        UUID fileId = UUID.randomUUID();
        Documentacion documentacionMock = new Documentacion();
        when(documentacionRepository.findById(fileId)).thenReturn(Optional.of(documentacionMock));

        // Act
        Optional<Documentacion> resultado = documentoService.getfile(fileId);

        // Assert
        assertTrue(resultado.isPresent());
        assertEquals(documentacionMock, resultado.get());
    }


    @Test
    public void testGetfile_FileNotFound() {
        // Arrange
        UUID fileId = UUID.randomUUID();
        when(documentacionRepository.findById(fileId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FileNotFoundException.class, () -> {
            documentoService.getfile(fileId);
        });
    }


    @Test
    public void testGetAllFiles() {
        // Arrange
        // asignando arreglos vacíos (new byte[]{}) a los campos dataDocumentoActa y dataDocumentoCedula de esa instancia.
        Documentacion documentacion1 = new Documentacion();
        documentacion1.setDataDocumentoActa(new byte[]{});
        documentacion1.setDataDocumentoCedula(new byte[]{});

        // asignando arreglos vacíos (new byte[]{}) a los campos dataDocumentoActa y dataDocumentoCedula de esa instancia.
        Documentacion documentacion2 = new Documentacion();
        documentacion2.setDataDocumentoActa(new byte[]{});
        documentacion2.setDataDocumentoCedula(new byte[]{});

        //Esta lista contiene las instancias documentacion1 y documentacion2 que se crearon previamente.
        List<Documentacion> documentacionList = Arrays.asList(documentacion1, documentacion2);

        when(documentacionRepository.findAll()).thenReturn(documentacionList);

        // Mock para simular la solicitud HTTP
        //  MockHttpServletRequest Permite establecer y recuperar diversos parámetros y atributos de solicitud
        MockHttpServletRequest request = new MockHttpServletRequest();
        // RequestContextHolder Permite almacenar y recuperar el contexto de la solicitud actual en un hilo de ejecución.
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        List<DocumentosDto> result = documentoService.getAllFiles();

        // Assert
        assertEquals(documentacionList.size(), result.size());

    }

    @Test
    public void testEstadoDocumentacion() {
        // Arrange
        Long idAspirante = 1L;
        Boolean nuevoEstado = true;

        // Crear un aspirante simulado
        Aspirante aspirante = new Aspirante();
        aspirante.setIdaspirante(idAspirante);

        // Crear una documentación simulada
        Documentacion documentacion = new Documentacion();
        documentacion.setEstadoDocumentos(!nuevoEstado); // Estado inicial de los documentos

        // Mockear el comportamiento del repositorio de aspirantes para devolver el aspirante cuando se busque por su ID
        when(aspiranteRepository.findById(idAspirante)).thenReturn(Optional.of(aspirante));

        // Mockear el comportamiento del repositorio de documentos para devolver la documentación cuando se busque por el aspirante
        when(documentacionRepository.findByAspirante(aspirante)).thenReturn(Optional.of(documentacion));

        // Act
        assertDoesNotThrow(() -> {
            documentoService.estadoDocumentacion(idAspirante, nuevoEstado);
        });

        // Assert
        // Verificar que el estado de la documentación se haya actualizado correctamente
        assertTrue(documentacion.getEstadoDocumentos().equals(nuevoEstado));
        // Verificar que el método save del repositorio de documentos se haya llamado una vez
        verify(documentacionRepository, times(1)).save(documentacion);
    }



}
