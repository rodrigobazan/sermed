package ar.com.koodi.sermedadaptador;

import java.time.LocalDate;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.BuscarPersonaEntreAfiliadosAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Inputs.BuscarPersonaEntreAfiliadosInput;
import Mockito.MockitoExtension;
import Modelo.Persona;
import ModeloApi.ObraSocialDTO;
import ModeloApi.PersonaDTO;
import ModeloApi.SangreDTO;
import ModeloApi.TipoDocumentoDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarPersonaEntreAfiliadosAdapterTest {

	@Mock
	private BuscarPersonaEntreAfiliadosInput buscarPersonaEntreAfiliadosInput;
	
	@Test
	public void existePersonaPorDNI_ExistePersona_ReturnTrue() throws PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		BuscarPersonaEntreAfiliadosAdapter buscarPersonaEntreAfiliadosAdapter = new BuscarPersonaEntreAfiliadosAdapter(buscarPersonaEntreAfiliadosInput);
		when(buscarPersonaEntreAfiliadosInput.existePersonaPorDNI(any(Persona.class))).thenReturn(true);
		boolean resultado = buscarPersonaEntreAfiliadosAdapter.existePersonaEntreAfiliados(new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "33166401", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001-00", 0, null));
		Assertions.assertEquals(true, resultado);
	}

}
