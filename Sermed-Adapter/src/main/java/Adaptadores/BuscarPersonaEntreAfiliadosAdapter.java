package Adaptadores;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Factorys.PersonaFactory;
import Inputs.BuscarPersonaEntreAfiliadosInput;
import ModeloApi.PersonaDTO;

public class BuscarPersonaEntreAfiliadosAdapter {

	private BuscarPersonaEntreAfiliadosInput buscarPersonaEntreAfiliadosInput;

	public BuscarPersonaEntreAfiliadosAdapter(BuscarPersonaEntreAfiliadosInput buscarPersonaEntreAfiliadosInput) {
		super();
		this.buscarPersonaEntreAfiliadosInput = buscarPersonaEntreAfiliadosInput;
	}

	public boolean existePersonaEntreAfiliados(PersonaDTO persona) throws PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		return buscarPersonaEntreAfiliadosInput.existePersonaPorDNI(PersonaFactory.mapeoDTOCore(persona));
	}
	
	
}
