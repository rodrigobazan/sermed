package Adaptadores;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaExisteException;
import Excepciones.PersonaIncompletaException;
import Factorys.PersonaFactory;
import Inputs.CrearMedicoInput;
import Inputs.CrearPersonaInput;
import Modelo.Persona;
import ModeloApi.PersonaDTO;

public class CrearPersonaAdapter {
    private CrearPersonaInput crearPersonaInput;

    public CrearPersonaAdapter(CrearPersonaInput crearPersonaInput) {
        this.crearPersonaInput = crearPersonaInput;
    }

    public boolean crearPersona(PersonaDTO persona) throws PersonaExisteException, NumeroAfiliadoIncorrectoException, PersonaIncompletaException, DniConPuntosException {
        return crearPersonaInput.crearPersona(PersonaFactory.mapeoDTOCore(persona));
    }

}
