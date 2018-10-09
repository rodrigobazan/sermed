package Inputs;

import Excepciones.NombreObraSocialExisteException;
import Excepciones.UpdateObraSocialException;
import Modelo.ObraSocial;

public interface ModificarObraSocialInput {

	ObraSocial modificarObraSocial(ObraSocial obraSocial) throws NombreObraSocialExisteException, UpdateObraSocialException;
}
