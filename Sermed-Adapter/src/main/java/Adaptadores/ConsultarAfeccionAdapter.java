package Adaptadores;

import Excepciones.AfeccionNoExisteException;
import Inputs.ConsultarAfeccionIput;
import Modelo.Afeccion;
import ModeloApi.AfeccionDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultarAfeccionAdapter {
    private ConsultarAfeccionIput consultarAfeccionIput;

    public ConsultarAfeccionAdapter(ConsultarAfeccionIput consultarAfeccionIput) {
        this.consultarAfeccionIput = consultarAfeccionIput;
    }

    public List<AfeccionDTO> consultarAfecciones() {
        List<AfeccionDTO> afecciones = new ArrayList<>();
        consultarAfeccionIput.consultarAfecciones().forEach(afeccion -> afecciones.add(mapeoCoreDTO(afeccion)));
        return afecciones;
    }

    public List<AfeccionDTO> consultarAfeccionesPorNombre(String nombre){
        List<AfeccionDTO> afecciones = new ArrayList<>();
        consultarAfeccionIput.consultarAfeccionesPorNombre(nombre).forEach(afeccion -> afecciones.add(mapeoCoreDTO(afeccion)));
        return afecciones;
    }

    public AfeccionDTO consultarAfeccionPorNombre(String nombre) throws AfeccionNoExisteException {
        return mapeoCoreDTO(consultarAfeccionIput.consultarAfeccionPorNombre(nombre));
    }


    private AfeccionDTO mapeoCoreDTO(Afeccion afeccion) {
        return new AfeccionDTO(afeccion.getIdAfeccion(), afeccion.getNombreAfeccion());
    }

}
