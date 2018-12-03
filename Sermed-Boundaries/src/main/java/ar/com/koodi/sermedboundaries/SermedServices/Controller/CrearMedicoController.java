package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.CrearMedicoAdapter;
import Excepciones.MedicoExisteException;
import ModeloApi.MedicoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/medico")
public class CrearMedicoController {

    private CrearMedicoAdapter crearMedicoAdapter;

    public CrearMedicoController(CrearMedicoAdapter crearMedicoAdapter) {
        this.crearMedicoAdapter = crearMedicoAdapter;
    }

    @RequestMapping(value = "/nuevo", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> crearMedico(@RequestBody @Valid MedicoDTO medico){
        try {
            boolean resultado = crearMedicoAdapter.crearMedico(medico);
            if(resultado) return ResponseEntity.status(HttpStatus.OK).body(true);
            else return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MedicoExisteException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Medico ya existe");
        }
    }
}
