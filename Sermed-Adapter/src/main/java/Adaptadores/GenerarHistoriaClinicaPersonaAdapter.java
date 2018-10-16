package Adaptadores;

import Excepciones.PersonaNoExisteException;
import Factorys.HistoriaClinicaFactory;
import Inputs.GenerarHistoriaClinicaPersonaInput;
import ModeloApi.HistoriaClinicaReporteDTO;

public class GenerarHistoriaClinicaPersonaAdapter {

	private GenerarHistoriaClinicaPersonaInput generarHistoriaClinicaPersonaInput;

	public GenerarHistoriaClinicaPersonaAdapter(GenerarHistoriaClinicaPersonaInput generarHistoriaClinicaPersonaInput) {
		super();
		this.generarHistoriaClinicaPersonaInput = generarHistoriaClinicaPersonaInput;
	}

	public HistoriaClinicaReporteDTO generarHistoriaClinicaPersona(String numeroAfiliado, int nroOrden) throws PersonaNoExisteException {
		return HistoriaClinicaFactory.mapeoCoreDTO(generarHistoriaClinicaPersonaInput.generarHistoriaClinicaPersona(numeroAfiliado, nroOrden));
	}
	
	
}
