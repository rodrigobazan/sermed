package ar.com.koodi.sermedboundaries.SermedServices.Controller;

import Adaptadores.ModificarMedicoAdapter;
import Excepciones.MatriculasIgualesException;
import Excepciones.MedicoIncompletoException;
import Excepciones.UpdateMedicoException;
import ModeloApi.MedicoDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/sermed/medico")
public class ModificarMedicoController {

    private final ModificarMedicoAdapter modificarMedicoAdapter;

    public ModificarMedicoController(ModificarMedicoAdapter modificarMedicoAdapter) {
        this.modificarMedicoAdapter = modificarMedicoAdapter;
    }

    @RequestMapping(value = "/modificar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<?> modificarMedico(@RequestBody @Valid MedicoDTO medico){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(modificarMedicoAdapter.modificarMedico(medico));
        } catch (UpdateMedicoException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (MatriculasIgualesException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Matricula ya existe");
        } catch (MedicoIncompletoException e) {
            return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Faltan datos");
        }
    }
}
