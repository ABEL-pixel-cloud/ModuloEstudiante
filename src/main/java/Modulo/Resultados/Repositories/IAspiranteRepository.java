package Modulo.Resultados.Repositories;


import Modulo.Resultados.Entity.Aspirante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAspiranteRepository extends JpaRepository<Aspirante,Long> {

    Optional<Aspirante> findByDocumento(Long documento);

    Optional<Aspirante>  findByCorreo(String correo);
    Optional<Aspirante>  findById(Long idaspirante);




}