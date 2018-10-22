package Adaptadores;

import Excepciones.AfeccionNoExisteException;
import Factorys.AfeccionFactory;
import Inputs.ConsultarAfeccionInput;
import ModeloApi.AfeccionDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultarAfeccionAdapter {
    private ConsultarAfeccionInput consultarAfeccionInput;

    public ConsultarAfeccionAdapter(ConsultarAfeccionInput consultarAfeccionInput) {
        this.consultarAfeccionInput = consultarAfeccionInput;
    }

    public List<AfeccionDTO> consultarAfecciones() {
        List<AfeccionDTO> afecciones = new ArrayList<>();
        consultarAfeccionInput.consultarAfecciones().forEach(afeccion -> afecciones.add(AfeccionFactory.mapeoCoreDTO(afeccion)));
        return afecciones;
    }

    public List<AfeccionDTO> consultarAfeccionesPorNombre(String nombre){
        List<AfeccionDTO> afecciones = new ArrayList<>();
        consultarAfeccionInput.consultarAfeccionesPorNombre(nombre).forEach(afeccion -> afecciones.add(AfeccionFactory.mapeoCoreDTO(afeccion)));
        return afecciones;
    }

    public AfeccionDTO consultarAfeccionPorNombre(String nombre) throws AfeccionNoExisteException {
        return AfeccionFactory.mapeoCoreDTO(consultarAfeccionInput.consultarAfeccionPorNombre(nombre));
    }


}
