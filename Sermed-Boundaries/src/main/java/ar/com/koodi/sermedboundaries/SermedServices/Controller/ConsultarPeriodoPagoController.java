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

import Adaptadores.ConsultarPeriodoPagoAdapter;
import ModeloApi.PeriodoPagoDTO;

@RestController
@RequestMapping("/sermed/")
public class ConsultarPeriodoPagoController {

	private ConsultarPeriodoPagoAdapter consultarPeriodoPagoAdapter;

	public ConsultarPeriodoPagoController(ConsultarPeriodoPagoAdapter consultarPeriodoPagoAdapter) {
		super();
		this.consultarPeriodoPagoAdapter = consultarPeriodoPagoAdapter;
	}
	
	@RequestMapping(value = "/periodospago", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarPeriodoPago(){
		Collection<PeriodoPagoDTO>periodosPagoDTO = new ArrayList<>();
		consultarPeriodoPagoAdapter.consultarPeriodoPago().forEach(periodopago->periodosPagoDTO.add(periodopago));
		if(periodosPagoDTO.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(periodosPagoDTO);
	}
	
	@RequestMapping(value = "/periodospago/anio/{anio}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarPeriodoPagoPorAnio(@PathVariable("anio")int anio){
		Collection<PeriodoPagoDTO>periodosPagoDTO = new ArrayList<>();
		consultarPeriodoPagoAdapter.consultarPeriodoPagoPorAnio(anio).forEach(periodopago->periodosPagoDTO.add(periodopago));
		if(periodosPagoDTO.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(periodosPagoDTO);
	}
	
	@RequestMapping(value = "/periodospago/mes/{mes}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarPeriodoPagoPorMes(@PathVariable("mes")int mes){
		Collection<PeriodoPagoDTO>periodosPagoDTO = new ArrayList<>();
		consultarPeriodoPagoAdapter.consultarPeriodoPagoPorMes(mes).forEach(periodopago->periodosPagoDTO.add(periodopago));
		if(periodosPagoDTO.isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(periodosPagoDTO);
	}
	
	@RequestMapping(value = "/periodospago/mes/{mes}/anio/{anio}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarPeriodoPagoPorMesYAnio(@PathVariable("mes")int mes, @PathVariable("anio") int anio){
		try {
			PeriodoPagoDTO periodoPagoDTO = consultarPeriodoPagoAdapter.consultarPeriodoPagoPorMesAnio(mes, anio);
			return ResponseEntity.status(HttpStatus.OK).body(periodoPagoDTO);
		}
		catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}		
	}
	
	@RequestMapping(value = "/periodospago/mesdesde/{mesdesde}/meshasta/{meshasta}/anio/{anio}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarPeriodoPagoPorMesYAnio(@PathVariable("mesdesde")int mesdesde, @PathVariable("meshasta")int meshasta, @PathVariable("anio") int anio){
		Collection<PeriodoPagoDTO> periodoPagoDTOs = new ArrayList<>();
		consultarPeriodoPagoAdapter.consultarPeriodoPagoPorIntervalo(mesdesde, meshasta, anio).forEach(periodopago-> periodoPagoDTOs.add(periodopago));
		if(periodoPagoDTOs.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(periodoPagoDTOs);	
	}
}
