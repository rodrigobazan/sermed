package Adaptadores;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaExisteException;
import Excepciones.PersonaIncompletaException;
import Excepciones.UpdatePersonaException;
import Factorys.PersonaFactory;
import Inputs.ModificarPersonaInput;
import ModeloApi.PersonaDTO;

public class ModificarPersonaAdapter {

	private ModificarPersonaInput modificarPersonaInput;

	public ModificarPersonaAdapter(ModificarPersonaInput modificarPersonaInput) {
		super();
		this.modificarPersonaInput = modificarPersonaInput;
	}

	public PersonaDTO modificarPersona(PersonaDTO personaDTO) throws PersonaIncompletaException, PersonaExisteException, UpdatePersonaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		return PersonaFactory.mapeoCoreDTO(modificarPersonaInput.modificarPersona(PersonaFactory.mapeoDTOCore(personaDTO)));
	}
	
	
}
