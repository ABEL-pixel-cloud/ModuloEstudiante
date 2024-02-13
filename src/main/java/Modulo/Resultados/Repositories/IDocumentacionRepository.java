package Modulo.Resultados.Repositories;


import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Entity.Documentacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IDocumentacionRepository extends JpaRepository<Documentacion, UUID> {
    @Query
    Optional<Documentacion> findByAspirante(Aspirante aspirante);
    @Query
    Optional<Documentacion> findByAspiranteAndEstadoDocumentosTrue(Aspirante aspirante);
    @Query
    Optional<Documentacion> findByAspirante_Idaspirante(Long idAspirante);


}
