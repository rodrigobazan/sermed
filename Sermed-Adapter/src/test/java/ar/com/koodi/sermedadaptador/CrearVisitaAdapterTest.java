package ar.com.koodi.sermedadaptador;



import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.CrearVisitaAdapter;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Inputs.CrearVisitaInput;
import Mockito.MockitoExtension;
import Modelo.Visita;
import ModeloApi.AfeccionDTO;
import ModeloApi.AntecedenteMedicoDTO;
import ModeloApi.EnfermeroDTO;
import ModeloApi.MedicoDTO;
import ModeloApi.ObraSocialDTO;
import ModeloApi.PersonaDTO;
import ModeloApi.SangreDTO;
import ModeloApi.TipoDocumentoDTO;
import ModeloApi.VisitaDTO;

@ExtendWith(MockitoExtension.class)
public class CrearVisitaAdapterTest {
	
	@Mock
	private CrearVisitaInput crearVisitaInput;

	@Test
	public void crearVisita_CreaVisitaCorrectamente_ReturnTrue() throws PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		CrearVisitaAdapter crearVisitaAdapter = new CrearVisitaAdapter(crearVisitaInput);
		MedicoDTO medico = new MedicoDTO(1,"torres","geerman",12015,"as212321");
        EnfermeroDTO enfermero = new EnfermeroDTO(1,"torres","geerman",12015,"as212321");
		VisitaDTO visita = new VisitaDTO(1, 1452, factoryPersona(), LocalDateTime.of(2018,8,7,10,1),"Dolores columna", "Espina bifida", "110/80",36.5f,45,82,"Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
		when(crearVisitaInput.crearVisita(any(Visita.class))).thenReturn(true);
		boolean resultado = crearVisitaAdapter.crearVisita(visita);
		Assertions.assertEquals(true, resultado);
	}
	
	public PersonaDTO factoryPersona() {
        return new PersonaDTO(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumentoDTO(1, "DNI"),
		        "30672405", new SangreDTO(1, "A", "RH+"), "3825674978", new ObraSocialDTO(1, "ASDA"), "", 0, factoryAntecedenteMedico());

    }
	
	 private Collection<AntecedenteMedicoDTO> factoryAntecedenteMedico() {
		 AntecedenteMedicoDTO dislexia = new AntecedenteMedicoDTO(1, new AfeccionDTO(1, "Dislexia"), "Cronica");
		 AntecedenteMedicoDTO gonorrea = new AntecedenteMedicoDTO(2, new AfeccionDTO(1, "gonorrea"), "Cronica Tambien");
		 AntecedenteMedicoDTO diabetes = new AntecedenteMedicoDTO(3, new AfeccionDTO(1, "diabetes"), "nerviosa");

	        Collection<AntecedenteMedicoDTO> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

	        return listaAntecedentes;
	    }

}
