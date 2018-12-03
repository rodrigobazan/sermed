package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.ConsultarObrasSocialesAdapter;
import Excepciones.ObraSocialNoExisteException;
import ModeloApi.ObraSocialDTO;

@RestController
@RequestMapping("/")
public class ConsultarObrasSocialesController {

	private ConsultarObrasSocialesAdapter consultarObrasSocialesAdapter;

	public ConsultarObrasSocialesController(ConsultarObrasSocialesAdapter consultarObrasSocialesAdapter) {
		super();
		this.consultarObrasSocialesAdapter = consultarObrasSocialesAdapter;
	}

	@RequestMapping(value = "/obrassociales", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> consultarObrasSociales() {
		Collection<ObraSocialDTO> obraSocialDTOs = new ArrayList<>();
		consultarObrasSocialesAdapter.consultarObrasSociales().forEach(obrasocial -> obraSocialDTOs.add(obrasocial));
		if (obraSocialDTOs.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(obraSocialDTOs);
	}

	@RequestMapping(value = "/obrassociales/nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> consultarObrasSociales(@PathVariable("nombre") String nombre) {
		Collection<ObraSocialDTO> obraSocialDTOs = new ArrayList<>();
		consultarObrasSocialesAdapter.consultarObrasSocialesPorNombre(nombre)
				.forEach(obrasocial -> obraSocialDTOs.add(obrasocial));
		if (obraSocialDTOs.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(obraSocialDTOs);
	}

	@RequestMapping(value = "/obrasocial/nombre/{nombre}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarObraSocialPorNombre(@PathVariable("nombre")String nombre){
		try {
			ObraSocialDTO obraSocialDTO = consultarObrasSocialesAdapter.consultarObraSocialPorNombre(nombre);
			return ResponseEntity.status(HttpStatus.OK).body(obraSocialDTO);
		}
		catch (ObraSocialNoExisteException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

}
