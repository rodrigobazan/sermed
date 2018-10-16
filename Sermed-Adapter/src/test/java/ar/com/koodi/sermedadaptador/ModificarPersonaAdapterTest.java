package ar.com.koodi.sermedadaptador;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import Adaptadores.ModificarPersonaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaExisteException;
import Excepciones.PersonaIncompletaException;
import Excepciones.UpdatePersonaException;
import Inputs.ModificarPersonaInput;
import Mockito.MockitoExtension;
import Modelo.Afeccion;
import Modelo.AntecedenteMedico;
import Modelo.ObraSocial;
import Modelo.Persona;
import Modelo.Sangre;
import Modelo.TipoDocumento;
import ModeloApi.AfeccionDTO;
import ModeloApi.AntecedenteMedicoDTO;
import ModeloApi.ObraSocialDTO;
import ModeloApi.PersonaDTO;
import ModeloApi.SangreDTO;
import ModeloApi.TipoDocumentoDTO;

@ExtendWith(MockitoExtension.class)
public class ModificarPersonaAdapterTest {

	@Mock
	private ModificarPersonaInput modificarPersonaInput;
	
	@Test
	public void modificarPersona_ModificaCorrectamente_ReturnPersonaModificada() throws PersonaIncompletaException, PersonaExisteException, UpdatePersonaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		PersonaDTO personaDTO = factoryPersonaDTO();
		ModificarPersonaAdapter modificarPersonaAdapter = new ModificarPersonaAdapter(modificarPersonaInput);
		when(modificarPersonaInput.modificarPersona(any(Persona.class))).thenReturn(factoryPersona());
		PersonaDTO personaModificada = modificarPersonaAdapter.modificarPersona(personaDTO); 
		Assertions.assertEquals("Torres",personaModificada.apellidos);
	}
	
	private PersonaDTO factoryPersonaDTO(){
        return new PersonaDTO(null, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001", 0, factoryAntecedenteMedicoDTO());

    }

    private Collection<AntecedenteMedicoDTO> factoryAntecedenteMedicoDTO() {
        AntecedenteMedicoDTO dislexia = new AntecedenteMedicoDTO(null, new AfeccionDTO(1, "Dislexia"), "Cronica");
        AntecedenteMedicoDTO gonorrea = new AntecedenteMedicoDTO(null, new AfeccionDTO(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedicoDTO diabetes = new AntecedenteMedicoDTO(null, new AfeccionDTO(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedicoDTO> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }
    
    private Persona factoryPersona() throws PersonaIncompletaException, NumeroAfiliadoIncorrectoException, DniConPuntosException{
        return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001",factoryAntecedenteMedico(),0);

    }

    private Collection<AntecedenteMedico> factoryAntecedenteMedico() {
        AntecedenteMedico dislexia = new AntecedenteMedico(null, new Afeccion(1, "Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(null, new Afeccion(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(null, new Afeccion(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }
}
