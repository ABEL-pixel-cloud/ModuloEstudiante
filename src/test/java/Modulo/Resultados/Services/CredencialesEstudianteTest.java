package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Cohorte;
import Modulo.Resultados.Entity.Estudiante;
import Modulo.Resultados.Repositories.IEstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;
import static org.mockito.Mockito.*;

public class CredencialesEstudianteTest {


    @Mock
    private IEstudianteRepository estudianteRepository;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private CredencialesEstudiante credencialesEstudiante;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void enviarCredencialesEstudiante_ValidId_CredentialsSent() {
        // Arrange
        Long idEstudiante = 1L;
        Estudiante estudiante = new Estudiante();
        estudiante.setIdEstudiante(idEstudiante);
        estudiante.setNombre("Nombre Estudiante");
        estudiante.setAspirante(new Aspirante());
        estudiante.getAspirante().setCorreo("example@example.com");
        estudiante.getAspirante().setPrograma("Desarrollo Back-End");
        estudiante.setCohorte(new Cohorte());
        estudiante.getCohorte().setCohorte("Cohorte 1");

        when(estudianteRepository.findByidEstudiante(anyLong())).thenReturn(Optional.of(estudiante));

        // Act
        credencialesEstudiante.enviarCredencialesEstudiante(idEstudiante);

        // Assert
        verify(emailService, times(1)).sendEmail(any(String[].class), anyString(), anyString());
    }





}
