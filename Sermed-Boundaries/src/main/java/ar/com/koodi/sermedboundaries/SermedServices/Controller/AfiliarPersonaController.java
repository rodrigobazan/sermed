package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.AfiliarPersonaAdapter;
import Excepciones.*;
import ModeloApi.AfiliarPersonaRequestWrapperDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/afiliado")
public class AfiliarPersonaController {

    private final AfiliarPersonaAdapter afiliarPersonaAdapter;

    public AfiliarPersonaController(AfiliarPersonaAdapter afiliarPersonaAdapter) {
        this.afiliarPersonaAdapter = afiliarPersonaAdapter;
    }

    @RequestMapping(value = "/nuevoMiembro", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> afiliarPersona(@RequestBody @Valid AfiliarPersonaRequestWrapperDTO afiliarPersona){
        try {
            boolean resultado = afiliarPersonaAdapter.afiliadPersona(afiliarPersona.persona, afiliarPersona.afiliado);
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
        } catch (PersonaAfiliadaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Persona ya afiliada");
        }

    }
}
