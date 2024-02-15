package Modulo.Resultados.Services;



import Modulo.Resultados.Dtos.CrearAspiranteDto;
import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Exceptions.ExceptionHandlers;
import Modulo.Resultados.Exceptions.ResultadosApiException;
/*import Modulo.Resultados.Publisher.Publisher;

 */
import Modulo.Resultados.Repositories.IAspiranteRepository;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;


@Service
public class CrearAspiranteService {

    IAspiranteRepository aspiranteRepository;

    /*Publisher publisher;

     */
    EmailService service;

    IDocumentacionRepository documentacionRepository;


    @Autowired
    public CrearAspiranteService(IAspiranteRepository aspiranteRepository, /*Publisher publisher*/
                                 IDocumentacionRepository documentacionRespository,EmailService service) {
        this.aspiranteRepository = aspiranteRepository;
        /*this.publisher = publisher;*/
        this.documentacionRepository = documentacionRespository;
        this.service=service;


    }

    // se implementa este metodo para crear un Aspirante

    public ResponseEntity<String> Crear(CrearAspiranteDto dto) {
        Optional<Aspirante> aspiranteExistente = aspiranteRepository.findByCorreo(dto.getCorreo());

        if (aspiranteExistente.isPresent()) {
            // Si ya existe un aspirante con el mismo correo, generar una excepción
            return ResponseEntity.ok("aspirante ya existe");
        }


        // se crea el Aspirante
        Aspirante nuevoAspirante = new Aspirante(dto.getTipo_De_Documento(),
                dto.getDocumento(), dto.getNombres_Completos(), dto.getTelefono(),
                dto.getCorreo(), dto.getTipo_De_Perfil(), dto.getEstado_De_Proceso(),
                dto.getResultado_Prueba_Gorilla(), dto.getLink_De_Prueba(), dto.getAdmitido(),
                dto.getFinanciador(), dto.getPrograma(), dto.getObservacion());

        if (nuevoAspirante.getCorreo() != null) {
            // se guarda el Aspirante
            aspiranteRepository.save(nuevoAspirante);
            /*// se manda el correo por defecto al crear el aspirante
            crearEmailPorDefecto(nuevoAspirante.getCorreo());

             */



                String correoAspirante = nuevoAspirante.getCorreo();

                String nombre = nuevoAspirante.getNombresCompletos();

                String entrenamiento = nuevoAspirante.getPrograma();

                // Asunto
                String subject = " ¡Estás a un paso de empezar tu entrenamiento!";


                // Contenido HTML del correo

                String message = "<html><body>" +

                        "<p style='font-size: 14px;'> Hola  " + "<b>" + nombre + "</b>" + "<br>" + "<br>" + "<br>" +
                        "Para <b>BETEK</b> es un gusto notificarte  que tu beca para el entrenamiento en  " + "<b>" + entrenamiento + "</b>" + "  ha sido <b>APROBADA</b>  y pronto" + "<br>" +
                        " comenzaremos esta aventura que transformará tu vida y te conectará con oportunidades laborales en sector de la tecnología." + "<br>" + "<br>" +

                        "Para legalizar tu beca y formalizar nuestro compromiso contigo y tu entrenamiento, es necesario que nos envíes el documento adjunto" + "<br>" +
                        " diligenciado y firmado." +
                        "<br>" +

                        "<div style=\"text-align: center;\"  >" +
                        "<h4 style='font-size: 14px;'>¿Qué encontrarás en el acta de compromiso adjunta?</h4><br>" +
                        "</div>" +

                        "<ul style='font-size: 14px;'>" +
                        "<li><b>Acta de compromiso:</b> Tú como becario debes diligenciar todos los datos señalados en color gris y agregar tu firma al final en" + "<br>" +
                        "la opción <b>firma participante</b>, si eres <b>menor de edad</b> tu representante también debe firmar y completar la información.</li>" + "<br>" + "<br>" +

                        "<li><b>Formato requisitos de información:</b> Diligenciar la información solicitada y adjuntar tú fotocopia o foto de la cédula por" + "<br>" +
                        "ambos lados..</li> " +
                        "</ul>" +

                        "<h5 style='font-size: 14px;'> <b>Nota</b> </h5> " +
                        "<ul style='font-size: 14px;'>" +
                        "<li>Para editar y diligenciar el PDF puedes hacer uso de la siguiente herramienta:" + "<br>" +
                        "   https://www.sejda.com/es/  </li>" + "<br>" + "<br>" +

                        " <li>Es importante que la firma sea digital, es decir puedes escribir la firma en una hoja, tomarle foto y adjuntarla como imagen al documento" + "<br>" +
                        " según corresponda.</li>" + "<br>" + "<br>" +
                        "</ul>" +

                        "<p style='font-size: 14px;'> Quedamos muy atentos de tu respuesta y de nuevo <b>FELICITACIONES y BIENVENIDO (A)</b> al Bootcamp de BETEK." + "<br>" + "<br" +
                        "Cordialmente," + "</p>" + "<br>" + "<br>" +
                        "</p>" +
                        "</p>" +

                        "</body></html>";


                // se proporciona la ruta del archivo que deseas adjuntar al correo
                String filePath = "ModuloEstudiante/src/main/resources/File/ACTADECOMPROMISO.pdf";
                File file = new File(filePath);

                // se  Verifica si el archivo existe antes de intentar adjuntarlo
                if (file.exists()) {
                    service.sendEmailWithFile(new String[]{correoAspirante}, subject, message, file);
                } else {
                    System.out.println("El archivo no existe en la ruta especificada.");
                }

        } else throw new IllegalArgumentException("La dirección de correo electrónico es nula o está vacía");

        return ResponseEntity.ok("se ha creado el aspirante");
    }
    /*

    // se implementa este metodo para mandar por defecto el correo al crear el Aspirante
    public void crearEmailPorDefecto(String email) {
        if (email != null) {
            this.publisher.sendAspirante(email);
        } else {
            System.err.println("Intento de enviar un correo nulo.");
        }
    }

     */

    // se crea este metodo para listar todos los Aspirantes
    public List<Aspirante> listar(){
        // de esta forma se en lista las publicaciones con crudrepository
        return StreamSupport.stream(this.aspiranteRepository.findAll().spliterator(),false)
                .toList();
    }


    // se crea este metodo para eliminar por id el Aspirante
    public ResponseEntity<String> eliminar(Long id) {

        Aspirante aspirante = this.aspiranteRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("el aspirante no existe"));

        // Obtener el ID del aspirante
        Long idAspiranteActual = aspirante.getIdaspirante();

        // Eliminar el aspirante de la base de datos
        aspiranteRepository.delete(aspirante);

        // Devolver una respuesta exitosa
        return ResponseEntity.ok("Aspirante eliminado correctamente");

    }



}
