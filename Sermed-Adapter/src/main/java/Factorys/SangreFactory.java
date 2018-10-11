package Factorys;

import Modelo.Sangre;
import ModeloApi.SangreDTO;

public class SangreFactory {

    private SangreFactory() {
    }

    static public SangreDTO mapeoCoreDto (Sangre sangre) {
        return new SangreDTO(sangre.getIdSangre(),sangre.getGrupo(),sangre.getFactor());
    }
}
