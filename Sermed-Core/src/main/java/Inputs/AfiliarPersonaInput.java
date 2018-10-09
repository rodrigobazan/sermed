package Inputs;

import Excepciones.PersonaAfiliadaException;
import Modelo.Afiliado;
import Modelo.Persona;

public interface AfiliarPersonaInput {

    boolean afiliarPersona(Persona persona, Afiliado afiliado) throws PersonaAfiliadaException;
}
