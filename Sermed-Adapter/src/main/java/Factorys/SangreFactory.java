package Factorys;

import Modelo.Sangre;
import ModeloApi.SangreDTO;

public class SangreFactory {

    private SangreFactory() {
    }

    public static SangreDTO mapeoCoreDTO(Sangre sangre) {
        return new SangreDTO(sangre.getIdSangre(),sangre.getGrupo(),sangre.getFactor());
    }

    public static Sangre mapeoDTOCore(SangreDTO sangre){
        return new Sangre(sangre.idSangre, sangre.grupo, sangre.factor);
    }
}
