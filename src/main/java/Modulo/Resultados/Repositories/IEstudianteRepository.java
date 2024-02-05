package Modulo.Resultados.Repositories;


import Modulo.Resultados.Entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEstudianteRepository extends JpaRepository<Estudiante,Long> {
    @Query
    Optional<Estudiante> findByidEstudiante(Long estudiante);
}