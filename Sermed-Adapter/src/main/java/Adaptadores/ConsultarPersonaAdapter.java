package Adaptadores;

import java.util.ArrayList;
import java.util.List;

import Excepciones.PersonaNoExisteException;
import Factorys.PersonaFactory;
import Inputs.ConsultarPersonaInput;
import Modelo.Persona;
import ModeloApi.PersonaDTO;

public class ConsultarPersonaAdapter {

	private ConsultarPersonaInput consultarPersonaInput;

	public ConsultarPersonaAdapter(ConsultarPersonaInput consultarPersonaInput) {
		super();
		this.consultarPersonaInput = consultarPersonaInput;
	}

	public List<PersonaDTO> consultarPersonas() {
		List<Persona> personas = (List<Persona>) consultarPersonaInput.consultarPersonas();
		List<PersonaDTO> personasDTO = new ArrayList<>();
		if(!personas.isEmpty()) {
			personas.stream().forEach(persona->{
				personasDTO.add(PersonaFactory.mapeoCoreDTO(persona));
			});
		}
		return personasDTO;
	}

	public List<PersonaDTO> consultarPersonasPorApellido(String apellido) {
		List<Persona> personas = (List<Persona>) consultarPersonaInput.consultarPersonasPorApellido(apellido);
		List<PersonaDTO> personasDTO = new ArrayList<>();
		if(!personas.isEmpty()) {
			personas.stream().forEach(persona->{
				personasDTO.add(PersonaFactory.mapeoCoreDTO(persona));
			});
		}
		return personasDTO;
	}

	public PersonaDTO consultarPersonaPorNumeroAfiliado(String nroAfiliado, int nroOrden) throws PersonaNoExisteException {
		return PersonaFactory.mapeoCoreDTO(consultarPersonaInput.consultarPersonaPorNumeroAfiliado(nroAfiliado, nroOrden));
	}

	
	
	
}
