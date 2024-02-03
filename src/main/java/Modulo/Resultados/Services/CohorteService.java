package Modulo.Resultados.Services;



import Modulo.Resultados.Entity.Cohorte;
import Modulo.Resultados.Entity.Estudiante;
import Modulo.Resultados.Repositories.IAspiranteRepository;
import Modulo.Resultados.Repositories.ICohorteRepository;
import Modulo.Resultados.Repositories.IDocumentacionRepository;
import Modulo.Resultados.Repositories.IEstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CohorteService {


    private IEstudianteRepository estudianteRepository;

    private ICohorteRepository cohorteRepository;

    @Autowired
    public CohorteService(IEstudianteRepository estudianteRepository,
                          ICohorteRepository cohorteRepository) {
        this.estudianteRepository = estudianteRepository;
        this.cohorteRepository = cohorteRepository;
    }


    public void CreacionDeCohorte(List<Estudiante> estudiantes, String cohorte) {

        Cohorte cohorteObjeto = Cohorte.builder()
                .cohorte(cohorte)
                .build();


        cohorteRepository.save(cohorteObjeto);

        // Itera sobre la lista de estudiantes
        for (Estudiante estudiante : estudiantes) {
            // Obtén el ID del estudiante actual
            Long idEstudiante = estudiante.getIdEstudiante();

            // Busca el estudiante en el repositorio
            Optional<Estudiante> estudianteEncontrado = estudianteRepository.findById(idEstudiante);

            // Si el estudiante se encuentra en la base de datos
            if (estudianteEncontrado.isPresent()) {
                // Asigna la cohorte al estudiante y guarda el cambio
                Estudiante estudianteActualizado = estudianteEncontrado.get();
                estudianteActualizado.setCohorte(cohorteObjeto);
                estudianteRepository.save(estudianteActualizado);
            }
        }
    }

}
