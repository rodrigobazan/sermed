package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ModificarObraSocialAdapter;
import Excepciones.NombreObraSocialExisteException;
import Excepciones.UpdateObraSocialException;
import ModeloApi.ObraSocialDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/obraSocial")
public class ModificarObraSocialController {

    private final ModificarObraSocialAdapter modificarObraSocialAdapter;

    public ModificarObraSocialController(ModificarObraSocialAdapter modificarObraSocialAdapter) {
        this.modificarObraSocialAdapter = modificarObraSocialAdapter;
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> modificarObraSocial(@RequestBody @Valid ObraSocialDTO obraSocial){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(modificarObraSocialAdapter.modificarObraSocial(obraSocial));
        } catch (NombreObraSocialExisteException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Ya existe esta obra social");
        } catch (UpdateObraSocialException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
