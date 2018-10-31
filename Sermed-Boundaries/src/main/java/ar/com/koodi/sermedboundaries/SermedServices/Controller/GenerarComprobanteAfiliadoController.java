package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.GenerarComprobanteAfiliadoAdapter;
import Excepciones.ComprobanteNoExisteException;
import ModeloApi.ComprobanteAfiliadoReporteDTO;

@RestController
@RequestMapping("/sermed/")
public class GenerarComprobanteAfiliadoController {

	private GenerarComprobanteAfiliadoAdapter generarComprobanteAfiliadoAdapter;

	public GenerarComprobanteAfiliadoController(GenerarComprobanteAfiliadoAdapter generarComprobanteAfiliadoAdapter) {
		super();
		this.generarComprobanteAfiliadoAdapter = generarComprobanteAfiliadoAdapter;
	}
	
	@RequestMapping(value = "/comprobante/numero/{nroComprobante}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> generarComprobanteAfiliadoReporte(@PathVariable("nroComprobante") String nroComprobante){
		ComprobanteAfiliadoReporteDTO comprobanteAfiliadoReporteDTO;
		try {
			comprobanteAfiliadoReporteDTO = generarComprobanteAfiliadoAdapter.generarComprobanteAfiliadoReporte(nroComprobante);
			return ResponseEntity.status(HttpStatus.OK).body(comprobanteAfiliadoReporteDTO);
		} catch (ComprobanteNoExisteException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
}