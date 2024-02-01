package Modulo.Resultados.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;


@Configuration
public class MailConfiguration {
    @Value("${email.sender}")
    private String emailUser;

    @Value("${email.password}")
    private String password;

    @Bean
    public JavaMailSender getJavaMailSender(){

        // se crea una instancia para enviar correos electronicos
        JavaMailSenderImpl mailSender= new JavaMailSenderImpl();

        //Configuración de las propiedades del servidor de correo:
        mailSender.setHost("smtp.gmail.com");  // host para google
        mailSender.setPort(587);
        mailSender.setUsername(emailUser);
        mailSender.setPassword(password);





        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp"); //. Indica a JavaMail que debe comunicarse con el servidor de correo saliente utilizando el protocolo SMTP para enviar mensajes de correo electrónico.
        props.put("mail.smtp.auth", "true"); // lo que permite al remitente autenticarse en el servidor de correo saliente antes de enviar el correo electrónico.
        props.put("mail.smtp.starttls.enable", "true"); // se está indicando que se desea habilitar la capa de seguridad de transporte (TLS) al conectar con el servidor SMTP.
        props.put("mail.debug", "true");//se está indicando que deseas que el sistema de correo registre información detallada sobre lo que está sucediendo durante el proceso de envío de correo.
        // se agregar esta línea para confiar en todos los certificados SSL (solo para pruebas)
        props.put("mail.smtp.ssl.trust", "*"); //Esta línea agrega una propiedad adicional que confía en todos los certificados SSL.

        return mailSender;


    }
}
