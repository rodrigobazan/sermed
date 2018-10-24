package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ConsultarPlanAdapter;
import ModeloApi.PlanDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/sermed/")
public class ConsultarPlanController {

    private ConsultarPlanAdapter consultarPlanAdapter;

    public ConsultarPlanController(ConsultarPlanAdapter consultarPlanAdapter) {
        this.consultarPlanAdapter = consultarPlanAdapter;
    }

    @RequestMapping(value = "/plan", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarPlan(){
        Collection<PlanDTO> planDTOS = new ArrayList<>();
        consultarPlanAdapter.consultarPlanes().forEach(planDTO -> planDTOS.add(planDTO));
        if(planDTOS.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(planDTOS);
    }

    @RequestMapping(value = "/plan/nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarPlanPorNombre(@PathVariable("nombre")String nombre){
        Collection<PlanDTO> planDTOS = new ArrayList<>();
        consultarPlanAdapter.consultarPlanesPorNombre(nombre).forEach(planDTO -> planDTOS.add(planDTO));
        if(planDTOS.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(planDTOS);
    }



}
