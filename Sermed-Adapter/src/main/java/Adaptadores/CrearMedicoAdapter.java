package Adaptadores;

import Excepciones.MedicoExisteException;
import Factorys.MedicoFactory;
import Inputs.CrearMedicoInput;
import ModeloApi.MedicoDTO;

public class CrearMedicoAdapter {
    private CrearMedicoInput crearMedicoInput;

    public CrearMedicoAdapter(CrearMedicoInput crearMedicoInput) {
        this.crearMedicoInput = crearMedicoInput;
    }

    public boolean crearMedico(MedicoDTO medicoDTO) throws MedicoExisteException {
        return crearMedicoInput.crearMedico(MedicoFactory.mapeoDTOCore(medicoDTO));
    }

}
