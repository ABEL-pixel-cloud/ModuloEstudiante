package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Cohorte;
import Modulo.Resultados.Entity.Estudiante;
import Modulo.Resultados.Repositories.ICohorteRepository;
import Modulo.Resultados.Repositories.IEstudianteRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;

public class CohorteServiceTest {
    @InjectMocks
    private CohorteService cohorteService;

    @Mock
    private IEstudianteRepository estudianteRepositoryMock;

    @Mock
    private ICohorteRepository cohorteRepositoryMock;


    @Test
    public void testCreacionDeCohorte() {
        // Arrange
        List<Estudiante> estudiantes = Arrays.asList(
                new Estudiante(1L, "Estudiante 1"),
                new Estudiante(2L, "Estudiante 2")
        );
        String cohorte = "Cohorte 2024";

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
