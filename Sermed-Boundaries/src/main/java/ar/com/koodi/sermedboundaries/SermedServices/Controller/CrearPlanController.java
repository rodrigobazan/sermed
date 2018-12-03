package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.CrearPlanAdapter;
import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import ModeloApi.PlanDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/plan")
public class CrearPlanController {

    private final CrearPlanAdapter crearPlanAdapter;

    public CrearPlanController(CrearPlanAdapter crearPlanAdapter) {
        this.crearPlanAdapter = crearPlanAdapter;
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> crearNuevoPlan(@RequestBody @Valid PlanDTO plan){
        try {
            boolean resultado = crearPlanAdapter.crearPlan(plan);
            if(resultado) return ResponseEntity.status(HttpStatus.OK).body(true);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (PlanExisteException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Plan ya existe");
        } catch (PlanIncompletoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan datos");
        }
    }
}
