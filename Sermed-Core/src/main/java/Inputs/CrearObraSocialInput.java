package Inputs;

import Excepciones.ObraSocialExisteException;
import Modelo.ObraSocial;

public interface CrearObraSocialInput {
	
	boolean crearObraSocial(ObraSocial obraSocial) throws ObraSocialExisteException;

}
