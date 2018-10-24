package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.CrearEnfermeroAdapter;
import Excepciones.EnfermeroExisteException;
import ModeloApi.EnfermeroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sermed/enfermero")
public class CrearEnfermeroController {

    private final CrearEnfermeroAdapter crearEnfermeroAdapter;

    public CrearEnfermeroController(CrearEnfermeroAdapter crearEnfermeroAdapter) {
        this.crearEnfermeroAdapter = crearEnfermeroAdapter;
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> crearEnfermero(@RequestBody @Valid EnfermeroDTO enfermero){
        try {
            boolean resultado = crearEnfermeroAdapter.crearEnfermero(enfermero);
            if(resultado) return ResponseEntity.status(HttpStatus.OK).body(true);
            else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (EnfermeroExisteException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Enfermero ya existe");
        }
    }
}
