package Modulo.Resultados.Services;



import Modulo.Resultados.Entity.Cohorte;
import Modulo.Resultados.Entity.Estudiante;
import Modulo.Resultados.Repositories.ICohorteRepository;
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


    public void creacionDeCohorte(Long idEstudiante, String cohorte) {
        // Crear una instancia de Cohorte
        Cohorte cohorteObjeto = new Cohorte();
        // Establecer los valores del cohorte
        cohorteObjeto.setCohorte(cohorte);
        cohorteRepository.save(cohorteObjeto);

        // Buscar el estudiante en el repositorio por su ID
        Optional<Estudiante> estudianteEncontrado = estudianteRepository.findById(idEstudiante);

        // Verificar si el estudiante se encuentra en la base de datos
        if (estudianteEncontrado.isPresent()) {
            // Asignar la cohorte al estudiante y guardar el cambio
            Estudiante estudianteActualizado = estudianteEncontrado.get();
            estudianteActualizado.setCohorte(cohorteObjeto);
            estudianteRepository.save(estudianteActualizado);
        } else {
            throw new IllegalArgumentException("El ID del estudiante no existe en la base de datos.");
        }
    }





}