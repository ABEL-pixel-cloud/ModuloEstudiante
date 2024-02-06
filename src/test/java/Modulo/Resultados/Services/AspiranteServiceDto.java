package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Repositories.IAspiranteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class AspiranteServiceDto {
    @Mock
    private IAspiranteRepository aspiranteRepository;

    @InjectMocks
    private AspiranteService aspiranteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testVerificarAspirantesyValidarDocumentacion_AspiranteExiste() {
        // Arrange
        Long cedulaAspirante = 12345L; // se define numero de cedula
        Aspirante aspiranteMock = new Aspirante(); // se crea instancia de aspirante
        Optional<Aspirante> aspiranteExiste = Optional.of(aspiranteMock); // simulamos la base de datos
        when(aspiranteRepository.findByDocumento(cedulaAspirante)).thenReturn(aspiranteExiste); //

        // Act
        //realiza la verificación de la existencia del aspirante y la validación de la documentación.
        Aspirante resultado = aspiranteService.verificarAspirantesyValidarDocumentacion(cedulaAspirante);

        // Assert
        assertNotNull(resultado); // verificar que el resultado no sea nulo
        assertEquals(aspiranteMock, resultado); // verificamos que el aspirante retornado sea igual que el aspirante aspiranteMock que se ha creado.
    }


}
