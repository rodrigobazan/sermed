package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ModificarEnfermeroAdapter;
import Excepciones.EnfermeroIncompletoException;
import Excepciones.MatriculasIgualesException;
import Excepciones.UpdateEnfermeroException;
import ModeloApi.EnfermeroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/enfermero")
public class ModificarEnfermeroController {

    private final ModificarEnfermeroAdapter modificarEnfermeroAdapter;

    public ModificarEnfermeroController(ModificarEnfermeroAdapter modificarEnfermeroAdapter) {
        this.modificarEnfermeroAdapter = modificarEnfermeroAdapter;
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> modificarEnfermero(@RequestBody @Valid EnfermeroDTO enfermero){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(modificarEnfermeroAdapter.modificarEnfermero(enfermero));
        } catch (MatriculasIgualesException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Matricula ya existe");
        } catch (EnfermeroIncompletoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan datos");
        } catch (UpdateEnfermeroException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
