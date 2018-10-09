package Inputs;

import Excepciones.PersonaNoExisteException;
import ModeloReporte.HistoriaClinicaPersonaDTO;

public interface GenerarHistoriaClinicaPersonaInput {
	
	HistoriaClinicaPersonaDTO generarHistoriaClinicaPersona(String numeroAfiliado, Integer orden) throws PersonaNoExisteException;

}
