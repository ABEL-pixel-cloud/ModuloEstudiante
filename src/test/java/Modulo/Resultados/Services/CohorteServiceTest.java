package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Cohorte;
import Modulo.Resultados.Entity.Estudiante;
import Modulo.Resultados.Repositories.ICohorteRepository;
import Modulo.Resultados.Repositories.IEstudianteRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class CohorteServiceTest {

    private CohorteService cohorteService;

    @Test
    public void testCreacionDeCohorte() {
        // Arrange
        List<Estudiante> estudiantes = new ArrayList<>();
        Estudiante estudianteMock1 = new Estudiante();
        estudianteMock1.setIdEstudiante(1L); // Estudiante existente en la base de datos
        estudiantes.add(estudianteMock1);

        String cohorte = "Cohorte2024";

        // Mock del repositorio de Cohorte
        ICohorteRepository cohorteRepositoryMock = mock(ICohorteRepository.class);

        // Mock del repositorio de Estudiante
        IEstudianteRepository estudianteRepositoryMock = mock(IEstudianteRepository.class);
        when(estudianteRepositoryMock.findById(1L)).thenReturn(Optional.of(estudianteMock1));

        // Instancia del servicio
        CohorteService service = new CohorteService(estudianteRepositoryMock,cohorteRepositoryMock);

        // Act
        service.CreacionDeCohorte(estudiantes, cohorte);

        // Assert
        // Verificar que se haya guardado la cohorte
        verify(cohorteRepositoryMock, times(1)).save(any(Cohorte.class));

        // Verificar que se haya asignado la cohorte al estudiante
        assertEquals(cohorte, estudianteMock1.getCohorte().getCohorte());
    }
}
