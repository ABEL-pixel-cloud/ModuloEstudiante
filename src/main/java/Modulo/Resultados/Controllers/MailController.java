package Modulo.Resultados.Controllers;


import Modulo.Resultados.Dtos.EmailDto;
import Modulo.Resultados.Dtos.EmailFileDto;
import Modulo.Resultados.Services.IMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v1")
public class MailController {

    @Autowired
    private IMailService iMailService;

    @PostMapping("/sendMessage")
    public ResponseEntity<?> reciveRequestEmail(@RequestBody EmailDto emailDto){

        System.out.println("Mensaje Recibido " + emailDto);

        iMailService.sendEmail(emailDto.getToUser(),emailDto.getSubject(),emailDto.getMessage());

        Map<String, String> response = new HashMap<>(); //
        response.put("estado", "Enviado"); // la clave es estado y el valor enviado

        return ResponseEntity.ok(response);
    }


    @PostMapping("/sendMessageFile")
    public ResponseEntity<?> receiveRequestEmailFile(@ModelAttribute EmailFileDto emailFileDto){

        try{
            String filename=emailFileDto.getFile().getOriginalFilename(); //Obtiene el nombre original del archivo que se adjuntará al correo electrónico a partir del objeto
            Path path= Paths.get("src/main/resources/File"+filename); //Crea un objeto Path que representa la ubicación donde se guardará el archivo
            Files.createDirectories(path.getParent()); //Se asegura de que el directorio que contiene el archivo exista. Si no existe, crea los directorios necesarios.
            Files.copy(emailFileDto.getFile().getInputStream(),path, StandardCopyOption.REPLACE_EXISTING); //Copia el contenido del archivo adjunto del objeto emailFileDto al archivo en la ubicación especificada. Se utiliza StandardCopyOption.REPLACE_EXISTING para reemplazar el archivo si ya existe.
            File file=path.toFile(); //Convierte el objeto Path a un objeto File

            iMailService.sendEmailWithFile(emailFileDto.getToUser(),emailFileDto.getSubject(),emailFileDto.getMessage(),file);


            Map<String, String> response = new HashMap<>();
            response.put("estado", "Enviado");
            response.put("archivo", filename);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw  new RuntimeException("error"+e.getMessage());

        }

    }
}
