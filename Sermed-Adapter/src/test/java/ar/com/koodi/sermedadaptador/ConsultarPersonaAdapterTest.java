package ar.com.koodi.sermedadaptador;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.ConsultarPersonaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Excepciones.PersonaNoExisteException;
import Inputs.ConsultarPersonaInput;
import Mockito.MockitoExtension;
import Modelo.Afeccion;
import Modelo.AntecedenteMedico;
import Modelo.ObraSocial;
import Modelo.Persona;
import Modelo.Sangre;
import Modelo.TipoDocumento;
import ModeloApi.PersonaDTO;

@ExtendWith(MockitoExtension.class)
public class ConsultarPersonaAdapterTest {

	@Mock
	private ConsultarPersonaInput consultarPersonaInput;
	
	@Test
	public void consultarPersonas_ExistePersonas_ReturnListaPersonas() {
		ConsultarPersonaAdapter consultarPersonaAdapter = new ConsultarPersonaAdapter(consultarPersonaInput);
		when(consultarPersonaInput.consultarPersonas()).thenReturn(crearPersonasArray());
		List<PersonaDTO> personas = consultarPersonaAdapter.consultarPersonas();
		Assertions.assertEquals(4, personas.size());
		
	}
	
	@Test
	public void consultarPersonasPorApellido_ExistenPersona_ReturnListaPersonas() {
		ConsultarPersonaAdapter consultarPersonaAdapter = new ConsultarPersonaAdapter(consultarPersonaInput);
		when(consultarPersonaInput.consultarPersonasPorApellido("Torr")).thenReturn(crearPersonaFiltroApellidoArray());
		List<PersonaDTO> personas = consultarPersonaAdapter.consultarPersonasPorApellido("Torr");
		Assertions.assertEquals(2, personas.size());
	}
	
	@Test
	public void consultarPersonaPorNumeroAfiliado_ExistePersona_ReturnPersona() throws PersonaNoExisteException, PersonaIncompletaException {
		ConsultarPersonaAdapter consultarPersonaAdapter = new ConsultarPersonaAdapter(consultarPersonaInput);
		when(consultarPersonaInput.consultarPersonaPorNumeroAfiliado("000001", 0)).thenReturn(factoryPersona());
		PersonaDTO personaDTO = consultarPersonaAdapter.consultarPersonaPorNumeroAfiliado("000001",0);
		Assertions.assertEquals("Torres", personaDTO.apellidos);
	}
	
    private List<Persona> crearPersonasArray(){
        List<Persona> lasPersonas=new ArrayList<Persona>();
        try {
            lasPersonas.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            lasPersonas.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            lasPersonas.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            lasPersonas.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            return lasPersonas;
        }
        catch (Exception ex) {
        }
        finally {
            return lasPersonas;
        }
    }

    private Collection<Persona> crearPersonaFiltroApellidoArray() {
        List<Persona> lasPersonas=new ArrayList<Persona>();
        try {
            lasPersonas.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            lasPersonas.add(Persona.instancia(1, "Torresson", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            return lasPersonas;
        }
        catch (Exception ex) {
        }
        finally {
            return lasPersonas;
        }
    }


    private Collection<AntecedenteMedico> factoryAntecedenteMedico() {
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1, "Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2, new Afeccion(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3, new Afeccion(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }

    private Persona factoryPersona() throws PersonaIncompletaException {
        try {
            return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0);
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
            return null;
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
        }

    }
}
