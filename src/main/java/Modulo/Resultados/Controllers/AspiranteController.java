package Modulo.Resultados.Controllers;



import Modulo.Resultados.Dtos.CrearAspiranteDto;
import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Services.CrearAspiranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/aspirante")
public class AspiranteController {

    private CrearAspiranteService service;

    @Autowired
    public AspiranteController(CrearAspiranteService service) {
        this.service = service;
    }

    @PostMapping
    public Aspirante crearAspirante(@RequestBody CrearAspiranteDto dto){
        return this.service.Crear(dto);

    }


    @GetMapping
    public List<Aspirante> ListarUsuarios(){
        return this.service.listar();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable Long id){
        return this.service.eliminar(id);
    }
}
