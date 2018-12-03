package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ModificarPlanAdapter;
import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Excepciones.UpdatePlanException;
import ModeloApi.PlanDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/plan")
public class ModificarPlanController {

    private final ModificarPlanAdapter modificarPlanAdapter;

    public ModificarPlanController(ModificarPlanAdapter modificarPlanAdapter) {
        this.modificarPlanAdapter = modificarPlanAdapter;
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> modificarPlan(@RequestBody @Valid PlanDTO plan){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(modificarPlanAdapter.modificarPlan(plan));
        } catch (PlanExisteException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Plan ya existe");
        } catch (PlanIncompletoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan Datos");
        } catch (UpdatePlanException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
