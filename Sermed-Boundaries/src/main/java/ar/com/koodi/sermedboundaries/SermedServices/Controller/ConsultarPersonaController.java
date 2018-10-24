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

import Adaptadores.ConsultarPersonaAdapter;
import Excepciones.PersonaNoExisteException;
import ModeloApi.PersonaDTO;

@RestController
@RequestMapping("/sermed/")
public class ConsultarPersonaController {
	
	private ConsultarPersonaAdapter consultarPersonaAdapter;

	public ConsultarPersonaController(ConsultarPersonaAdapter consultarPersonaAdapter) {
		super();
		this.consultarPersonaAdapter = consultarPersonaAdapter;
	}
	
	@RequestMapping(value="/personas", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>consultarPersonas(){
		Collection<PersonaDTO> personaDTOs = new ArrayList<>();
		consultarPersonaAdapter.consultarPersonas().forEach(persona->personaDTOs.add(persona));
		if(personaDTOs.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(personaDTOs);
	}

	@RequestMapping(value="/personas/apellido/{apellido}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>consultarPersonasPorApellido(@PathVariable("apellido")String apellido){
		Collection<PersonaDTO> personaDTOs = new ArrayList<>();
		consultarPersonaAdapter.consultarPersonasPorApellido(apellido).forEach(persona->personaDTOs.add(persona));
		if(personaDTOs.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(personaDTOs);
	}
	
	@RequestMapping(value="/persona/nroafiliado/{nroafiliado}/nroorden/{nroorden}", method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?>consultarPersonasPorNumeroAfiliado(@PathVariable("nroafiliado")String nroafiliado, @PathVariable("nroorden")int nroorden){
		try {
			PersonaDTO personaDTO =consultarPersonaAdapter.consultarPersonaPorNumeroAfiliado(nroafiliado, nroorden);
			return ResponseEntity.status(HttpStatus.OK).body(personaDTO);
		}
		catch (PersonaNoExisteException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}	
	}
}
