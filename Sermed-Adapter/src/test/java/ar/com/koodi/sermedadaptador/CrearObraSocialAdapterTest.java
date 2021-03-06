package ar.com.koodi.sermedadaptador;

import Adaptadores.CrearObraSocialAdapter;
import Excepciones.ObraSocialExisteException;
import Inputs.CrearObraSocialInput;
import Mockito.MockitoExtension;
import Modelo.ObraSocial;
import ModeloApi.ObraSocialDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearObraSocialAdapterTest {

    @Mock
    CrearObraSocialInput crearObraSocialInput;

    @Test
    public void crearObraSocial_GuardaCorrectamente_DevuelveTrue() throws ObraSocialExisteException {
        ObraSocialDTO obraSocialDTO = factoryObraSocial();
        CrearObraSocialAdapter crearObraSocialAdapter = new CrearObraSocialAdapter(crearObraSocialInput);
        when(crearObraSocialInput.crearObraSocial(any(ObraSocial.class))).thenReturn(true);
        boolean resultado = crearObraSocialAdapter.crearObraSocial(obraSocialDTO);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void crearObraSocial_NombreExiste_ObraSocialExisteException() throws ObraSocialExisteException {
        ObraSocialDTO obraSocialDTO = new ObraSocialDTO(null, "APOS");
        CrearObraSocialAdapter crearObraSocialAdapter = new CrearObraSocialAdapter(crearObraSocialInput);
        when(crearObraSocialInput.crearObraSocial(any(ObraSocial.class))).thenThrow(ObraSocialExisteException.class);
        Assertions.assertThrows(ObraSocialExisteException.class, () -> crearObraSocialAdapter.crearObraSocial(obraSocialDTO));
    }

    private ObraSocialDTO factoryObraSocial() {
        return new ObraSocialDTO(null, "APOS");
    }
}
