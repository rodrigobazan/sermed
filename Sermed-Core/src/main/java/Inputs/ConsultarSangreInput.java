package Inputs;

import java.util.List;

import Modelo.Sangre;

public interface ConsultarSangreInput {
	
	List<Sangre> consultarSangre();

	List<Sangre> consultarSangrePorGrupo(String grupo);
	
	List<Sangre> consultarSangrePorFactor(String factor);
	
	Sangre consultarSangrePorGrupoFactor(String grupo, String factor);
}
