package ar.com.koodi.sermedadaptador;

import Adaptadores.CrearPersonaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaExisteException;
import Excepciones.PersonaIncompletaException;
import Inputs.CrearMedicoInput;
import Inputs.CrearPersonaInput;
import Mockito.MockitoExtension;
import Modelo.Afeccion;
import Modelo.AntecedenteMedico;
import Modelo.Persona;
import ModeloApi.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearPersonaAdapterTest {

    @Mock
    private CrearPersonaInput crearPersonaInput;

    @Test
    public void crearPersona_PersonaNoExiste_GuardaPersona() throws NumeroAfiliadoIncorrectoException, PersonaIncompletaException, DniConPuntosException, PersonaExisteException {
        PersonaDTO personaDTO = factoryPersona();
        when(crearPersonaInput.crearPersona(any(Persona.class))).thenReturn(true);
        CrearPersonaAdapter crearPersonaAdapter = new CrearPersonaAdapter(crearPersonaInput);
        boolean resultado = crearPersonaAdapter.crearPersona(personaDTO);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void crearPersona_PersonaExiste_NoGuardaPersona() throws PersonaExisteException {
        PersonaDTO persona = new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001", 0, factoryAntecedenteMedico());
        CrearPersonaAdapter crearPersonaAdapter = new CrearPersonaAdapter(crearPersonaInput);
        when(crearPersonaInput.crearPersona(any(Persona.class))).thenThrow(PersonaExisteException.class);
        Assertions.assertThrows(PersonaExisteException.class, () -> crearPersonaAdapter.crearPersona(persona));
    }

    private PersonaDTO factoryPersona(){
        return new PersonaDTO(null, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001", 0, factoryAntecedenteMedico());

    }

    private Collection<AntecedenteMedicoDTO> factoryAntecedenteMedico() {
        AntecedenteMedicoDTO dislexia = new AntecedenteMedicoDTO(null, new AfeccionDTO(1, "Dislexia"), "Cronica");
        AntecedenteMedicoDTO gonorrea = new AntecedenteMedicoDTO(null, new AfeccionDTO(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedicoDTO diabetes = new AntecedenteMedicoDTO(null, new AfeccionDTO(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedicoDTO> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }
}
