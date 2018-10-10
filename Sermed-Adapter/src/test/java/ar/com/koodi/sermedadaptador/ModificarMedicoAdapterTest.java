package ar.com.koodi.sermedadaptador;

import Adaptadores.ModificarMedicoAdapter;
import Excepciones.MatriculasIgualesException;
import Excepciones.MedicoIncompletoException;
import Excepciones.UpdateMedicoException;
import Inputs.ModificarMedicoInput;
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
public class ModificarMedicoAdapterTest {

    @Mock
    ModificarMedicoInput modificarMedicoInput;

    @Test
    public void modificarMedico_DatosMedico_AssertTrue() throws UpdateMedicoException, MatriculasIgualesException, MedicoIncompletoException {
        ModificarMedicoAdapter modificarMedicoAdapter = new ModificarMedicoAdapter(modificarMedicoInput);
        when(modificarMedicoInput.modificarMedico(any(Medico.class))).thenReturn(factoryMedicoModificado());
        MedicoDTO medicoDTO = new MedicoDTO(1, "Morales", "Cachete", 190000, "75395184");
        MedicoDTO medicoModificado = modificarMedicoAdapter.modificarMedico(medicoDTO);
        Assertions.assertEquals("Gomez", medicoModificado.apellido);
        Assertions.assertEquals("Droopy", medicoModificado.nombre);
    }

    @Test
    public void modificarMedico_MatriculaExiste_NoActualiza() throws UpdateMedicoException, MatriculasIgualesException, MedicoIncompletoException {
        ModificarMedicoAdapter modificarMedicoAdapter = new ModificarMedicoAdapter(modificarMedicoInput);
        MedicoDTO medicoNuevo = new MedicoDTO(1, "Power", "Max", 190000, "12345678");
        when(modificarMedicoInput.modificarMedico(any(Medico.class))).thenThrow(MatriculasIgualesException.class);
        Assertions.assertThrows(MatriculasIgualesException.class, () -> modificarMedicoAdapter.modificarMedico(medicoNuevo));
    }

    private Medico factoryMedicoModificado() {
        return new Medico(1, "Gomez", "Droopy", 190000, "75395184");
    }
}
