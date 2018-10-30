package ar.com.koodi.sermedadaptador;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.ConsultarVisitaAdapter;
import Excepciones.AfiliadoSinPlanException;
import Excepciones.AfiliadoSinTitularException;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Excepciones.PlanIncompletoException;
import Excepciones.VisitaIncompletaException;
import Excepciones.VisitaNoExisteException;
import Inputs.ConsultarVisitaInput;
import Mockito.MockitoExtension;
import Modelo.Afeccion;
import Modelo.AntecedenteMedico;
import Modelo.Enfermero;
import Modelo.Medico;
import Modelo.ObraSocial;
import Modelo.Persona;
import Modelo.Sangre;
import Modelo.TipoDocumento;
import Modelo.Visita;
import ModeloApi.VisitaDTO;

@ExtendWith(MockitoExtension.class)
public class ConsultarVisitaAdapterTest {

	@Mock
	private ConsultarVisitaInput consultarVisitaInput;
	
	@Test
	public void consultarVisitas_ExistenVisitas_ReturnListaVisitas() {
		ConsultarVisitaAdapter consultarVisitaAdapter = new ConsultarVisitaAdapter(consultarVisitaInput);
		when(consultarVisitaInput.consultarVisitas()).thenReturn(crearVisitasArray());
		List<VisitaDTO> visitasDTO = consultarVisitaAdapter.consultarVisitas();
		Assertions.assertEquals(4, visitasDTO.size());
	}
	
	@Test
	public void consultarVisitasEntreFechas_ExistenVisitas_ReturnListaVisitas() {
		ConsultarVisitaAdapter consultarVisitaAdapter = new ConsultarVisitaAdapter(consultarVisitaInput);
		LocalDate fechaDesde = LocalDate.of(2018, 8, 9);
        LocalDate fechaHasta = LocalDate.of(2018, 8, 12);
		when(consultarVisitaInput.consultarVisitasEntreFechas(fechaDesde, fechaHasta)).thenReturn(crearVisitasFiltroArray());
		List<VisitaDTO> visitasDTO = consultarVisitaAdapter.consultarVisitasEntreFechas(fechaDesde, fechaHasta);
		Assertions.assertEquals(3, visitasDTO.size());
	}
	
	@Test
	public void consultarVisitaPorNumero_ExisteVisita_ReturnVisita() throws VisitaNoExisteException, VisitaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException {
		ConsultarVisitaAdapter consultarVisitaAdapter = new ConsultarVisitaAdapter(consultarVisitaInput);
		when(consultarVisitaInput.consultarVisitaPorNumero(1452)).thenReturn(factoryVisita());
		VisitaDTO visitaDTO = consultarVisitaAdapter.consultarVisitaPorNumero(1452);
		Assertions.assertEquals("Torres", visitaDTO.elPaciente.apellidos);
		
	}

	private Visita factoryVisita() throws VisitaIncompletaException, PlanIncompletoException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
        try{
            Persona elPaciente= factoryPersona();
            Medico medico = new Medico(1, "torreson", "geerman", 12015, "as212321");
            Enfermero enfermero = new Enfermero(1, "torres", "geerman", 12015, "as212321");
            return Visita.instancia(1, 1452, elPaciente, LocalDateTime.of(2018, 8, 7, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
        }catch (Exception s){
            return null;
        }

	}
	
	private List<Visita> crearVisitasArray() {
        List<Visita> lasVisitas = new ArrayList<Visita>();
        try {
            Persona elPaciente= factoryPersona();
            Medico medico = new Medico(1, "torres", "geerman", 12015, "as212321");
            Enfermero enfermero = new Enfermero(1, "torres", "geerman", 12015, "as212321");
            Visita visita1 = Visita.instancia(1, 1452, elPaciente, LocalDateTime.of(2018, 8, 7, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita2 = Visita.instancia(2, 1453, elPaciente, LocalDateTime.of(2018, 8, 9, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita3 = Visita.instancia(3, 1454, elPaciente, LocalDateTime.of(2018, 8, 10, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita4 = Visita.instancia(4, 1455, elPaciente, LocalDateTime.of(2018, 8, 12, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);


            lasVisitas.add(visita1);
            lasVisitas.add(visita2);
            lasVisitas.add(visita3);
            lasVisitas.add(visita4);
            return lasVisitas;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return lasVisitas;
        }
    }
	
	private List<Visita> crearVisitasFiltroArray() {
        List<Visita> lasVisitas = new ArrayList<Visita>();
        try {
            Persona elPaciente= factoryPersona();
            Medico medico = new Medico(1, "torres", "geerman", 12015, "as212321");
            Enfermero enfermero = new Enfermero(1, "torres", "geerman", 12015, "as212321");            
            Visita visita2 = Visita.instancia(2, 1453, elPaciente, LocalDateTime.of(2018, 8, 9, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita3 = Visita.instancia(3, 1454, elPaciente, LocalDateTime.of(2018, 8, 10, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita4 = Visita.instancia(4, 1455, elPaciente, LocalDateTime.of(2018, 8, 12, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);

            lasVisitas.add(visita2);
            lasVisitas.add(visita3);
            lasVisitas.add(visita4);
            return lasVisitas;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return lasVisitas;
        }
    }
	
	private Persona factoryPersona() throws PersonaIncompletaException {
        try {
            return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0);
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
        }

    }
	
	 private Collection<AntecedenteMedico> factoryAntecedenteMedico() {
	        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1, "Dislexia"), "Cronica");
	        AntecedenteMedico gonorrea = new AntecedenteMedico(2, new Afeccion(1, "gonorrea"), "Cronica Tambien");
	        AntecedenteMedico diabetes = new AntecedenteMedico(3, new Afeccion(1, "diabetes"), "nerviosa");

	        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

	        return listaAntecedentes;
	    }

}
