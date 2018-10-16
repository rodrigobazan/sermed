package Factorys;

import Modelo.ObraSocial;
import ModeloApi.ObraSocialDTO;

public class ObraSocialFactory {

    private ObraSocialFactory() {
    }

    public static ObraSocialDTO mapeoCoreDTO(ObraSocial obraSocial) {
        return new ObraSocialDTO(obraSocial.getIdObraSocial(), obraSocial.getNombre());
    }

    public static ObraSocial mapeoDTOCore(ObraSocialDTO obraSocialDTO){
        return new ObraSocial(obraSocialDTO.idObraSocial, obraSocialDTO.obraSocial);
    }
}
