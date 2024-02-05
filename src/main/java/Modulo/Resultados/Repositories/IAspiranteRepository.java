package Modulo.Resultados.Repositories;


import Modulo.Resultados.Entity.Aspirante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAspiranteRepository extends JpaRepository<Aspirante,Long> {
    @Query
    Optional<Aspirante> findByDocumento(Long documento);
    @Query
    Optional<Aspirante>  findByCorreo(String correo);
    @Query
    Optional<Aspirante>  findById(Long idaspirante);




}