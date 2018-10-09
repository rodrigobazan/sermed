package Inputs;

import Excepciones.PersonaExisteException;
import Excepciones.PersonaIncompletaException;
import Excepciones.UpdatePersonaException;
import Modelo.Persona;

public interface ModificarPersonaInput {

    Persona modificarPersona(Persona personaDatosNuevos) throws PersonaIncompletaException, PersonaExisteException, UpdatePersonaException;
}
