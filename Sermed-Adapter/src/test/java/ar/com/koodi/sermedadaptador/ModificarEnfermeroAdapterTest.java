package ar.com.koodi.sermedadaptador;

import Adaptadores.ModificarEnfermeroAdapter;
import Excepciones.EnfermeroIncompletoException;
import Excepciones.MatriculasIgualesException;
import Excepciones.UpdateEnfermeroException;
import Inputs.ModificarEnfermeroInput;
import Mockito.MockitoExtension;
import Modelo.Enfermero;
import ModeloApi.EnfermeroDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModificarEnfermeroAdapterTest {

    @Mock
    ModificarEnfermeroInput modificarEnfermeroInput;

    @Test
    public void modificarEnfermero_DatosEnfermero_AssertTrue() throws MatriculasIgualesException, EnfermeroIncompletoException, UpdateEnfermeroException {
        ModificarEnfermeroAdapter modificarEnfermeroAdapter = new ModificarEnfermeroAdapter(modificarEnfermeroInput);
        when(modificarEnfermeroInput.modificarEnfermero(any(Enfermero.class))).thenReturn(factoryEnfermeroModificado());
        EnfermeroDTO enfermeroDTO = new EnfermeroDTO(1, "Cosme", "Fulanito", 123456, "3825674678");
        EnfermeroDTO enfermeroModificado = modificarEnfermeroAdapter.modificarEnfermero(enfermeroDTO);
        Assertions.assertEquals("Tompson", enfermeroModificado.apellido);
        Assertions.assertEquals("Homero", enfermeroModificado.nombre);

    }

    @Test
    public void modificarEnfermero__MatriculaExiste_NoActualiza() throws MatriculasIgualesException, EnfermeroIncompletoException, UpdateEnfermeroException {
        ModificarEnfermeroAdapter modificarEnfermeroAdapter = new ModificarEnfermeroAdapter(modificarEnfermeroInput);
        EnfermeroDTO romiNueva = new EnfermeroDTO(1,"Vega", "Romina", 192256, "4813148");
        when(modificarEnfermeroInput.modificarEnfermero(any(Enfermero.class))).thenThrow(MatriculasIgualesException.class);
        Assertions.assertThrows(MatriculasIgualesException.class, () -> modificarEnfermeroAdapter.modificarEnfermero(romiNueva));

    }

    private Enfermero factoryEnfermeroModificado() {
        return new Enfermero(1, "Tompson", "Homero", 123456, "3825674678");
    }
}
