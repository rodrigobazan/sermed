package Factorys;

import Modelo.Afeccion;
import ModeloApi.AfeccionDTO;

public class AfeccionFactory {

    private AfeccionFactory() {
    }

    static public AfeccionDTO mapeoCoreDTO(Afeccion afeccion) {
        return new AfeccionDTO(afeccion.getIdAfeccion(), afeccion.getNombreAfeccion());
    }
}
