package Adaptadores;

import Excepciones.MedicoNoExisteException;
import Factorys.MedicoFactory;
import Inputs.ConsultarMedicoInput;
import Modelo.Medico;
import ModeloApi.MedicoDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultarMedicoAdapter {
    private ConsultarMedicoInput consultarMedicoInput;

    public ConsultarMedicoAdapter(ConsultarMedicoInput consultarMedicoInput) {
        this.consultarMedicoInput = consultarMedicoInput;
    }

    public List<MedicoDTO> consultarMedicos() {
        List<MedicoDTO> listaDeMedicos = new ArrayList<>();
        consultarMedicoInput.consultarMedicos().forEach(medico -> listaDeMedicos.add(MedicoFactory.mapeoCoreDTO(medico)));
        return listaDeMedicos;
    }

    public List<MedicoDTO> consultarMedicosPorApellido(String apellido) {
        List<MedicoDTO> listaMedicos = new ArrayList<>();
        consultarMedicoInput.consultarMedicosPorApellido(apellido).forEach(medico -> listaMedicos.add(MedicoFactory.mapeoCoreDTO(medico)));
        return listaMedicos;
    }

    public MedicoDTO consultarMedicoPorMatricula(int matricula) throws MedicoNoExisteException {
        return MedicoFactory.mapeoCoreDTO(consultarMedicoInput.consultarMedicoPorMatricula(matricula));
    }

}
