package Modulo.Resultados.Services;



import Modulo.Resultados.Dtos.CrearAspiranteDto;
import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Publisher.Publisher;
import Modulo.Resultados.Repositories.IAspiranteRepository;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;


@Service
public class CrearAspiranteService {

    IAspiranteRepository aspiranteRepository;

    Publisher publisher;
    IDocumentacionRepository documentacionRepository;


    @Autowired
    public CrearAspiranteService(IAspiranteRepository aspiranteRepository, Publisher publisher,
                                 IDocumentacionRepository documentacionRespository){
        this.aspiranteRepository =aspiranteRepository;
        this.publisher=publisher;
        this.documentacionRepository =documentacionRespository;


    }

    // se implementa este metodo para crear un Aspirante

    public Aspirante Crear(CrearAspiranteDto dto){


        // se crea el Aspirante
        Aspirante nuevoAspirante = new Aspirante(dto.getTipo_De_Documento(),
                dto.getDocumento(), dto.getNombres_Completos(), dto.getTelefono(),
                dto.getCorreo(), dto.getTipo_De_Perfil(),dto.getEstado_De_Proceso(),
                dto.getResultado_Prueba_Gorilla(), dto.getLink_De_Prueba(),dto.getAdmitido(),
                dto.getFinanciador(), dto.getPrograma(), dto.getObservacion());

        if (nuevoAspirante.getCorreo() != null) {
            // se guarda el Aspirante
            aspiranteRepository.save(nuevoAspirante);
            // se manda el correo por defecto al crear el aspirante
            crearEmailPorDefecto(nuevoAspirante.getCorreo());
        } else {
            System.err.println("Intento de crear un Aspirante con correo nulo.");

        }

        return nuevoAspirante;
    }

    // se implementa este metodo para mandar por defecto el correo al crear el Aspirante
    public void crearEmailPorDefecto(String email) {
        if (email != null) {
            this.publisher.sendAspirante(email);
        } else {
            System.err.println("Intento de enviar un correo nulo.");
        }
    }

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
