package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;

import Modulo.Resultados.Entity.Estudiante;
import Modulo.Resultados.Repositories.IAspiranteRepository;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import Modulo.Resultados.Repositories.IEstudianteRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

public class EstudianteServiceTest {

    @Mock
    private IDocumentacionRepository documentacionRepository;

    @Mock
    private IAspiranteRepository aspiranteRepository;

    @Mock
    private IEstudianteRepository estudianteRepository;

    @InjectMocks
    private EstudianteService estudianteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCrearEstudiantes_WithExistingAspiranteAndCompleteDocumentacion() {
        // Arrange
        Long idAspirante = 1L;

        // Crear un aspirante simulado
        Aspirante aspirante = new Aspirante();
        aspirante.setIdaspirante(idAspirante);
        aspirante.setNombresCompletos("John Doe");
        aspirante.setPrograma("Ingeniería");
        aspirante.setDocumento(1234567890L); // Establecer el documento como un número long

        // Crear una documentación simulada con estado completo
        Documentacion documentacion = new Documentacion();
        documentacion.setEstadoDocumentos(true);

        // Mockear el comportamiento del repositorio de aspirantes para devolver el aspirante cuando se busque por su ID
        when(aspiranteRepository.findById(idAspirante)).thenReturn(Optional.of(aspirante));
        // Mockear el comportamiento del repositorio de documentos para devolver la documentación cuando se busque por el aspirante
        when(documentacionRepository.findByAspirante(aspirante)).thenReturn(Optional.of(documentacion));

        // Act
        assertDoesNotThrow(() -> {
            estudianteService.crearEstudiantes(idAspirante);
        });

        // Assert
        // Verificar que el método save del repositorio de estudiantes se haya llamado una vez
        verify(estudianteRepository, times(1)).save(any(Estudiante.class));
    }



}