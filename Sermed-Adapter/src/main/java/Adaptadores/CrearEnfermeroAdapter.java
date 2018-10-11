package Adaptadores;

import Excepciones.EnfermeroExisteException;
import Factorys.EnfermeroFactory;
import Inputs.CrearEnfermeroInput;
import ModeloApi.EnfermeroDTO;

public class CrearEnfermeroAdapter {

    private CrearEnfermeroInput crearEnfermeroInput;


    public CrearEnfermeroAdapter(CrearEnfermeroInput crearEnfermeroInput) {
        this.crearEnfermeroInput = crearEnfermeroInput;
    }

    public boolean crearEnfermero(EnfermeroDTO enfermeroDTO) throws EnfermeroExisteException {
        return crearEnfermeroInput.crearEnfermero(EnfermeroFactory.mapeoDTOCore(enfermeroDTO));
    }

}
