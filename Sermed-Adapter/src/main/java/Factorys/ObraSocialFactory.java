package Factorys;

import Modelo.ObraSocial;
import ModeloApi.ObraSocialDTO;

public class ObraSocialFactory {

    private ObraSocialFactory() {
    }

    static public ObraSocialDTO mapeoCoreDTO(ObraSocial obraSocial) {
        return new ObraSocialDTO(obraSocial.getIdObraSocial(), obraSocial.getNombre());
    }
}
