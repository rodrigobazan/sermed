package Adaptadores;

import java.util.ArrayList;
import java.util.List;

import Excepciones.ObraSocialNoExisteException;
import Inputs.ConsultarObrasSocialesInput;
import Modelo.ObraSocial;
import ModeloApi.ObraSocialDTO;

public class ConsultarObrasSocialesAdapter {

	private ConsultarObrasSocialesInput consultarObrasSocialesInput;

	public ConsultarObrasSocialesAdapter(ConsultarObrasSocialesInput consultarObrasSocialesInput) {
		super();
		this.consultarObrasSocialesInput = consultarObrasSocialesInput;
	}

	public List<ObraSocialDTO> consultarObrasSociales() {
		List<ObraSocial> obrasSociales = consultarObrasSocialesInput.consultarObrasSociales();
		List<ObraSocialDTO> obrasSocialesDTO = new ArrayList<>();
		if (!obrasSociales.isEmpty()) {
			obrasSociales.stream().forEach(obraSocial -> {
				obrasSocialesDTO.add(mapeoCoreDTO(obraSocial));
			});
		}
		return obrasSocialesDTO;
	}

	public List<ObraSocialDTO> consultarObrasSocialesPorNombre(String nombre) {
		List<ObraSocial> obrasSociales = consultarObrasSocialesInput.consultarObrasSocialesPorNombre(nombre);
		List<ObraSocialDTO> obrasSocialesDTO = new ArrayList<>();
		if (!obrasSociales.isEmpty()) {
			obrasSociales.stream().forEach(obraSocial -> {
				obrasSocialesDTO.add(mapeoCoreDTO(obraSocial));
			});
		}
		return obrasSocialesDTO;
	}

	public ObraSocialDTO consultarObraSocialPorNombre(String nombre) throws ObraSocialNoExisteException {
		return mapeoCoreDTO(consultarObrasSocialesInput.consultarObraSocialPorNombre(nombre));
	}
	
	private ObraSocialDTO mapeoCoreDTO(ObraSocial obraSocial) {
		return new ObraSocialDTO(obraSocial.getIdObraSocial(), obraSocial.getNombre());
	}



}
