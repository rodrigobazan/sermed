package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.CrearComprobantePagoAdapter;
import Excepciones.*;
import ModeloApi.ComprobanteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sermed/comprobante")
public class CrearComprobanteDePagoController {

    private final CrearComprobantePagoAdapter crearComprobantePagoAdapter;

    public CrearComprobanteDePagoController(CrearComprobantePagoAdapter crearComprobantePagoAdapter) {
        this.crearComprobantePagoAdapter = crearComprobantePagoAdapter;
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> crearComprobante(@RequestBody @Valid ComprobanteDTO comprobante){
        try {
            boolean resultado = crearComprobantePagoAdapter.crearComprobantePago(comprobante);
            if(resultado) return ResponseEntity.status(HttpStatus.OK).body(true);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (PersonaIncompletaException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan datos de personas");
        } catch (PlanIncompletoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan datos de plan");
        } catch (DniConPuntosException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Formato dni incorrecto");
        } catch (NumeroAfiliadoIncorrectoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Numero de afiliado incorrecto");
        } catch (AfiliadoSinTitularException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado sin titular");
        } catch (AfiliadoSinPlanException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado sin plan");
        } catch (ComprobanteExisteException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Comprobante ya existe");
        }
    }
}
