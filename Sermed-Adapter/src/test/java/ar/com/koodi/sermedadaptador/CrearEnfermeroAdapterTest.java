package ar.com.koodi.sermedadaptador;

import Adaptadores.CrearEnfermeroAdapter;
import Excepciones.EnfermeroExisteException;
import Inputs.CrearEnfermeroInput;
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
public class CrearEnfermeroAdapterTest {

    @Mock
    private CrearEnfermeroInput crearEnfermeroInput;

    @Test
    public void crearEnfermero_GuardaCorrectamente_DevuelveTrue() throws EnfermeroExisteException {
        EnfermeroDTO enfermeroDTO = factoryEnfermero();
        CrearEnfermeroAdapter crearEnfermeroAdapter = new CrearEnfermeroAdapter(crearEnfermeroInput);
        when(crearEnfermeroInput.crearEnfermero(any(Enfermero.class))).thenReturn(true);
        boolean resultado = crearEnfermeroAdapter.crearEnfermero(enfermeroDTO);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void crearEnfermero_EnfermeroExiste_EnfermeroExisteException() throws EnfermeroExisteException {
        EnfermeroDTO enfermeroDTO = new EnfermeroDTO(null, "Gomez", "Droopy", 123456, "12345678");
        CrearEnfermeroAdapter crearEnfermeroAdapter = new CrearEnfermeroAdapter(crearEnfermeroInput);
        when(crearEnfermeroInput.crearEnfermero(any(Enfermero.class))).thenThrow(EnfermeroExisteException.class);
        Assertions.assertThrows(EnfermeroExisteException.class, () -> crearEnfermeroAdapter.crearEnfermero(enfermeroDTO));

    }

    public EnfermeroDTO factoryEnfermero() {
        return new EnfermeroDTO(null, "Cosme", "Fulanito", 123456, "3825674678");
    }

}
