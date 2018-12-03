package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ModificarPersonaAdapter;
import Excepciones.*;
import ModeloApi.PersonaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/persona")
public class ModificarPersonaController {

    private final ModificarPersonaAdapter modificarPersonaAdapter;

    public ModificarPersonaController(ModificarPersonaAdapter modificarPersonaAdapter) {
        this.modificarPersonaAdapter = modificarPersonaAdapter;
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> modificarPersona(@RequestBody @Valid PersonaDTO persona){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(modificarPersonaAdapter.modificarPersona(persona));
        } catch (PersonaIncompletaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan Datos");
        } catch (PersonaExisteException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Persona ya existe");
        } catch (UpdatePersonaException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (DniConPuntosException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Formato de DNI incorrecto");
        } catch (NumeroAfiliadoIncorrectoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Numero afiliado incorrecto");
        }
    }
}
