package ar.com.koodi.sermedadaptador;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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

import Adaptadores.ConsultarVisitasDePersonaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Excepciones.VisitaIncompletaException;
import Inputs.ConsultarVisitasDePersonaInput;
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
import ModeloApi.ObraSocialDTO;
import ModeloApi.PersonaDTO;
import ModeloApi.SangreDTO;
import ModeloApi.TipoDocumentoDTO;
import ModeloApi.VisitaDTO;

@ExtendWith(MockitoExtension.class)
public class ConsultarVisitasPersonaAdapterTest {
	
	@Mock
	private ConsultarVisitasDePersonaInput consultarVisitasPersonaInput;
	
	@Test
	public void consultarVisitasPersona_ExistenVisitas_ReturnVisitas() throws PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException, VisitaIncompletaException {
		ConsultarVisitasDePersonaAdapter consultarVisitasDePersonaAdapter = new ConsultarVisitasDePersonaAdapter(consultarVisitasPersonaInput);
		when(consultarVisitasPersonaInput.consultarVisitasPersona(any(Persona.class))).thenReturn(crearVisitasArray());
		List<VisitaDTO> visitasDTO = consultarVisitasDePersonaAdapter.consultarVisitasDePersona(factoryPersonaTitularDTO());
		Assertions.assertEquals(4, visitasDTO.size());
	}
	
	@Test
	public void consultarVisitasPersonaPorFechas_ExistenVisitas_ReturnVisitas() throws VisitaIncompletaException, PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		ConsultarVisitasDePersonaAdapter consultarVisitasDePersonaAdapter = new ConsultarVisitasDePersonaAdapter(consultarVisitasPersonaInput);
		LocalDate fechaDesde = LocalDate.of(2018, 8, 9);
        LocalDate fechaHasta = LocalDate.of(2018, 8, 12);		
		when(consultarVisitasPersonaInput.consultarVisitasPersonaPorFechas(any(Persona.class),eq(fechaDesde),eq(fechaHasta))).thenReturn(crearVisitasArrayFiltro());
		List<VisitaDTO> visitasDTO = consultarVisitasDePersonaAdapter.consultarVisitasPersonaPorFechas(factoryPersonaTitularDTO(),fechaDesde, fechaHasta);
		Assertions.assertEquals(3, visitasDTO.size());
	}
	
	private List<Visita> crearVisitasArray() throws VisitaIncompletaException {
        List<Visita> lasVisitas = new ArrayList<Visita>();        
            Persona elPaciente= factoryPersonaTitular();
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
        
    }
	
	public List<Visita> crearVisitasArrayFiltro() throws VisitaIncompletaException {
        List<Visita> lasVisitas = new ArrayList<Visita>();        
            Persona elPaciente= factoryPersonaTitular();
            Medico medico = new Medico(1, "torres", "geerman", 12015, "as212321");
            Enfermero enfermero = new Enfermero(1, "torres", "geerman", 12015, "as212321");            
            Visita visita2 = Visita.instancia(2, 1453, elPaciente, LocalDateTime.of(2018, 8, 9, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita3 = Visita.instancia(3, 1454, elPaciente, LocalDateTime.of(2018, 8, 10, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita4 = Visita.instancia(4, 1455, elPaciente, LocalDateTime.of(2018, 8, 12, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            lasVisitas.add(visita2);
            lasVisitas.add(visita3);
            lasVisitas.add(visita4);
            return lasVisitas;
        
    }
	
    public Persona factoryPersonaTitular() {
        try {
            return Persona.instancia(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumento(1, "DNI"),
                    "30672405", new Sangre(1, "A", "RH+"), "3825674978", new ObraSocial(1, "ASDA"), "", factoryAntecedenteMedico(), 0);
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
            return null;
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
       
       public PersonaDTO factoryPersonaTitularDTO() {
           return new PersonaDTO(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumentoDTO(1, "DNI"),
                       "30672405", new SangreDTO(1, "A", "RH+"), "3825674978", new ObraSocialDTO(1, "ASDA"), "", 0, new ArrayList<>());
           
       }

      

}
