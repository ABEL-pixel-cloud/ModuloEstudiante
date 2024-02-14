package Modulo.Resultados.Controllers;



import Modulo.Resultados.Dtos.CrearAspiranteDto;
import Modulo.Resultados.Entity.Aspirante;
import Modulo.Resultados.Services.CrearAspiranteService;
import io.swagger.v3.oas.annotations.media.Schema;
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

    @PostMapping("/crearAspirante")
    public ResponseEntity<String> crearAspirante(@RequestBody CrearAspiranteDto dto){
        return this.service.Crear(dto);

    }


    @GetMapping("/listarAspirante")
    public List<Aspirante> ListarUsuarios(){
        return this.service.listar();
    }

    @DeleteMapping("/EliminarAspirante/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable @Schema(description = "agrega el numero del aspirante que deseas eliminar", example = "1 o 2 o 3 ") Long id){
        return this.service.eliminar(id);
    }
}
