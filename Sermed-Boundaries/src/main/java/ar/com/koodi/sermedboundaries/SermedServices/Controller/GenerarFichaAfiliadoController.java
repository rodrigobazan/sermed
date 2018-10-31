package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import Adaptadores.GenerarFichaAfiliadoAdapter;
import Excepciones.AfiliadoNoExisteException;
import ModeloApi.FichaAfiliadoDTO;

@RestController
@RequestMapping("/sermed/")
public class GenerarFichaAfiliadoController {

	private GenerarFichaAfiliadoAdapter generarFichaAfiliadoAdapter;

	public GenerarFichaAfiliadoController(GenerarFichaAfiliadoAdapter generarFichaAfiliadoAdapter) {
		super();
		this.generarFichaAfiliadoAdapter = generarFichaAfiliadoAdapter;
	}

	@RequestMapping(value = "/ficha/numeroafiliado/{nroAfiliado}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> generarFichaAfiliado(@PathVariable("nroAfiliado") String nroAfiliado){
		try {
			FichaAfiliadoDTO fichaAfiliadoDTO = generarFichaAfiliadoAdapter.generarFichaAfiliado(nroAfiliado);
			return ResponseEntity.status(HttpStatus.OK).body(fichaAfiliadoDTO);
		} catch (AfiliadoNoExisteException e) {
			return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Afiliado no existe");
		}
		
    }
}
