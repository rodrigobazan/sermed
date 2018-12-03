package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.CrearAfiliadoAdapter;
import Excepciones.*;
import ModeloApi.AfiliadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/afiliado")
public class CrearAfiliadoController {

    private final CrearAfiliadoAdapter crearAfiliadoAdapter;

    public CrearAfiliadoController(CrearAfiliadoAdapter crearAfiliadoAdapter) {
        this.crearAfiliadoAdapter = crearAfiliadoAdapter;
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> crearAfiliado(@RequestBody @Valid AfiliadoDTO afiliado){
        try {
            boolean resultado = crearAfiliadoAdapter.crearAfiliado(afiliado);
            if(resultado) return ResponseEntity.status(HttpStatus.OK).body(true);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (PersonaIncompletaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan datos de personas");
        } catch (PlanIncompletoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan datos de plan");
        } catch (AfiliadoSinPlanException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado sin plan");
        } catch (NumeroAfiliadoIncorrectoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Numero de afiliado incorrecto");
        } catch (AfiliadoSinTitularException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado sin titular");
        } catch (DniConPuntosException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Formato dni incorrecto");
        } catch (AfiliadoExisteException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado ya existe");
        } catch (TitularEnAfiliadoActivoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Titular ya tiene afiliado activo");
        }
    }
}
