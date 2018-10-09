package Adaptadores;

import Excepciones.EnfermeroExisteException;
import Inputs.CrearEnfermeroInput;
import Modelo.Enfermero;
import ModeloApi.EnfermeroDTO;

public class CrearEnfermeroAdapter {

    private CrearEnfermeroInput crearEnfermeroInput;


    public CrearEnfermeroAdapter(CrearEnfermeroInput crearEnfermeroInput) {
        this.crearEnfermeroInput = crearEnfermeroInput;
    }

    public boolean crearEnfermero(EnfermeroDTO enfermeroDTO) throws EnfermeroExisteException {
        return crearEnfermeroInput.crearEnfermero(mapeoAdaptadorCore(enfermeroDTO));
    }


    private Enfermero mapeoAdaptadorCore(EnfermeroDTO enfermeroDTO) {
        return new Enfermero(enfermeroDTO.idEnfermero, enfermeroDTO.apellido, enfermeroDTO.nombre, enfermeroDTO.matricula,
                enfermeroDTO.telefono);
    }
}
