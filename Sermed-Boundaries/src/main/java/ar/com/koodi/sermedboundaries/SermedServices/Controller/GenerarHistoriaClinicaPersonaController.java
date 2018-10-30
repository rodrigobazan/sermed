package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.GenerarHistoriaClinicaPersonaAdapter;
import Excepciones.PersonaNoExisteException;
import ModeloApi.HistoriaClinicaReporteDTO;

@RestController
@RequestMapping("/sermed/")
public class GenerarHistoriaClinicaPersonaController {

	private GenerarHistoriaClinicaPersonaAdapter generarHistoriaClinicaPersonaAdapter;

	public GenerarHistoriaClinicaPersonaController(
			GenerarHistoriaClinicaPersonaAdapter generarHistoriaClinicaPersonaAdapter) {
		super();
		this.generarHistoriaClinicaPersonaAdapter = generarHistoriaClinicaPersonaAdapter;
	}

	@RequestMapping(value = "historiaclinica/persona/numeroafiliado/{numeroafiliado}/numeroorden/{numeroorden}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> generarHistoriaClinicaPersona(@PathVariable("numeroafiliado") String numeroafiliado,
			@PathVariable("numeroorden") int numeroorden) {
		HistoriaClinicaReporteDTO historiaClinicaReporteDTO = null;
		try {
			historiaClinicaReporteDTO = generarHistoriaClinicaPersonaAdapter
					.generarHistoriaClinicaPersona(numeroafiliado, numeroorden);
			return ResponseEntity.status(HttpStatus.OK).body(historiaClinicaReporteDTO);
		} catch (PersonaNoExisteException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Persona no existe");
		}
		
	}

}
