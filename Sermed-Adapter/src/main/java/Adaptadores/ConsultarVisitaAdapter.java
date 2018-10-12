package Adaptadores;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Excepciones.VisitaNoExisteException;
import Factorys.VisitaFactory;
import Inputs.ConsultarVisitaInput;
import Modelo.Visita;
import ModeloApi.VisitaDTO;

public class ConsultarVisitaAdapter {

	private ConsultarVisitaInput consultarVisitaInput;

	public ConsultarVisitaAdapter(ConsultarVisitaInput consultarVisitaInput) {
		super();
		this.consultarVisitaInput = consultarVisitaInput;
	}

	public List<VisitaDTO> consultarVisitas() {
		List<Visita> visitas = consultarVisitaInput.consultarVisitas();
		List<VisitaDTO> visitasDTO = new ArrayList<>();
		if(!visitas.isEmpty()) {
			visitas.stream().forEach(visita->{
				visitasDTO.add(VisitaFactory.mapeoCoreDTO(visita));
			});
		}
		return visitasDTO;
	}

	public List<VisitaDTO> consultarVisitasEntreFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
		List<Visita> visitas = consultarVisitaInput.consultarVisitasEntreFechas(fechaDesde, fechaHasta);
		List<VisitaDTO> visitasDTO = new ArrayList<>();
		if(!visitas.isEmpty()) {
			visitas.stream().forEach(visita->{
				visitasDTO.add(VisitaFactory.mapeoCoreDTO(visita));
			});
		}
		return visitasDTO;
	}

	public VisitaDTO consultarVisitaPorNumero(int numero) throws VisitaNoExisteException {
		return VisitaFactory.mapeoCoreDTO(consultarVisitaInput.consultarVisitaPorNumero(numero));
	}
	
	
}
