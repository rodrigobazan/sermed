package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ConsultarTipoDocumentoAdapter;
import Excepciones.TipoDocumentoNoExisteException;
import ModeloApi.TipoDocumentoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;

@RestController
@RequestMapping("/sermed/")
public class ConsultarTipoDocumentoController {

    private final ConsultarTipoDocumentoAdapter consultarTipoDocumentoAdapter;

    public ConsultarTipoDocumentoController(ConsultarTipoDocumentoAdapter consultarTipoDocumentoAdapter) {
        this.consultarTipoDocumentoAdapter = consultarTipoDocumentoAdapter;
    }

    @RequestMapping(value = "/tipodocumento", method = RequestMethod.GET, produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarTipoDocumento(){
        Collection<TipoDocumentoDTO> tipoDocumentoDTOS = new ArrayList<>();
        consultarTipoDocumentoAdapter.consultarTiposDocumentos().forEach(tipoDocumentoDTO -> ((ArrayList<TipoDocumentoDTO>) tipoDocumentoDTOS).add(tipoDocumentoDTO));
        if(tipoDocumentoDTOS.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(tipoDocumentoDTOS);
    }

    @RequestMapping(value = "/tipodocumento/nombre/{nombre}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarTipoDocumentoPorNombre(@PathVariable("nombre") String nombre){
        Collection<TipoDocumentoDTO> tipoDocumentoDTOS = new ArrayList<>();
        consultarTipoDocumentoAdapter.consultarTiposDocumentoPorNombre(nombre).forEach(tipoDocumentoDTO -> ((ArrayList<TipoDocumentoDTO>) tipoDocumentoDTOS).add(tipoDocumentoDTO));
        if(tipoDocumentoDTOS.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.status(HttpStatus.OK).body(tipoDocumentoDTOS);
    }


    @RequestMapping(value = "/tipodocumento/nombreunico/{nombre}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarTipoDocumentoPorNombreUnico(@PathVariable("nombre") String nombre)  {
      try {
          TipoDocumentoDTO tipoDocumentoDTO = (consultarTipoDocumentoAdapter.consultarTiposDocumentoUnicoPorNombre(nombre));
          return ResponseEntity.status(HttpStatus.OK).body(tipoDocumentoDTO);
      } catch (TipoDocumentoNoExisteException e) {
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }


    }


}
