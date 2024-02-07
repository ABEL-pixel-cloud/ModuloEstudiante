package Modulo.Resultados.Services;

import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class EmailServiceTest {
    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendEmail() {
        // Arrange
        String[] toUser = {"recipient@example.com"};
        String subject = "Test Subject";
        String message = "Test Message";

        // Act
        emailService.sendEmail(toUser, subject, message);

        // Assert
        verify(mailSender).send(any(MimeMessage.class)); // Verifica que el método send del mailSender haya sido llamado
    }

    @Test
    public void testSendEmailWithFile() {
        // Arrange
        String[] toUser = {"recipient@example.com"};
        String subject = "Test Subject";
        String message = "Test Message";
        File file = new File("D:\\Desktop\\ModuloResultados\\Resultados\\src\\main\\resources\\File\\ACTADE COMPROMISO.pdf");

        // Act
        emailService.sendEmailWithFile(toUser, subject, message, file);

        // Assert
        verify(mailSender).send(any(MimeMessage.class)); // Verifica que el método send del mailSender haya sido llamado
    }
}
