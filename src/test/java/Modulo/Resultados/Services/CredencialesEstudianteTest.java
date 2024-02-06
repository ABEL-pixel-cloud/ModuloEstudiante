package Modulo.Resultados.Services;

import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Cohorte;
import Modulo.Resultados.Entity.Estudiante;
import Modulo.Resultados.Repositories.IAspiranteRepository;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import Modulo.Resultados.Repositories.IEstudianteRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CredencialesEstudianteTest {

    @Mock
    private IDocumentacionRepository documentacionRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private IAspiranteRepository aspiranteRepository;

    @Mock
    private IEstudianteRepository estudianteRepository;
    @InjectMocks
    private CredencialesEstudiante credencialesEstudiante;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        credencialesEstudiante = new CredencialesEstudiante(documentacionRepository, emailService, aspiranteRepository, estudianteRepository);
    }

    @Test
    public void testEnviarCredencialesEstudiante() {
        // Arrange
        Estudiante estudiante = new Estudiante();
        estudiante.setIdEstudiante(1L);

        Aspirante aspirante = new Aspirante();
        aspirante.setIdaspirante(1L);
        aspirante.setCorreo("example@example.com");
        aspirante.setPrograma("Desarrollo Back-End");

        Cohorte cohorte = new Cohorte();
        cohorte.setCohorte("Cohorte5");

        estudiante.setAspirante(aspirante);
        estudiante.setCohorte(cohorte);

        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(estudiante);

        when(estudianteRepository.findByidEstudiante(1L)).thenReturn(Optional.of(estudiante));



        // Assert
        ArgumentCaptor<String[]> emailCaptor = ArgumentCaptor.forClass(String[].class);
        ArgumentCaptor<String> subjectCaptor = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> messageCaptor = ArgumentCaptor.forClass(String.class);

        verify(emailService).sendEmail(emailCaptor.capture(), subjectCaptor.capture(), messageCaptor.capture());

        String[] capturedEmails = emailCaptor.getValue();
        String capturedSubject = subjectCaptor.getValue();
        String capturedMessage = messageCaptor.getValue();

        assertEquals(1, capturedEmails.length);
        assertEquals("example@example.com", capturedEmails[0]);
        assertEquals("Estas son tus credenciales para Desarrollo Back-End", capturedSubject);
        assertNotNull(capturedMessage);
        assertTrue(capturedMessage.contains("Hola"));
        assertTrue(capturedMessage.contains("Cohorte5"));
        assertTrue(capturedMessage.contains("Grupo de WhatsApp"));
        assertTrue(capturedMessage.contains("Grupo de Slack"));
        assertTrue(capturedMessage.contains("Correo de Makaia"));
        assertTrue(capturedMessage.contains("FELICITACIONES"));
    }

}
