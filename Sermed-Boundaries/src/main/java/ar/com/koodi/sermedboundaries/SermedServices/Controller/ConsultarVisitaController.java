package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

import Adaptadores.ConsultarVisitaAdapter;
import Excepciones.VisitaNoExisteException;
import ModeloApi.VisitaDTO;

@RestController
@RequestMapping("/sermed/")
public class ConsultarVisitaController {

	private ConsultarVisitaAdapter consultarVisitaAdapter;

	public ConsultarVisitaController(ConsultarVisitaAdapter consultarVisitaAdapter) {
		super();
		this.consultarVisitaAdapter = consultarVisitaAdapter;
	}
	
	@RequestMapping(value = "visitas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<?> consultarVisitas(){
		Collection<VisitaDTO> visitaDTOs = new ArrayList<>();
		consultarVisitaAdapter.consultarVisitas().forEach(visita->visitaDTOs.add(visita));
		if(visitaDTOs.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(visitaDTOs);
	}
	
	@RequestMapping(value = "visitas/fechadesde/{fechadesde}/fechahasta/{fechahasta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<?> consultarVisitasEntreFechas(@PathVariable("fechadesde")@DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate fechadesde, @PathVariable("fechahasta")@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechahasta){
		Collection<VisitaDTO> visitaDTOs = new ArrayList<>();
		consultarVisitaAdapter.consultarVisitasEntreFechas(fechadesde, fechahasta).forEach(visita->visitaDTOs.add(visita));
		if(visitaDTOs.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(visitaDTOs);
	}
	
	@RequestMapping(value = "visita/numero/{numero}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public ResponseEntity<?> consultarVisitaPorNumero(@PathVariable("numero")int numero){
		try {
			VisitaDTO visitaDTO = consultarVisitaAdapter.consultarVisitaPorNumero(numero);			
			return ResponseEntity.status(HttpStatus.OK).body(visitaDTO);	
		}
		catch (VisitaNoExisteException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}		
	}
}
