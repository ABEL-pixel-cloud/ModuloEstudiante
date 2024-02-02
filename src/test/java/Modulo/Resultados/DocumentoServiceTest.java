package Modulo.Resultados;



import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import Modulo.Resultados.Services.AspiranteService;
import Modulo.Resultados.Services.DocumentacionService;
import Modulo.Resultados.Services.DocumentoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
public class DocumentoServiceTest {
    @Mock
    private AspiranteService aspiranteService;


    // creamos un mock para el repository IdocumentacionRepository
    @Mock
    private IDocumentacionRepository documentacionRepository;

    //inyectamos el mock al servicio documentacionservice
    @InjectMocks
    private DocumentacionService documentacionService;



    @Test
    public void testStore_DocumentacionExistente() throws IOException {
        // Arrange
        Long cedulaAspirante = 12345L;  // creamos él numero de cedula para el aspirante
        //Se crean objetos MockMultipartFile simulados para representar los archivos que se cargarán durante las pruebas.
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "test data".getBytes());
        MockMultipartFile documento = new MockMultipartFile("documento", "documento.txt", "text/plain", "documento data".getBytes());
        // se crea un aspirante de prueba
        Aspirante aspiranteMock = new Aspirante();
        when(aspiranteService.verificarAspirantesyValidarDocumentacion(cedulaAspirante)).thenReturn(aspiranteMock);
        // crea una documentación de prueba
        Documentacion documentacionMock = new Documentacion();
        when(documentacionRepository.findByAspirante(aspiranteMock)).thenReturn(Optional.of(documentacionMock));

        // Act  se llama el metodo store y pasamos archivos simulados
        Documentacion resultado = documentacionService.store(file, documento, cedulaAspirante);

        // Assert  verificamos si el resultado obtenido es el esperado
        assertEquals(documentacionMock, resultado);
    }




}
