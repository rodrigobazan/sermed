package Adaptadores;

import Factorys.SangreFactory;
import Inputs.ConsultarSangreInput;
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
        this.consultarSangreInput.consultarSangre().forEach(sangre -> sangreDTOS.add(SangreFactory.mapeoCoreDTO(sangre)));
        return sangreDTOS;
    }

    public List<SangreDTO> consultarSangrePorGrupo(String grupo){
        List<SangreDTO> sangreDTOS = new ArrayList<>();
        this.consultarSangreInput.consultarSangrePorGrupo(grupo).forEach(sangre -> sangreDTOS.add(SangreFactory.mapeoCoreDTO(sangre)));
        return sangreDTOS;
    }

    public List<SangreDTO> consultarSangrePorFactor(String factor){
        List<SangreDTO> sangreDTOS = new ArrayList<>();
        this.consultarSangreInput.consultarSangrePorFactor(factor).forEach(sangre -> sangreDTOS.add(SangreFactory.mapeoCoreDTO(sangre)));
        return sangreDTOS;
    }

    public SangreDTO consultarSangrePorGrupoFactor (String grupo, String factor){
        return SangreFactory.mapeoCoreDTO(this.consultarSangreInput.consultarSangrePorGrupoFactor(grupo, factor));
    }


}