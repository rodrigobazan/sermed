package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ConsultarAfeccionAdapter;
import Excepciones.AfeccionNoExisteException;
import ModeloApi.AfeccionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/sermed/")
public class ConsultarAfeccionesController {

    private final ConsultarAfeccionAdapter consultarAfeccionAdapter;

    public ConsultarAfeccionesController(ConsultarAfeccionAdapter consultarAfeccionAdapter) {
        this.consultarAfeccionAdapter = consultarAfeccionAdapter;
    }

    @RequestMapping(value = "/afecciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarAfecciones() {
        Collection<AfeccionDTO> afeccionList = new ArrayList<>();
        consultarAfeccionAdapter.consultarAfecciones().forEach(afeccionDTO -> afeccionList.add(afeccionDTO));
        if (afeccionList.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(afeccionList);
    }

    @RequestMapping(value = "/afecciones/nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarAfeccionesPorNombre(@PathVariable("nombre") String nombre) {
        List<AfeccionDTO> afecciones = new ArrayList<>();
        consultarAfeccionAdapter.consultarAfeccionesPorNombre(nombre).forEach(afeccionDTO -> afecciones.add(afeccionDTO));
        if(afecciones.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(afecciones);
    }

    @RequestMapping(value = "/afeccion/nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarAfeccionPorNombre(@PathVariable("nombre") String nombre){
        try {
            AfeccionDTO afeccion = consultarAfeccionAdapter.consultarAfeccionPorNombre(nombre);
            return ResponseEntity.status(HttpStatus.OK).body(afeccion);
        } catch (AfeccionNoExisteException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
