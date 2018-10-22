package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ConsultarAfeccionAdapter;
import ModeloApi.AfeccionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;

@RestController
public class ConsultarAfeccionesController {

    private final ConsultarAfeccionAdapter consultarAfeccionAdapter;

    public ConsultarAfeccionesController(ConsultarAfeccionAdapter consultarAfeccionAdapter) {
        this.consultarAfeccionAdapter = consultarAfeccionAdapter;
    }

    @RequestMapping(value = "/sermed/afecciones", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarAfecciones(){
        Collection<AfeccionDTO> afeccionList = new ArrayList<>();
        consultarAfeccionAdapter.consultarAfecciones().forEach(afeccionDTO -> afeccionList.add(afeccionDTO));
        if(afeccionList.isEmpty()) return new ResponseEntity<>(HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(afeccionList);
    }
}
