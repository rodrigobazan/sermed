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

import Adaptadores.ConsultarMedicoAdapter;
import Excepciones.MedicoNoExisteException;
import ModeloApi.MedicoDTO;

@RestController
@RequestMapping("/sermed/")
public class ConsultarMedicoController {

	private ConsultarMedicoAdapter consultarMedicoAdapter;

	public ConsultarMedicoController(ConsultarMedicoAdapter consultarMedicoAdapter) {
		super();
		this.consultarMedicoAdapter = consultarMedicoAdapter;
	}
	
	@RequestMapping(value = "/medicos", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarMedicos(){
		Collection<MedicoDTO> medicoDTOs = new ArrayList<>();
		consultarMedicoAdapter.consultarMedicos().forEach(medico->medicoDTOs.add(medico));
		if(medicoDTOs.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(medicoDTOs);
	}
	
	@RequestMapping(value = "/medicos/apellido/{apellido}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarMedicosPorApellido(@PathVariable("apellido")String apellido){
		Collection<MedicoDTO> medicoDTOs = new ArrayList<>();
		consultarMedicoAdapter.consultarMedicosPorApellido(apellido).forEach(medico->medicoDTOs.add(medico));
		if(medicoDTOs.isEmpty())return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.status(HttpStatus.OK).body(medicoDTOs);
	}
		
	@RequestMapping(value = "/medico/matricula/{matricula}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> consultarMedicoPorMatricula(@PathVariable("matricula")Integer matricula){
		try {
			MedicoDTO medicoDTOs = consultarMedicoAdapter.consultarMedicoPorMatricula(matricula);
			return ResponseEntity.status(HttpStatus.OK).body(medicoDTOs); 
		}
		catch (MedicoNoExisteException e) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
}
