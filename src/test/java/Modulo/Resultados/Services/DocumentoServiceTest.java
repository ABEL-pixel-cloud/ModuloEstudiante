package Modulo.Resultados.Services;

import Modulo.Resultados.Dtos.DocumentosDto;
import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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
        when(documentacionRepository.save(ArgumentMatchers.any(Documentacion.class))).thenReturn(documentacionMock);

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
        Documentacion documentacion1 = new Documentacion();
        documentacion1.setDataDocumentoActa(new byte[]{});
        documentacion1.setDataDocumentoCedula(new byte[]{});

        Documentacion documentacion2 = new Documentacion();
        documentacion2.setDataDocumentoActa(new byte[]{});
        documentacion2.setDataDocumentoCedula(new byte[]{});

        List<Documentacion> documentacionList = Arrays.asList(documentacion1, documentacion2);

        when(documentacionRepository.findAll()).thenReturn(documentacionList);

        // Mock para simular la solicitud HTTP
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Act
        List<DocumentosDto> result = documentoService.getAllFiles();

        // Assert
        assertEquals(documentacionList.size(), result.size());
        // Puedes agregar más aserciones según tus necesidades específicas
    }
}
