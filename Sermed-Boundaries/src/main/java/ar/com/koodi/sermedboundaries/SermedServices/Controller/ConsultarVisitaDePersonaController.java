package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.ConsultarPersonaAdapter;
import Adaptadores.ConsultarVisitasDePersonaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Excepciones.PersonaNoExisteException;
import ModeloApi.PersonaDTO;
import ModeloApi.VisitaDTO;

@RestController
@RequestMapping("/sermed/")
public class ConsultarVisitaDePersonaController {

	private ConsultarPersonaAdapter consultarPersonaAdapter;

	private ConsultarVisitasDePersonaAdapter consultarVisitaAdapter;

	public ConsultarVisitaDePersonaController(ConsultarPersonaAdapter consultarPersonaAdapter,
			ConsultarVisitasDePersonaAdapter consultarVisitaAdapter) {
		super();
		this.consultarPersonaAdapter = consultarPersonaAdapter;
		this.consultarVisitaAdapter = consultarVisitaAdapter;
	}

	@RequestMapping(value = "/visitas/persona/{nroAfiliado}/{nroOrden}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> consultarVisitasDePersona(@PathVariable("nroAfiliado") String numeroAfiliado,
			@PathVariable("nroOrden") int nroOrden) {

		PersonaDTO persona;
		try {
			persona = consultarPersonaAdapter.consultarPersonaPorNumeroAfiliado(numeroAfiliado, nroOrden);
		} catch (PersonaNoExisteException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Persona no existe");
		}
		Collection<VisitaDTO> visitaDTOs = new ArrayList<>();
		try {
			consultarVisitaAdapter.consultarVisitasDePersona(persona).forEach(visita -> visitaDTOs.add(visita));
		} catch (PersonaIncompletaException | DniConPuntosException | NumeroAfiliadoIncorrectoException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Datos de Persona Incorrectos");
		}
		if (visitaDTOs.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(visitaDTOs);

	}
	
	
	@RequestMapping(value = "/visitas/persona/{nroAfiliado}/{nroOrden}/fechadesde/{fechadesde}/fechahasta/{fechahasta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> consultarVisitasDePersona(@PathVariable("nroAfiliado") String numeroAfiliado,
			@PathVariable("nroOrden") int nroOrden,
			@PathVariable("fechadesde") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate fechadesde, 
			@PathVariable("fechahasta")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechahasta){
		PersonaDTO persona;
		try {
			persona = consultarPersonaAdapter.consultarPersonaPorNumeroAfiliado(numeroAfiliado, nroOrden);
		} catch (PersonaNoExisteException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Persona no existe");
		}
		Collection<VisitaDTO> visitaDTOs = new ArrayList<>();
		try {
			consultarVisitaAdapter.consultarVisitasPersonaPorFechas(persona, fechadesde, fechahasta).forEach(visita -> visitaDTOs.add(visita));
		} catch (PersonaIncompletaException | DniConPuntosException | NumeroAfiliadoIncorrectoException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Datos de Persona Incorrectos");
		}
		if (visitaDTOs.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(visitaDTOs);

	}

}
