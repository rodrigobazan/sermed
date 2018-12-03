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

import Adaptadores.ConsultarAfiliadoAdapter;
import Adaptadores.ConsultarComprobanteDeAfiliadoAdapter;
import Excepciones.AfiliadoNoExisteException;
import Excepciones.AfiliadoSinPlanException;
import Excepciones.AfiliadoSinTitularException;
import Excepciones.DniConPuntosException;
import Excepciones.FechaIncorrectaException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Excepciones.PlanIncompletoException;
import ModeloApi.AfiliadoDTO;
import ModeloApi.ComprobanteDTO;

@RestController
@RequestMapping("/")
public class ConsultarComprobantDeAfiliadoController {

	private ConsultarComprobanteDeAfiliadoAdapter consultarComprobanteDeAfiliadoAdapter;
	
	private ConsultarAfiliadoAdapter consultarAfiliadoAdapter;

	public ConsultarComprobantDeAfiliadoController(
			ConsultarComprobanteDeAfiliadoAdapter consultarComprobanteDeAfiliadoAdapter,
			ConsultarAfiliadoAdapter consultarAfiliadoAdapter) {
		super();
		this.consultarComprobanteDeAfiliadoAdapter = consultarComprobanteDeAfiliadoAdapter;
		this.consultarAfiliadoAdapter = consultarAfiliadoAdapter;
	}
	
	@RequestMapping(value = "comprobantes/afiliado/numeroafiliado/{nroAfiliado}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> consultarTodosLosComprobantes(@PathVariable("nroAfiliado")String nroAfiliado){
		try {
		AfiliadoDTO afiliadoDTO = consultarAfiliadoAdapter.consultarAfiliadoPorNumero(nroAfiliado);
		Collection<ComprobanteDTO> comprobantes = new ArrayList<>();
		consultarComprobanteDeAfiliadoAdapter.consultarTodosLosComprobantes(afiliadoDTO).forEach(comprobante->comprobantes.add(comprobante));
		if(comprobantes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(comprobantes);
		}
		catch (AfiliadoNoExisteException a) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado no existe");
		}
		catch (NumeroAfiliadoIncorrectoException | AfiliadoSinTitularException | 
				DniConPuntosException | AfiliadoSinPlanException | PlanIncompletoException | 
				PersonaIncompletaException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Datos de afiliado incorrectos");
		}
	}
	
	@RequestMapping(value = "comprobantes/afiliado/numeroafiliado/{nroAfiliado}/fechadesde/{fechaDesde}/fechahasta/{fechaHasta}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> consultarTodosLosComprobantesPorFechas(@PathVariable("nroAfiliado")String nroAfiliado, 
			@PathVariable("fechaDesde") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaDesde, 
			@PathVariable("fechaHasta") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fechaHasta){
		try {
		AfiliadoDTO afiliadoDTO = consultarAfiliadoAdapter.consultarAfiliadoPorNumero(nroAfiliado);
		Collection<ComprobanteDTO> comprobantes = new ArrayList<>();
		consultarComprobanteDeAfiliadoAdapter.consultarComprobantesAfiliadoPorFechas(afiliadoDTO, fechaDesde, fechaHasta).forEach(comprobante->comprobantes.add(comprobante));
		if(comprobantes.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(comprobantes);
		}
		catch (AfiliadoNoExisteException a) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado no existe");
		}
		catch (FechaIncorrectaException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Fecha incorrecta");
		}
		catch (NumeroAfiliadoIncorrectoException | AfiliadoSinTitularException | 
				DniConPuntosException | AfiliadoSinPlanException | PlanIncompletoException | 
				PersonaIncompletaException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Datos de afiliado incorrectos");
		}
	}
	
}
