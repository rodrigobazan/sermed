package Inputs;

import Excepciones.PersonaNoAfiliadaException;
import Modelo.Afiliado;
import Modelo.Persona;

public interface DesafiliarPersonaInput {

    boolean desafiliarPersona(Persona persona, Afiliado afiliado) throws PersonaNoAfiliadaException;
}
