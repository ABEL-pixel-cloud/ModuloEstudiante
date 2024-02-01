package Modulo.Resultados.Repositories;


import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDocumentacionRepository extends JpaRepository<Documentacion, UUID> {

    Optional<Documentacion> findByAspirante(Aspirante aspirante);
    Optional<Documentacion> findByAspiranteAndEstadoDocumentosTrue(Aspirante aspirante);


}
