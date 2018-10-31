package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.AnularComprobanteAdapter;
import Adaptadores.ConsultarComprobantesAdapter;
import Excepciones.AfiliadoSinPlanException;
import Excepciones.AfiliadoSinTitularException;
import Excepciones.ComprobanteAnuladoException;
import Excepciones.ComprobanteNoExisteException;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Excepciones.PlanIncompletoException;
import ModeloApi.ComprobanteDTO;

@RestController
@RequestMapping("/sermed/")
public class AnularComprobanteController {

	private AnularComprobanteAdapter anularComprobanteAdapter;

	private ConsultarComprobantesAdapter consultarComprobantesAdapter;
	
	public AnularComprobanteController(AnularComprobanteAdapter anularComprobanteAdapter,
			ConsultarComprobantesAdapter consultarComprobantesAdapter) {
		super();
		this.anularComprobanteAdapter = anularComprobanteAdapter;
		this.consultarComprobantesAdapter = consultarComprobantesAdapter;
	}

	@RequestMapping(value = "comprobante/anular/numero/{nroComprobante}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> anularComprobante(@PathVariable("nroComprobante")String nroComprobante){		
		try {
			ComprobanteDTO comprobanteAAnular;
			comprobanteAAnular = consultarComprobantesAdapter.consultarComprobantePorNumero(nroComprobante);
			boolean resultado;
			resultado = anularComprobanteAdapter.anularComprobante(comprobanteAAnular);
			if(resultado)return ResponseEntity.status(HttpStatus.OK).body(true);
			else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (ComprobanteNoExisteException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Comprobante no existe");
		}
		 catch (PersonaIncompletaException | PlanIncompletoException | AfiliadoSinPlanException
				| ComprobanteAnuladoException | DniConPuntosException | NumeroAfiliadoIncorrectoException
				| AfiliadoSinTitularException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
		
	}
}
