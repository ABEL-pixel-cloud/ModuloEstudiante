package Modulo.Resultados.Services;

import Modulo.Resultados.Dtos.DocumentosDto;
import Modulo.Resultados.Dtos.EstadoDocumentosDto;
import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class DocumentoServiceTest {

    @Mock
    private IDocumentacionRepository documentacionRepository;

    @Mock
    private AspiranteService aspiranteService;

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
        // se Crea una lista de aspirantes para probar
        List<Aspirante> aspirantes = new ArrayList<>();
        Aspirante aspirante1 = new Aspirante();
        aspirante1.setIdaspirante(1L); // Definir un ID para el primer aspirante de tipo long con 1l
        aspirantes.add(aspirante1);

        // Crear una documentación para el primer aspirante
        Documentacion documentacion = new Documentacion();
        documentacion.setEstadoDocumentos(false); // Estado inicial de los documentos

        // Mockear el comportamiento del repositorio para devolver la documentación cuando se busque por el aspirante
        //(any(Aspirante.class) no importa queinstancia espesifica se pase al metodo
        when(documentacionRepository.findByAspirante(any(Aspirante.class))).thenReturn(Optional.of(documentacion));

        // Act
        List<EstadoDocumentosDto> estadoDocumentacionList = documentoService.estadoDocumentacion(aspirantes, true);

        // Assert
        // Verificar que la lista de DTOs no esté vacía
        assertFalse(estadoDocumentacionList.isEmpty());

        // Verificar que la documentación se haya actualizado con el nuevo estado
        verify(documentacionRepository, times(1)).save(any(Documentacion.class));


    }

}
