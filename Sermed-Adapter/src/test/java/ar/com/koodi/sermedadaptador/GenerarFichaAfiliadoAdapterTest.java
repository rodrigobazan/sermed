package ar.com.koodi.sermedadaptador;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.GenerarFichaAfiliadoAdapter;
import Excepciones.AfiliadoNoExisteException;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Excepciones.PlanIncompletoException;
import Inputs.GenerarFichaAfiliadoInput;
import Mockito.MockitoExtension;
import Modelo.Afiliado;
import Modelo.ObraSocial;
import Modelo.Persona;
import Modelo.Plan;
import Modelo.Sangre;
import Modelo.TipoDocumento;
import ModeloApi.FichaAfiliadoDTO;
import ModeloApi.ObraSocialDTO;
import ModeloApi.PersonaDTO;
import ModeloApi.PlanDTO;
import ModeloApi.SangreDTO;
import ModeloApi.TipoDocumentoDTO;

@ExtendWith(MockitoExtension.class)
public class GenerarFichaAfiliadoAdapterTest {

	@Mock
	private GenerarFichaAfiliadoInput generarFichaAfiliadoInput;
	
	@Test
	public void generarFichaAfiliadoParaReporte_GeneraCorrectamente_ReturnFichaAfiliado() throws AfiliadoNoExisteException, PlanIncompletoException{
		GenerarFichaAfiliadoAdapter generarFichaAfiliadoAdapter = new GenerarFichaAfiliadoAdapter(generarFichaAfiliadoInput); 
		when(generarFichaAfiliadoInput.generarFichaAfiliadoParaReporte("000001")).thenReturn(factoryFicha());
		FichaAfiliadoDTO fichaDTO= generarFichaAfiliadoAdapter.generarFichaAfiliado("000001");
		Assertions.assertNotNull(fichaDTO);
		
	}
	
	private ModeloReporte.FichaAfiliadoDTO factoryFicha() throws PlanIncompletoException{
		return new ModeloReporte.FichaAfiliadoDTO(LocalDate.of(2018, 6, 27), "000003", new Persona(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
	                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
	                    new ObraSocial(1, "OSFATUN"), "000001", null,0), new ArrayList<>(), factoryPlan());
	}
	
	 private Persona factoryPersona() throws PersonaIncompletaException {
	        try {
	            return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
	                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
	                    new ObraSocial(1, "OSFATUN"), "000001", null, 0);
	        } catch (DniConPuntosException e) {
	            e.printStackTrace();
	            return null;
	        }

	    }

	    private List<Persona> factoryPersonaMiembros() {
	        try {
	            List<Persona> personas = new ArrayList<>();
	            personas.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
	                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
	                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
	            personas.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
	                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
	                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
	            personas.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
	                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
	                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
	            personas.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
	                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
	                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
	            return personas;
	        } catch (PersonaIncompletaException e) {
	            e.printStackTrace();
	            return new ArrayList<>();
	        } catch (DniConPuntosException e) {
	            e.printStackTrace();
	            return new ArrayList<>();
	        }

	    }

	    private Plan factoryPlan() throws PlanIncompletoException {
	        HashMap<String, Double> listaPrecios = new HashMap<>();
	        listaPrecios.put("1", (double) 380);
	        listaPrecios.put("2", (double) 480);
	        listaPrecios.put("3", (double) 550);
	        listaPrecios.put("4", (double) 600);
	        listaPrecios.put("5", (double) 650);
	        listaPrecios.put("6", (double) 700);
	        listaPrecios.put("7", (double) 750);

	        return Plan.instancia(1,"Plan Basico",listaPrecios);
	    }
}
