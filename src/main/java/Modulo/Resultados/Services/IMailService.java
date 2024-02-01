package Modulo.Resultados.Services;

import java.io.File;

public interface IMailService {

    void sendEmail(String[] toUser, String subject,String message);

    void sendEmailWithFile(String[] toUser, String subject, String message, File file);

}
