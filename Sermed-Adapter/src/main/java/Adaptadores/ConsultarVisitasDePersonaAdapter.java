package Adaptadores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Factorys.PersonaFactory;
import Factorys.VisitaFactory;
import Inputs.ConsultarVisitasDePersonaInput;
import Modelo.Visita;
import ModeloApi.PersonaDTO;
import ModeloApi.VisitaDTO;

public class ConsultarVisitasDePersonaAdapter {

	private ConsultarVisitasDePersonaInput consultarVisitasDePersonaInput;

	public ConsultarVisitasDePersonaAdapter(ConsultarVisitasDePersonaInput consultarVisitasDePersonaInput) {
		super();
		this.consultarVisitasDePersonaInput = consultarVisitasDePersonaInput;
	}

	public List<VisitaDTO> consultarVisitasDePersona(PersonaDTO persona)
			throws PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		List<Visita> visitas = consultarVisitasDePersonaInput.consultarVisitasPersona(PersonaFactory.mapeoDTOCore(persona));
		List<VisitaDTO> visitasDTO = new ArrayList<>();
		if (!visitas.isEmpty()) {
			visitas.stream().forEach(visita -> {
				visitasDTO.add(VisitaFactory.mapeoCoreDTO(visita));
			});
		}
		return visitasDTO;
	}

	public List<VisitaDTO> consultarVisitasPersonaPorFechas(PersonaDTO persona, LocalDate fechaDesde,
			LocalDate fechaHasta) throws PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		List<Visita> visitas = consultarVisitasDePersonaInput.consultarVisitasPersonaPorFechas(PersonaFactory.mapeoDTOCore(persona),fechaDesde,fechaHasta);
		List<VisitaDTO> visitasDTO = new ArrayList<>();
		if (!visitas.isEmpty()) {
			visitas.stream().forEach(visita -> {
				visitasDTO.add(VisitaFactory.mapeoCoreDTO(visita));
			});
		}
		return visitasDTO;
	}

}
