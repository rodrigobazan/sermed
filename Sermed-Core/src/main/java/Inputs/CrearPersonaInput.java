package Inputs;

import Excepciones.PersonaExisteException;
import Modelo.Persona;

public interface CrearPersonaInput {

    boolean crearPersona(Persona persona) throws PersonaExisteException;
}
