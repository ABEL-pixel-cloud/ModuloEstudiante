package Modulo.Resultados.Services;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;



import java.io.File;


import static org.mockito.Mockito.*;


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
    public void sendEmail_SuccessfullySendsEmail() throws Exception {
        // Arrange
        String[] toUser = {"labelardo477@gmail.com"};
        String subject = "Test Subject";
        String message = "Test Message";

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act
        emailService.sendEmail(toUser, subject, message);

        // Assert
        verify(mailSender).send(mimeMessage);
    }

    @Test
    public void sendEmailWithFile_shouldSendEmailWithAttachment() throws MessagingException {
        // Arrange
        String[] toUser = {"klondolo8@gmail.com"};
        String subject = "Test Subject";
        String message = "Test Message";
        File file = new File("test.txt");

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);

        // Act
        emailService.sendEmailWithFile(toUser, subject, message, file);

        // Assert
        verify(mailSender).send(mimeMessage);
    }
}