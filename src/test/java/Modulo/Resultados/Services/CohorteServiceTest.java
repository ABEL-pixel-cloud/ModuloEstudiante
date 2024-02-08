package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Cohorte;
import Modulo.Resultados.Entity.Estudiante;
import Modulo.Resultados.Repositories.ICohorteRepository;
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

public class CohorteServiceTest {

    @Mock
    private IEstudianteRepository estudianteRepository;

    @Mock
    private ICohorteRepository cohorteRepository;

    @InjectMocks
    private CohorteService cohorteService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreacionDeCohorte() {
        // Arrange
        List<Estudiante> estudiantes = new ArrayList<>();
        Estudiante estudiante = new Estudiante();
        estudiante.setIdEstudiante(1L);
        estudiantes.add(estudiante);

        String cohorte = "2024A";

        // Simular el comportamiento del repositorio de cohorte
        when(cohorteRepository.save(any())).thenReturn(new Cohorte());

        // Simular el comportamiento del repositorio de estudiante
        when(estudianteRepository.findById(1L)).thenReturn(Optional.of(estudiante));

        // Act
        cohorteService.creacionDeCohorte(estudiantes, cohorte);

        // Assert
        verify(cohorteRepository, times(1)).save(any());
        verify(estudianteRepository, times(1)).save(any());
    }
}