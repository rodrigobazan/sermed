package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.CrearObraSocialAdapter;
import Excepciones.ObraSocialExisteException;
import ModeloApi.ObraSocialDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sermed/obraSocial")
public class CrearObraSocialController {

    private final CrearObraSocialAdapter crearObraSocialAdapter;

    public CrearObraSocialController(CrearObraSocialAdapter crearObraSocialAdapter) {
        this.crearObraSocialAdapter = crearObraSocialAdapter;
    }

    @RequestMapping(value = "/nueva", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> crearObraSocial(@RequestBody @Valid ObraSocialDTO obraSocial) {
        try {
            boolean resultado = crearObraSocialAdapter.crearObraSocial(obraSocial);
            if (resultado) return ResponseEntity.status(HttpStatus.OK).body(true);
            else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ObraSocialExisteException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Obra social ya existe");
        }
    }
}
