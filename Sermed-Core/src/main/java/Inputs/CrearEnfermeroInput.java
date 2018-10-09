package Inputs;

import Excepciones.EnfermeroExisteException;
import Modelo.Enfermero;

public interface CrearEnfermeroInput {

    boolean crearEnfermero(Enfermero enfermero) throws EnfermeroExisteException;
}
