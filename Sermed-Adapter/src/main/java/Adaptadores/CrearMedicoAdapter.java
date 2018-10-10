package Adaptadores;

import Excepciones.MedicoExisteException;
import Inputs.CrearMedicoInput;
import Modelo.Medico;
import ModeloApi.MedicoDTO;

public class CrearMedicoAdapter {
    private CrearMedicoInput crearMedicoInput;

    public CrearMedicoAdapter(CrearMedicoInput crearMedicoInput) {
        this.crearMedicoInput = crearMedicoInput;
    }

    public boolean crearMedico(MedicoDTO medicoDTO) throws MedicoExisteException {
        return crearMedicoInput.crearMedico(mapeoAdapterCore(medicoDTO));
    }

    private Medico mapeoAdapterCore(MedicoDTO medicoDTO) {
        return new Medico(medicoDTO.idMedico, medicoDTO.apellido, medicoDTO.nombre, medicoDTO.matricula, medicoDTO.telefono);
    }

}
