package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.BuscarPersonaEntreAfiliadosAdapter;
import Adaptadores.ConsultarPersonaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import ModeloApi.PersonaDTO;

@RestController
@RequestMapping("/sermed/")
public class BuscarPersonaEntreAfiliadosController {

	private BuscarPersonaEntreAfiliadosAdapter buscarPersonaEntreAfiliadosAdapter;
	
	private ConsultarPersonaAdapter consultarPersonaAdapter;

	public BuscarPersonaEntreAfiliadosController(BuscarPersonaEntreAfiliadosAdapter buscarPersonaEntreAfiliadosAdapter,
			ConsultarPersonaAdapter consultarPersonaAdapter) {
		super();
		this.buscarPersonaEntreAfiliadosAdapter = buscarPersonaEntreAfiliadosAdapter;
		this.consultarPersonaAdapter = consultarPersonaAdapter;
	}
	
	
	@RequestMapping(value = "/persona/afiliados/documento/{documento}/tipodocumento/{tipodocumento}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> buscarPersonaEntreAfiliados(@PathVariable("documento")String documento, @PathVariable("tipodocumento")String tipodocumento){		
		PersonaDTO personaDTO = consultarPersonaAdapter.consultarPersonas().stream().filter(persona->persona.documento.equals(documento) && persona.tipoDocumento.nombre.equals(tipodocumento)).findAny().orElse(null);
		try {
			boolean resultado = buscarPersonaEntreAfiliadosAdapter.existePersonaEntreAfiliados(personaDTO);
			if(resultado)return ResponseEntity.status(HttpStatus.OK).body(personaDTO);
			else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (PersonaIncompletaException | DniConPuntosException | NumeroAfiliadoIncorrectoException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
		}
		
	}
	
	
}
