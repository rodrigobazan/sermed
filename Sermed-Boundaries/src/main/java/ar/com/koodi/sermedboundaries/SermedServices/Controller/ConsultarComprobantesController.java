package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ConsultarComprobantesAdapter;
import Excepciones.ComprobanteNoExisteException;
import Excepciones.FechaIncorrectaException;
import ModeloApi.ComprobanteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/sermed/")
public class ConsultarComprobantesController {
    private ConsultarComprobantesAdapter consultarComprobantesAdapter;

    public ConsultarComprobantesController(ConsultarComprobantesAdapter consultarComprobantesAdapter) {
        this.consultarComprobantesAdapter = consultarComprobantesAdapter;
    }
    @RequestMapping(value = "/comprobantes", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarComprobantes() {
        Collection<ComprobanteDTO> comprobanteDTOS = new ArrayList<>();
        consultarComprobantesAdapter.consultarComprobantes().forEach(comprobanteDTO -> comprobanteDTOS.add(comprobanteDTO));
        if (comprobanteDTOS.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(comprobanteDTOS);
    }

    @RequestMapping(value = "/comprobantes/numero/{numero}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarComprobantesPorNumero(@PathVariable("numero")String numero)  {
        ComprobanteDTO comprobanteDTOS = null;
        try {
            comprobanteDTOS = consultarComprobantesAdapter.consultarComprobantePorNumero(numero);
            return ResponseEntity.status(HttpStatus.OK).body(comprobanteDTOS);
        } catch (ComprobanteNoExisteException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/comprobantes/fechas/{desde}/{hasta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarComprobantesPorFechas(@PathVariable("desde")String desde, @PathVariable("hasta")String hasta) throws FechaIncorrectaException {
        Collection<ComprobanteDTO> comprobanteDTOS = new ArrayList<>();
        consultarComprobantesAdapter.consultarComprobantesPorFechas(LocalDate.parse(desde),LocalDate.parse(hasta)).forEach(comprobanteDTO -> comprobanteDTOS.add(comprobanteDTO));
        if (comprobanteDTOS.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(comprobanteDTOS);
    }


}
