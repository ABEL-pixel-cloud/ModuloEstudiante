package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Cohorte;
import Modulo.Resultados.Entity.Estudiante;
import Modulo.Resultados.Repositories.ICohorteRepository;
import Modulo.Resultados.Repositories.IEstudianteRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class CohorteServiceTest {


    @Mock
    private IEstudianteRepository estudianteRepositoryMock;

    @Mock
    private ICohorteRepository cohorteRepositoryMock;
    @InjectMocks
    private CohorteService cohorteService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testCreacionDeCohorte() {
        // Arrange
        List<Estudiante> estudiantes = Arrays.asList(
                new Estudiante(1L, "Estudiante 1"), // Asigna IDs válidos a los estudiantes
                new Estudiante(2L, "Estudiante 2")
        );
        String cohorte = "Cohorte 2024";

        // Configura el comportamiento del mock para devolver estudiantes válidos cuando se llame a findById
        when(estudianteRepositoryMock.findById(anyLong()))
                .thenAnswer(invocation -> {
                    Long id = invocation.getArgument(0);
                    for (Estudiante estudiante : estudiantes) {
                        if (estudiante.getIdEstudiante().equals(id)) {
                            return Optional.of(estudiante);
                        }
                    }
                    return Optional.empty();
                });

        Cohorte cohorteObjetoMock = new Cohorte(); // Crear un objeto Cohorte mock
        when(cohorteRepositoryMock.save(any(Cohorte.class))).thenReturn(cohorteObjetoMock); // Mock para el repositorio de Cohorte

        // Act
        cohorteService.CreacionDeCohorte(estudiantes, cohorte);

        // Assert
        verify(cohorteRepositoryMock, times(1)).save(any(Cohorte.class)); // Verificar que se llama a save en el repositorio de Cohorte
        verify(estudianteRepositoryMock, times(estudiantes.size())).findById(anyLong()); // Verificar que se llama a findById en el repositorio de Estudiante
        verify(estudianteRepositoryMock, times(estudiantes.size())).save(any(Estudiante.class)); // Verificar que se llama a save en el repositorio de Estudiante
    }
}
