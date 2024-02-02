package Modulo.Resultados;



import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import Modulo.Resultados.Services.AspiranteService;
import Modulo.Resultados.Services.DocumentacionService;
import Modulo.Resultados.Services.DocumentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class DocumentoServiceTest {
    @Mock
    private AspiranteService aspiranteService;


    // creamos un mock para el repository IdocumentacionRepository
    @Mock
    private IDocumentacionRepository documentacionRepository;

    //inyectamos el mock al servicio documentacionservice
    @InjectMocks
    private DocumentacionService documentacionService;

    // ejecutar antes de cada metodo de prueba
    @BeforeEach
    void beforeEach(){
        this.documentacionService= new DocumentoService();
    }

    @Test
    public void testStore_DocumentacionExistente() throws IOException {
        // Arrange
        Long cedulaAspirante = 12345L;  // creamos el numero de cedula para el aspirante
        //Se crean objetos MockMultipartFile simulados para representar los archivos que se cargarán durante las pruebas.
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());
        MockMultipartFile documento = new MockMultipartFile("documento", "documento.txt", "text/plain", "documento data".getBytes());
        // se crea un aspirante de prueba
        Aspirante aspiranteMock = new Aspirante();
        when(aspiranteService.verificarAspirantesyValidarDocumentacion(cedulaAspirante)).thenReturn(aspiranteMock);
        // crea una documentación de prueba
        Documentacion documentacionMock = new Documentacion();
        when(documentacionRepository.findByAspirante(aspiranteMock)).thenReturn(Optional.of(documentacionMock));

        // Act
        Documentacion resultado = documentacionService.store(file, documento, cedulaAspirante);

        // Assert
        assertEquals(documentacionMock, resultado);
    }




}
