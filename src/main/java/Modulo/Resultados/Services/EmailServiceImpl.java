package Modulo.Resultados.Services;


import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.nio.charset.StandardCharsets;


@Service
public class EmailServiceImpl implements IMailService {
    @Value("${email.sender}")
    private String emailUser;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendEmail(String[] toUser, String subject, String message) {

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeMessageHelper.setFrom(emailUser);
            mimeMessageHelper.setTo(toUser);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message, true); // Set true to indicate HTML content

            mailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new RuntimeException("Error sending email", e);
        }
    }



    @Override
    public void sendEmailWithFile(String[] toUser, String subject, String message, File file) {


        try {
            MimeMessage mimeMessage=mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper=new MimeMessageHelper(mimeMessage,true, StandardCharsets.UTF_8.name());

            mimeMessageHelper.setFrom(emailUser);
            mimeMessageHelper.setTo(toUser);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(message,true);
            mimeMessageHelper.addAttachment(file.getName(),file);

            mailSender.send(mimeMessage);


        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
