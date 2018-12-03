package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.CambiarPlanAfiliadoAdapter;
import Excepciones.*;
import ModeloApi.CambiarPlanRequestWrapperDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/afiliado")
public class ModificarPlanAfiliadoController {

    private final CambiarPlanAfiliadoAdapter cambiarPlanAfiliadoAdapter;

    public ModificarPlanAfiliadoController(CambiarPlanAfiliadoAdapter cambiarPlanAfiliadoAdapter) {
        this.cambiarPlanAfiliadoAdapter = cambiarPlanAfiliadoAdapter;
    }

    @RequestMapping(value = "/modificarPlan", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> modiicarPlanAfiliado(@RequestBody @Valid CambiarPlanRequestWrapperDTO cambiarPlanRequestWrapper){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cambiarPlanAfiliadoAdapter.modificarPlanAfiliado(cambiarPlanRequestWrapper.afiliado, cambiarPlanRequestWrapper.plan));
        } catch (PlanIncompletoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan datos");
        } catch (DniConPuntosException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Formato dni incorrecto");
        } catch (PersonaIncompletaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan datos de personas");
        } catch (AfiliadoSinTitularException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado sin titular");
        } catch (NumeroAfiliadoIncorrectoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Numero de afiliado incorrecto");
        } catch (AfiliadoSinPlanException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado sin plan");
        } catch (PlanesIgualesException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Plan igual al que ya tenia");
        } catch (UpdateAfiliadoException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
