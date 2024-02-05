package Modulo.Resultados.Consumer;


import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Repositories.IAspiranteRepository;
import Modulo.Resultados.Services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Optional;


@Component
public class Consumer {

    EmailService service;

    IAspiranteRepository aspiranteRepository;
    @Autowired
    public Consumer(EmailService service, IAspiranteRepository aspiranteRepository) {
        this.service = service;
        this.aspiranteRepository = aspiranteRepository;
    }

    @RabbitListener(queues = {"correo"})
    public void recive(@Payload String correo) {
        System.out.println("correo del aspirante" + correo);

        Optional<Aspirante> aspirante = aspiranteRepository.findByCorreo(correo);

        if (aspirante.isPresent()) {
            Aspirante aspirante1 = aspirante.get();

            String nombre = aspirante1.getNombresCompletos();

            String entrenamiento = aspirante1.getPrograma();

            // Asunto
            String subject = " ¡Estás a un paso de empezar tu entrenamiento!";


            // Contenido HTML del correo

            String message = "<html><body>" +

                    "<p style='font-size: 14px;'> Hola  "+ "<b>" + nombre +"</b>" + "<br>" + "<br>" + "<br>" +
                    "Para <b>MAKAIA</b> es un gusto notificarte  que tu beca para el entrenamiento en  "+ "<b>"+  entrenamiento  +"</b>" + "  ha sido <b>APROBADA</b>  y pronto" + "<br>" +
                    " comenzaremos esta aventura que transformará tu vida y te conectará con oportunidades laborales en sector de la tecnología." + "<br>"+ "<br>" +

                    "Para legalizar tu beca y formalizar nuestro compromiso contigo y tu entrenamiento, es necesario que nos envíes el documento adjunto" + "<br>" +
                    " diligenciado y firmado." +
                    "<br>" +

                    "<div style=\"text-align: center;\"  >" +
                    "<h4 style='font-size: 14px;'>¿Qué encontrarás en el acta de compromiso adjunta?</h4><br>"+
                    "</div>"+

                    "<ul style='font-size: 14px;'>"+
                    "<li><b>Acta de compromiso:</b> Tú como becario debes diligenciar todos los datos señalados en color gris y agregar tu firma al final en" + "<br>" +
                    "la opción <b>firma participante</b>, si eres <b>menor de edad</b> tu representante también debe firmar y completar la información.</li>" + "<br>" + "<br>" +

                    "<li><b>Formato requisitos de información:</b> Diligenciar la información solicitada y adjuntar tú fotocopia o foto de la cédula por" + "<br>" +
                    "ambos lados..</li> " +
                    "</ul>"+

                    "<h5 style='font-size: 14px;'> <b>Nota</b> </h5> " +
                    "<ul style='font-size: 14px;'>"+
                    "<li>Para editar y diligenciar el PDF puedes hacer uso de la siguiente herramienta:" +"<br>"+
                    "   https://www.sejda.com/es/  </li>" +"<br>"+"<br>"+

                    " <li>Es importante que la firma sea digital, es decir puedes escribir la firma en una hoja, tomarle foto y adjuntarla como imagen al documento" +"<br>"+
                    " según corresponda.</li>" +"<br>"+"<br>"+
                    "</ul>"+

                    "<p style='font-size: 14px;'> Quedamos muy atentos de tu respuesta y de nuevo <b>FELICITACIONES y BIENVENIDO (A)</b> al Bootcamp de MAKAIA." +"<br>"+"<br"+
                    "Cordialmente," +"</p>"+"<br>"+"<br>"+

                    "<img src='resources/File/makaia.png' alt='Logo de MAKAIA'>" + "<br>" + "<br>"+
                    "</p>" +
                    "</p>"+

                    "</body></html>";


            // se proporciona la ruta del archivo que deseas adjuntar al correo
            String filePath = "D:\\Desktop\\1\\modulo_resultados\\Resultados\\src\\main\\resources\\File\\ACTADE COMPROMISO.pdf";
            File file = new File(filePath);

            // se  Verifica si el archivo existe antes de intentar adjuntarlo
            if (file.exists()) {
                service.sendEmailWithFile(new String[]{correo}, subject, message, file);
            } else {
                System.out.println("El archivo no existe en la ruta especificada.");
            }



        } else {
            System.out.println("el aspirante no esta en la base de datos");
        }


    }
}
