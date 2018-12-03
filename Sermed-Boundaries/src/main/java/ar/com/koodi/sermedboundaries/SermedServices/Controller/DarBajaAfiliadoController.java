package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.DarBajaAfiliadoAdapter;
import Excepciones.*;
import ModeloApi.AfiliadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController
@RequestMapping("/afiliado")
public class DarBajaAfiliadoController {

    private DarBajaAfiliadoAdapter darBajaAfiliadoAdapter;

    public DarBajaAfiliadoController(DarBajaAfiliadoAdapter darBajaAfiliadoAdapter) {
        this.darBajaAfiliadoAdapter = darBajaAfiliadoAdapter;
    }

    @RequestMapping(value = "/darBaja/{fecha}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> darBajaAfiliado(@RequestBody @Valid AfiliadoDTO afiliado, @PathVariable("fecha")String fecha){
        try {
            LocalDate fechaParseada = LocalDate.parse(fecha);
            return ResponseEntity.status(HttpStatus.OK).body(darBajaAfiliadoAdapter.darBajaAfiliado(afiliado, fechaParseada));
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
        } catch (AfiliadoDeBajaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado ya inactivo");
        }
    }
}
