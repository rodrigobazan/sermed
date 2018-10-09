package Inputs;

import java.time.LocalDate;
import java.util.List;

import Excepciones.VisitaNoExisteException;
import Modelo.Visita;

public interface ConsultarVisitaInput {

	List<Visita> consultarVisitas();
	
	List<Visita> consultarVisitasEntreFechas(LocalDate desde, LocalDate hasta);
	
	Visita consultarVisitaPorNumero(int numero) throws VisitaNoExisteException;
}
