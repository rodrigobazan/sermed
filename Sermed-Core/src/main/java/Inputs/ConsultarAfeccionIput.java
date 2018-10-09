package Inputs;

import Excepciones.AfeccionNoExisteException;
import Modelo.Afeccion;

import java.util.Collection;

public interface ConsultarAfeccionIput {

    Collection<Afeccion> consultarAfecciones();

    Collection<Afeccion> consultarAfeccionesPorNombre(String afeccion);

    Afeccion consultarAfeccionPorNombre(String afeccion) throws AfeccionNoExisteException;
}
