package Inputs;

import java.util.List;

import Excepciones.MedicoNoExisteException;
import Modelo.Medico;

public interface ConsultarMedicoInput {

	List<Medico> consultarMedicos();
	
	List<Medico> consultarMedicosPorApellido(String apellido);
	
	Medico consultarMedicoPorMatricula(int matricula) throws MedicoNoExisteException;
}
