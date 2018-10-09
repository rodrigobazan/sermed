package Inputs;

import Excepciones.MedicoExisteException;
import Modelo.Medico;

public interface CrearMedicoInput {
	
	boolean crearMedico(Medico medico) throws MedicoExisteException;

}
