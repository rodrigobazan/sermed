package ar.com.koodi.sermedadaptador;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.ModificarObraSocialAdapter;
import Excepciones.NombreObraSocialExisteException;
import Excepciones.UpdateObraSocialException;
import Inputs.ModificarObraSocialInput;
import Mockito.MockitoExtension;
import Modelo.ObraSocial;
import ModeloApi.ObraSocialDTO;

@ExtendWith(MockitoExtension.class)
public class ModificarObraSocialAdapterTest {
	
	@Mock
	private ModificarObraSocialInput modificarObraSocialInput;
	
	@Test
	public void modificarObraSocial_ActualizaCorrectamente_ReturnObraSocialModificada() throws NombreObraSocialExisteException, UpdateObraSocialException {
		ObraSocialDTO obraSocial = new ObraSocialDTO(1, "OSFATUN");
		when(modificarObraSocialInput.modificarObraSocial(any(ObraSocial.class))).thenReturn(new ObraSocial(1, "OSFATUN"));
		ModificarObraSocialAdapter modificarObraSocialAdapter = new ModificarObraSocialAdapter(modificarObraSocialInput);
		ObraSocialDTO obraSocialModificada = modificarObraSocialAdapter.modificarObraSocial(obraSocial);
		Assertions.assertEquals("OSFATUN",obraSocialModificada.obraSocial);
	}

}
