package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.CrearVisitaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import ModeloApi.VisitaDTO;

@RestController
@RequestMapping("/visita")
public class CrearVisitaController {

	private CrearVisitaAdapter crearVisitaAdapter;

	public CrearVisitaController(CrearVisitaAdapter crearVisitaAdapter) {
		super();
		this.crearVisitaAdapter = crearVisitaAdapter;
	}

	@RequestMapping(value = "/nueva", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<?> crearVisita(@RequestBody @Valid VisitaDTO visitaDTO) throws PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException 
	{
		boolean resultado = crearVisitaAdapter.crearVisita(visitaDTO);
		if (resultado)
			return ResponseEntity.status(HttpStatus.OK).body(true);
		else
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
