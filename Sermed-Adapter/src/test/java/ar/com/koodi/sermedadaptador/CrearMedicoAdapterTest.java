package ar.com.koodi.sermedadaptador;

import Adaptadores.CrearMedicoAdapter;
import Excepciones.MedicoExisteException;
import Inputs.CrearMedicoInput;
import Mockito.MockitoExtension;
import Modelo.Medico;
import ModeloApi.MedicoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearMedicoAdapterTest {

    @Mock
    private CrearMedicoInput crearMedicoInput;

    @Test
    public void crearMedico_GuardaCorrectamente_DevuelveTrue() throws MedicoExisteException {
        MedicoDTO medicoDTO = factoryMedico();
        CrearMedicoAdapter crearMedicoAdapter = new CrearMedicoAdapter(crearMedicoInput);
        when(crearMedicoInput.crearMedico(any(Medico.class))).thenReturn(true);
        boolean resultado = crearMedicoAdapter.crearMedico(medicoDTO);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void crearEnfermero_EnfermeroExiste_EnfermeroExisteException() throws MedicoExisteException {
        MedicoDTO medicoDTO = new MedicoDTO(null, "Morales", "Cachete", 190200, "85235791");
        CrearMedicoAdapter crearMedicoAdapter = new CrearMedicoAdapter(crearMedicoInput);
        when(crearMedicoInput.crearMedico(any(Medico.class))).thenThrow(MedicoExisteException.class);
        Assertions.assertThrows(MedicoExisteException.class, () -> crearMedicoAdapter.crearMedico(medicoDTO));
    }

    private MedicoDTO factoryMedico() {
        return new MedicoDTO(null, "Gomez", "Droopy", 190200, "3825674978");
    }

}
