package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.BuscarPersonaEntreAfiliadosDeBajaAdapter;
import Adaptadores.ConsultarPersonaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import ModeloApi.PersonaDTO;

@RestController
@RequestMapping("/sermed/")
public class BuscarPersonaEntreAfiliadosDeBajaController {

	private BuscarPersonaEntreAfiliadosDeBajaAdapter buscarPersonaEntreAfiliadosDeBajaAdapter;

	private ConsultarPersonaAdapter consultarPersonaAdapter;

	public BuscarPersonaEntreAfiliadosDeBajaController(
			BuscarPersonaEntreAfiliadosDeBajaAdapter buscarPersonaEntreAfiliadosDeBajaAdapter,
			ConsultarPersonaAdapter consultarPersonaAdapter) {
		super();
		this.buscarPersonaEntreAfiliadosDeBajaAdapter = buscarPersonaEntreAfiliadosDeBajaAdapter;
		this.consultarPersonaAdapter = consultarPersonaAdapter;
	}

	@RequestMapping(value = "/persona/afiliados/baja/documento/{documento}/tipodocumento/{tipodocumento}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> buscarPersonaEntreAfiliadosDeBaja(@PathVariable("documento") String documento,
			@PathVariable("tipodocumento") String tipodocumento) {
		PersonaDTO personaDTO = consultarPersonaAdapter.consultarPersonas().stream().filter(persona->persona.documento.equals(documento) && persona.tipoDocumento.nombre.equals(tipodocumento)).findAny().orElse(null);
		try {
			boolean resultado = buscarPersonaEntreAfiliadosDeBajaAdapter.existePersonaPorDNI(personaDTO);
			if(resultado) return ResponseEntity.status(HttpStatus.OK).body(personaDTO);
			else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (PersonaIncompletaException | NumeroAfiliadoIncorrectoException | DniConPuntosException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
		}
		
	}

}
