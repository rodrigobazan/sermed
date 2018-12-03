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

import Adaptadores.ConsultarAfiliadoAdapter;
import Excepciones.AfiliadoNoExisteException;
import ModeloApi.AfiliadoDTO;

@RestController
@RequestMapping("/")
public class ConsultarAfiliadoController {

	private ConsultarAfiliadoAdapter consultarAfiliadoAdapter;

	public ConsultarAfiliadoController(ConsultarAfiliadoAdapter consultarAfiliadoAdapter) {
		super();
		this.consultarAfiliadoAdapter = consultarAfiliadoAdapter;
	}

	@RequestMapping(value = "/afiliados", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> consultarAfiliados() {
		Collection<AfiliadoDTO> afiliadoDTOs = new ArrayList<>();
		consultarAfiliadoAdapter.consultarAfiliados().forEach(afiliado -> afiliadoDTOs.add(afiliado));
		if (afiliadoDTOs.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(afiliadoDTOs);
	}

	@RequestMapping(value = "/afiliados/numero/{numero}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> consultarAfiliadosPorNumero(@PathVariable("numero") String numero) {
		Collection<AfiliadoDTO> afiliadoDTOs = new ArrayList<>();
		consultarAfiliadoAdapter.consultarAfiliadosPorNumero(numero).forEach(afiliado -> afiliadoDTOs.add(afiliado));
		if (afiliadoDTOs.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(afiliadoDTOs);
	}

	@RequestMapping(value = "/afiliado/numero/{numero}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<?> consultarAfiliadoPorNumero(@PathVariable("numero") String numero) {
		try {
			AfiliadoDTO afiliadoDTO = consultarAfiliadoAdapter.consultarAfiliadoPorNumero(numero);
			return ResponseEntity.status(HttpStatus.OK).body(afiliadoDTO);
		} catch (AfiliadoNoExisteException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}
}
