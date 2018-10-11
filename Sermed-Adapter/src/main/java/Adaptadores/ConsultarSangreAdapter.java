package Adaptadores;

import Inputs.ConsultarSangreInput;
import Modelo.Sangre;
import ModeloApi.SangreDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultarSangreAdapter {

    private ConsultarSangreInput consultarSangreInput;

    public ConsultarSangreAdapter(ConsultarSangreInput consultarSangreInput) {
        this.consultarSangreInput = consultarSangreInput;
    }

    public List<SangreDTO> consultarSangre(){
        List<SangreDTO> sangreDTOS = new ArrayList<>();
        this.consultarSangreInput.consultarSangre().forEach(sangre -> sangreDTOS.add(mapeoCoreDtoSangre(sangre)));
        return sangreDTOS;
    }

    public List<SangreDTO> consultarSangrePorGrupo(String grupo){
        List<SangreDTO> sangreDTOS = new ArrayList<>();
        this.consultarSangreInput.consultarSangrePorGrupo(grupo).forEach(sangre -> sangreDTOS.add(mapeoCoreDtoSangre(sangre)));
        return sangreDTOS;
    }

    public List<SangreDTO> consultarSangrePorFactor(String factor){
        List<SangreDTO> sangreDTOS = new ArrayList<>();
        this.consultarSangreInput.consultarSangrePorFactor(factor).forEach(sangre -> sangreDTOS.add(mapeoCoreDtoSangre(sangre)));
        return sangreDTOS;
    }

    public SangreDTO consultarSangrePorGrupoFactor (String grupo, String factor){
        return mapeoCoreDtoSangre(this.consultarSangreInput.consultarSangrePorGrupoFactor(grupo, factor));
    }

    public SangreDTO mapeoCoreDtoSangre (Sangre sangre) {
        return new SangreDTO(sangre.getIdSangre(),sangre.getGrupo(),sangre.getFactor());
    }


}