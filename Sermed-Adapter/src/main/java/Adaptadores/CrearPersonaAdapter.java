package Adaptadores;

import Inputs.CrearMedicoInput;
import Inputs.CrearPersonaInput;
import Modelo.Persona;
import ModeloApi.PersonaDTO;

public class CrearPersonaAdapter {
    private CrearPersonaInput crearPersonaInput;

    public CrearPersonaAdapter(CrearPersonaInput crearPersonaInput) {
        this.crearPersonaInput = crearPersonaInput;
    }

    /*public boolean crearPersona(PersonaDTO persona) {
        return crearPersonaInput.crearPersona(mapeoDTOCore(persona));
    }*/

}
