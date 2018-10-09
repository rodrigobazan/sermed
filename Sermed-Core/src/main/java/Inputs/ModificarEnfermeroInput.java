package Inputs;

import Excepciones.EnfermeroIncompletoException;
import Excepciones.MatriculasIgualesException;
import Excepciones.UpdateEnfermeroException;
import Modelo.Enfermero;

public interface ModificarEnfermeroInput {

	Enfermero modificarEnfermero (Enfermero enfermero) throws MatriculasIgualesException, EnfermeroIncompletoException, UpdateEnfermeroException;
}
