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

import Adaptadores.ConsultarEnfermeroAdapter;
import Excepciones.EnfermeroNoExisteException;
import ModeloApi.EnfermeroDTO;

@RestController
@RequestMapping("/sermed/")
public class ConsultarEnfermeroController {
	
	private final ConsultarEnfermeroAdapter consultarEnfermeroAdapter;

	public ConsultarEnfermeroController(ConsultarEnfermeroAdapter consultarEnfermeroAdapter) {
		super();
		this.consultarEnfermeroAdapter = consultarEnfermeroAdapter;
	}
	
	@RequestMapping(value = "/enfermeros", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarEnfermeros(){
		Collection<EnfermeroDTO> enfermeroDTOs= new ArrayList<>();
		consultarEnfermeroAdapter.consultarEnfermeros().forEach(enfermero->enfermeroDTOs.add(enfermero));
		if(enfermeroDTOs.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(enfermeroDTOs);
	}
	
	@RequestMapping(value = "/enfermeros/apellido/{apellido}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarEnfermerosPorApellido(@PathVariable("apellido")String apellido){
		Collection<EnfermeroDTO> enfermeroDTOs= new ArrayList<>();
		consultarEnfermeroAdapter.consultarEnfermerosPorApellido(apellido).forEach(enfermero->enfermeroDTOs.add(enfermero));
		if(enfermeroDTOs.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(enfermeroDTOs);
	}
	
	@RequestMapping(value = "/enfermero/matricula/{matricula}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarEnfermerosPorApellido(@PathVariable("matricula")Integer matricula){
		try {
			EnfermeroDTO dto = consultarEnfermeroAdapter.consultarEnfermeroPorMatricula(matricula);
			return ResponseEntity.status(HttpStatus.OK).body(dto);
		}
		catch (EnfermeroNoExisteException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		

	}
	

}
