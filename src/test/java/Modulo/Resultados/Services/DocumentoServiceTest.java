package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import java.io.IOException;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

        when(aspiranteService.verificarAspirantes(cedulaAspirante)).thenReturn(aspiranteMock);

        Documentacion documentacionMock = new Documentacion();
        when(documentacionRepository.findByAspirante(aspiranteMock)).thenReturn(Optional.of(documentacionMock));
        when(documentacionRepository.save(ArgumentMatchers.any(Documentacion.class))).thenReturn(documentacionMock);

        // Act
        Documentacion resultado = documentoService.store(file, documento, cedulaAspirante);

        // Assert
        assertEquals(documentacionMock, resultado);
    }
}
