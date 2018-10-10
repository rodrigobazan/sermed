package Adaptadores;

import Excepciones.MatriculasIgualesException;
import Excepciones.MedicoIncompletoException;
import Excepciones.UpdateMedicoException;
import Inputs.ModificarMedicoInput;
import Modelo.Medico;
import ModeloApi.MedicoDTO;

public class ModificarMedicoAdapter {
    private ModificarMedicoInput modificarMedicoInput;

    public ModificarMedicoAdapter(ModificarMedicoInput modificarMedicoInput) {
        this.modificarMedicoInput = modificarMedicoInput;
    }

    public MedicoDTO modificarMedico(MedicoDTO medicoDTO) throws UpdateMedicoException, MatriculasIgualesException, MedicoIncompletoException {
        Medico medicoModificado = modificarMedicoInput.modificarMedico(mapeoDTOCore(medicoDTO));
        return mapeoCoreDTO(medicoModificado);
    }

    private MedicoDTO mapeoCoreDTO(Medico medicoModificado) {
        return new MedicoDTO(medicoModificado.getIdMedico(), medicoModificado.getApellido(), medicoModificado.getNombre(), medicoModificado.getMatricula(), medicoModificado.getTelefono());
    }

    private Medico mapeoDTOCore(MedicoDTO medicoDTO) {
        return new Medico(medicoDTO.idMedico, medicoDTO.apellido, medicoDTO.nombre, medicoDTO.matricula, medicoDTO.telefono);
    }
}
