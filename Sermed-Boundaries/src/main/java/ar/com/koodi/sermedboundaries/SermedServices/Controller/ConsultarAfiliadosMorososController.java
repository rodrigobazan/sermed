package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ConsultarAfiliadosMorososAdapter;
import ModeloApi.AfiliadoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/sermed/afiliado/")
public class ConsultarAfiliadosMorososController {

    private ConsultarAfiliadosMorososAdapter consultarAfiliadosMorososAdapter;

    public ConsultarAfiliadosMorososController(ConsultarAfiliadosMorososAdapter consultarAfiliadosMorososAdapter) {
        this.consultarAfiliadosMorososAdapter = consultarAfiliadosMorososAdapter;
    }

    @RequestMapping(value = "/morosos/{fecha}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarAfiliadosMorosos(@PathVariable("fecha")String fecha){
        Collection<AfiliadoDTO> afiliados = new ArrayList<>();
        consultarAfiliadosMorososAdapter.consultarAfiliadosMorosos(LocalDate.parse(fecha)).forEach(afiliadoDTO -> afiliados.add(afiliadoDTO));
        if(afiliados.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(afiliados);
    }
}
