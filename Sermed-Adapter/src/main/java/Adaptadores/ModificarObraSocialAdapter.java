package Adaptadores;

import Excepciones.NombreObraSocialExisteException;
import Excepciones.UpdateObraSocialException;
import Inputs.ModificarObraSocialInput;
import Modelo.ObraSocial;
import ModeloApi.ObraSocialDTO;

public class ModificarObraSocialAdapter {

	private ModificarObraSocialInput modificarObraSocialInput;

	public ModificarObraSocialAdapter(ModificarObraSocialInput modificarObraSocialInput) {
		super();
		this.modificarObraSocialInput = modificarObraSocialInput;
	}

	public ObraSocialDTO modificarObraSocial(ObraSocialDTO obraSocialDTO) throws NombreObraSocialExisteException, UpdateObraSocialException {
		return mapeoCoreDTO(modificarObraSocialInput.modificarObraSocial(mapeoDTOCore(obraSocialDTO)));
	}

	private ObraSocialDTO mapeoCoreDTO(ObraSocial obraSocial) {
		return new ObraSocialDTO(obraSocial.getIdObraSocial(), obraSocial.getNombre());
	}

	private ObraSocial mapeoDTOCore(ObraSocialDTO obraSocialDTO) {
		return new ObraSocial(obraSocialDTO.idObraSocial,obraSocialDTO.obraSocial);
	}
	
	
	
}
