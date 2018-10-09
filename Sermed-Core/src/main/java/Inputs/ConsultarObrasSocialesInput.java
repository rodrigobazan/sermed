package Inputs;

import java.util.List;

import Excepciones.ObraSocialNoExisteException;
import Modelo.ObraSocial;

public interface ConsultarObrasSocialesInput {

	List<ObraSocial> consultarObrasSociales();
	
	List<ObraSocial> consultarObrasSocialesPorNombre(String nombre);
	
	ObraSocial consultarObraSocialPorNombre(String nombre) throws ObraSocialNoExisteException;

}
