package Modulo.Resultados.Controllers;


import Modulo.Resultados.Services.IMailService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/correo")
public class CorreoController {

    @Autowired
    private IMailService iMailService;


    @PostMapping("/enviarMensaje")
    public ResponseEntity<?> reciveRequestEmail(@RequestParam("Correo") @Schema(description = "Correo del aspirante", example = "dalnm@gmail.com") String[] usuario,@RequestParam("Asunto") String asunto,@RequestParam("Mensaje") String mensaje){

        iMailService.sendEmail(usuario,asunto,mensaje);

        Map<String, String> response = new HashMap<>(); //
        response.put("estado", "Enviado"); // la clave es estado y el valor enviado

        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/enviarMensajeConArchivo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> receiveRequestEmailFile(@RequestParam("Correo") @Schema(description = "Correo del aspirante", example = "dalnm@gmail.com")String[] usuario, @RequestParam("Asunto") String asunto, @RequestParam("Mensaje") String mensaje, @RequestParam("Archivo") MultipartFile archivo ){

        try{
            String filename=archivo.getOriginalFilename(); //Obtiene el nombre original del archivo que se adjuntará al correo electrónico a partir del objeto
            Path path= Paths.get("src/main/resources/File"+filename); //Crea un objeto Path que representa la ubicación donde se guardará el archivo
            Files.createDirectories(path.getParent()); //Se asegura de que el directorio que contiene el archivo exista. Si no existe, crea los directorios necesarios.
            Files.copy(archivo.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING); //Copia el contenido del archivo adjunto del objeto emailFileDto al archivo en la ubicación especificada. Se utiliza StandardCopyOption.REPLACE_EXISTING para reemplazar el archivo si ya existe.
            File file=path.toFile(); //Convierte el objeto Path a un objeto File

            iMailService.sendEmailWithFile(usuario,asunto,mensaje,file);


            Map<String, String> response = new HashMap<>();
            response.put("estado", "Enviado");
            response.put("archivo", filename);

            return ResponseEntity.ok(response);
        }catch (Exception e){
            throw  new RuntimeException("error"+e.getMessage());

        }

    }
}
