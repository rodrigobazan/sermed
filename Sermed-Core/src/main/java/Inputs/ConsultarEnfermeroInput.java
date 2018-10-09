package Inputs;

import java.util.List;

import Excepciones.EnfermeroNoExisteException;
import Modelo.Enfermero;

public interface ConsultarEnfermeroInput {

	List<Enfermero> consultarEnfermeros();

	List<Enfermero> consultarEnfermerosPorApellido(String apellido);
	
	Enfermero consultarEnfermeroPorMatricula(int matricula) throws EnfermeroNoExisteException;
}
