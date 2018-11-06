package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ConsultarSangreAdapter;
import ModeloApi.SangreDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/sermed/")
public class ConsultarSangreController {

    private ConsultarSangreAdapter consultarSangreAdapter;

    public ConsultarSangreController(ConsultarSangreAdapter consultarSangreAdapter) {
        this.consultarSangreAdapter = consultarSangreAdapter;
    }

    @RequestMapping(value = "/sangre", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarSangre(){
        Collection<SangreDTO> sangreDTOS = new ArrayList<>();
        consultarSangreAdapter.consultarSangre().forEach(sangreDTO -> sangreDTOS.add(sangreDTO));
        if(sangreDTOS.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(sangreDTOS);
    }

    @RequestMapping(value = "/sangre/grupo/{grupo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarSangrePorGrupo(@PathVariable("grupo")String grupo){
        Collection<SangreDTO> sangreDTOS = new ArrayList<>();
        consultarSangreAdapter.consultarSangrePorGrupo(grupo).forEach(sangreDTO -> sangreDTOS.add(sangreDTO));
        if(sangreDTOS.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(sangreDTOS);
    }

    @RequestMapping(value = "/sangre/factor/{factor}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarSangrePorFactor(@PathVariable("factor")String factor){
        Collection<SangreDTO> sangreDTOS = new ArrayList<>();
        consultarSangreAdapter.consultarSangrePorFactor(factor).forEach(sangreDTO -> sangreDTOS.add(sangreDTO));
        if(sangreDTOS.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(sangreDTOS);
    }

    @RequestMapping(value = "/sangre/factor/{factor}/grupo/{grupo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarSangrePorFactor(@PathVariable("factor")String factor, @PathVariable("grupo")String grupo){
        try {
            SangreDTO sangreDTOS = consultarSangreAdapter.consultarSangrePorGrupoFactor(grupo,factor);
            return ResponseEntity.status(HttpStatus.OK).body(sangreDTOS);
        }catch (Exception s){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }










}
