package Inputs;

import Excepciones.PersonaNoExisteException;
import Modelo.Persona;

import java.util.Collection;

public interface ConsultarPersonaInput {

    Collection<Persona> consultarPersonas();

    Collection<Persona> consultarPersonasPorApellido(String apellido);

    Persona consultarPersonaPorNumeroAfiliado(String numeroAfiliado, Integer Ordern) throws PersonaNoExisteException;
}
