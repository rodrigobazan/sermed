package Inputs;

import java.time.LocalDate;
import java.util.List;

import Modelo.Persona;
import Modelo.Visita;

public interface ConsultarVisitasDePersonaInput {

	List<Visita> consultarVisitasPersona(Persona laPersona);
	
	List<Visita> consultarVisitasPersonaPorFechas(Persona laPersona, LocalDate fechaDesde, LocalDate fechaHasta);
}
