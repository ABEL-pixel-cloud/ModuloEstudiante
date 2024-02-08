package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;

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
    public void testCrearEstudiantes() {
        // Arrange
        Aspirante aspirante = new Aspirante();
        aspirante.setIdaspirante(1L);
        aspirante.setNombresCompletos("Nombre Completo");
        aspirante.setDocumento(Long.valueOf("12345"));
        aspirante.setPrograma("Programa");

        List<Aspirante> aspirantes = new ArrayList<>();
        aspirantes.add(aspirante);

        Documentacion documentacion = new Documentacion();
        documentacion.setAspirante(aspirante);
        documentacion.setEstadoDocumentos(true);

        // Simular el comportamiento del repositorio de aspirante
        when(aspiranteRepository.findById(1L)).thenReturn(Optional.of(aspirante));

        // Simular el comportamiento del repositorio de documentación
        when(documentacionRepository.findByAspirante(aspirante)).thenReturn(Optional.of(documentacion));

        // Act
        estudianteService.crearEstudiantes(aspirantes);

        // Assert
        verify(estudianteRepository, times(1)).save(any());
    }
}