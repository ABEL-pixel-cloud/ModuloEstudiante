package Modulo.Resultados.Repositories;


import Modulo.Resultados.Entity.Cohorte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICohorteRepository extends JpaRepository<Cohorte,Long> {
}
