package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.DesafiliarPersonaAdapter;
import Excepciones.*;
import ModeloApi.AfiliarPersonaRequestWrapperDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/afiliado")
public class DesafiliarPersonaController {

    private final DesafiliarPersonaAdapter desafiliarPersonaAdapter;

    public DesafiliarPersonaController(DesafiliarPersonaAdapter desafiliarPersonaAdapter) {
        this.desafiliarPersonaAdapter = desafiliarPersonaAdapter;
    }

    @RequestMapping(value = "/desafiliarPersona", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> desafiliarPersona(@RequestBody @Valid AfiliarPersonaRequestWrapperDTO afiliarPersonaRequestWrapper){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(desafiliarPersonaAdapter.desafiliarPersona(afiliarPersonaRequestWrapper.persona, afiliarPersonaRequestWrapper.afiliado));
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
        } catch (PersonaNoAfiliadaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Esta persona no es parte del afiliado");
        }
    }
}
