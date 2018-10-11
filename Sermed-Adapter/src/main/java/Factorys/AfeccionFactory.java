package Factorys;

import Modelo.Afeccion;
import ModeloApi.AfeccionDTO;

public class AfeccionFactory {

    private AfeccionFactory() {
    }

    public static AfeccionDTO mapeoCoreDTO(Afeccion afeccion) {
        return new AfeccionDTO(afeccion.getIdAfeccion(), afeccion.getNombreAfeccion());
    }

    public static Afeccion mapeoDTOCore(AfeccionDTO afeccionDTO){
        return new Afeccion(afeccionDTO.idAfeccion, afeccionDTO.nombreAfeccion);
    }
}
