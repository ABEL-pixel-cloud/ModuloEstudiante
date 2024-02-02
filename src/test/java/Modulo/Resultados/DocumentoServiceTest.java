package Modulo.Resultados;



import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import Modulo.Resultados.Services.AspiranteService;
import Modulo.Resultados.Services.DocumentoService;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;


import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import static org.mockito.Mockito.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DocumentoServiceTest {
    @Mock
    private AspiranteService aspiranteService;


    // creamos un mock para el repository IdocumentacionRepository
    @Mock
    private IDocumentacionRepository documentacionRepository;

    //inyectamos el mock al servicio documentacionService
    @InjectMocks
    private DocumentoService documentoService;



    @Test
    void testStore() throws IOException {
        // Mock data
        Long cedulaAspirante = 123456789L;
        Aspirante aspirante = new Aspirante();
        aspirante.setDocumento(cedulaAspirante);
        when(aspiranteService.verificarAspirantesyValidarDocumentacion(cedulaAspirante)).thenReturn(aspirante);

        // Existing document
        Documentacion existingDocumentacion = new Documentacion();
        existingDocumentacion.setAspirante(aspirante);
        when(documentacionRepository.findByAspirante(aspirante)).thenReturn(Optional.of(existingDocumentacion));

        // Mock files
        MockMultipartFile file = new MockMultipartFile("file", "filename.txt", "text/plain", "file content".getBytes());
        MockMultipartFile documento = new MockMultipartFile("documento", "documento.txt", "text/plain", "documento content".getBytes());

        // Test method
        Documentacion result = documentoService.store(file, documento, cedulaAspirante);

        // Assertions
        assertEquals(aspirante, result.getAspirante());
        // Add more assertions based on your requirements

        verify(documentacionRepository, times(1)).delete(existingDocumentacion);
        verify(documentacionRepository, times(1)).save(ArgumentMatchers.any(Documentacion.class));

    }




}
