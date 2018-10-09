package Inputs;

import Excepciones.MatriculasIgualesException;
import Excepciones.MedicoIncompletoException;
import Excepciones.UpdateMedicoException;
import Modelo.Medico;

public interface ModificarMedicoInput {
	
	Medico modificarMedico(Medico medico) throws MatriculasIgualesException, MedicoIncompletoException, UpdateMedicoException;

}
