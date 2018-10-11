package Factorys;

import Modelo.Medico;
import ModeloApi.MedicoDTO;

public class MedicoFactory {

    private MedicoFactory() {
    }

    public static Medico mapeoDTOCore(MedicoDTO medicoDTO) {
        return new Medico(medicoDTO.idMedico, medicoDTO.apellido, medicoDTO.nombre, medicoDTO.matricula, medicoDTO.telefono);
    }

    public static MedicoDTO mapeoCoreDTO(Medico medico) {
        return new MedicoDTO(medico.getIdMedico(), medico.getApellido(), medico.getNombre(), medico.getMatricula(), medico.getTelefono());
    }
}
