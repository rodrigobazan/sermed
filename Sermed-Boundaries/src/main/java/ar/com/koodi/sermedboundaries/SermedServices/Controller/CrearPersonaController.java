package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.CrearPersonaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaExisteException;
import Excepciones.PersonaIncompletaException;
import ModeloApi.PersonaDTO;

@RestController
@RequestMapping("/sermed/persona")
public class CrearPersonaController {

	private CrearPersonaAdapter crearPersonaAdapter;

	public CrearPersonaController(CrearPersonaAdapter crearPersonaAdapter) {
		super();
		this.crearPersonaAdapter = crearPersonaAdapter;
	}
	
	@RequestMapping(value = "/nueva", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> crearPersona(@RequestBody @Valid PersonaDTO persona) throws PersonaIncompletaException, NumeroAfiliadoIncorrectoException, DniConPuntosException{
		try {
			boolean resultado = crearPersonaAdapter.crearPersona(persona);
			if(resultado)return ResponseEntity.status(HttpStatus.OK).body(true);
			else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (PersonaExisteException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Persona ya existe");
		}
	}
}
