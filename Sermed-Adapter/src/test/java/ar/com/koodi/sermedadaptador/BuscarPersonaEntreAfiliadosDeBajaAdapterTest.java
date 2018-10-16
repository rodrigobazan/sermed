package ar.com.koodi.sermedadaptador;

import Adaptadores.BuscarPersonaEntreAfiliadosDeBajaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Inputs.BuscarPersonaEntreAfiliadosDeBajaInput;
import Mockito.MockitoExtension;
import Modelo.Persona;
import ModeloApi.ObraSocialDTO;
import ModeloApi.PersonaDTO;
import ModeloApi.SangreDTO;
import ModeloApi.TipoDocumentoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarPersonaEntreAfiliadosDeBajaAdapterTest {

    @Mock
    BuscarPersonaEntreAfiliadosDeBajaInput buscarPersonaEntreAfiliadosDeBajaInput;

    @Test
    public void buscarPersonaEntreAfiliadoDeBaja_PersonaNoSeEncuentra_DevuelveFalso() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException {
        PersonaDTO persona = new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "99999999", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001-00", 0, null);
        when(buscarPersonaEntreAfiliadosDeBajaInput.existePersona(any(Persona.class))).thenReturn(false);
        BuscarPersonaEntreAfiliadosDeBajaAdapter buscarPersonaEntreAfiliadosDeBajaAdapter = new BuscarPersonaEntreAfiliadosDeBajaAdapter(buscarPersonaEntreAfiliadosDeBajaInput);
        boolean resultado = buscarPersonaEntreAfiliadosDeBajaAdapter.existePersonaPorDNI(persona);
        Assertions.assertFalse(resultado);
    }

    @Test
    public void buscarPersonaEntreAfiliadosDeBaja_PersonaSeEncuentra_DevuelveTrue() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException {
        PersonaDTO persona = new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "99999999", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001-00", 0, null);
        when(buscarPersonaEntreAfiliadosDeBajaInput.existePersona(any(Persona.class))).thenReturn(true);
        BuscarPersonaEntreAfiliadosDeBajaAdapter buscarPersonaEntreAfiliadosDeBajaAdapter = new BuscarPersonaEntreAfiliadosDeBajaAdapter(buscarPersonaEntreAfiliadosDeBajaInput);
        boolean resultado = buscarPersonaEntreAfiliadosDeBajaAdapter.existePersonaPorDNI(persona);
        Assertions.assertTrue(resultado);
    }
}
