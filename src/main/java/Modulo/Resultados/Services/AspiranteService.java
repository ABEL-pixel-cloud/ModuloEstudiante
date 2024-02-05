package Modulo.Resultados.Services;


import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Exceptions.ResultadosApiException;
import Modulo.Resultados.Repositories.IAspiranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AspiranteService {


    private IAspiranteRepository aspiranteRepository;

    @Autowired
    public AspiranteService(IAspiranteRepository aspiranteRepository) {
        this.aspiranteRepository = aspiranteRepository;
    }

    public Aspirante verificarAspirantesyValidarDocumentacion(Long cedulaAspirante) {

        Optional<Aspirante> aspiranteExiste=aspiranteRepository.findByDocumento(cedulaAspirante);


        if (aspiranteExiste.isPresent() ) {
            Aspirante aspirante=aspiranteExiste.get();
            return aspirante;
        } else {
            // El aspirante no existe en la base de datos
            throw new ResultadosApiException("No puedes enviar documentos, el aspirante no existe", HttpStatus.CONFLICT);
        }



    }

}