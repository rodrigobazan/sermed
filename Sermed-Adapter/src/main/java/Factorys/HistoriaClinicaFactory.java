package Factorys;

import java.util.ArrayList;
import java.util.List;

import Modelo.AntecedenteMedico;
import Modelo.Visita;
import ModeloApi.AntecedenteMedicoDTO;
import ModeloApi.HistoriaClinicaDTO;
import ModeloApi.VisitaDTO;
import ModeloReporte.HistoriaClinicaPersonaDTO;

public class HistoriaClinicaFactory {

	private HistoriaClinicaFactory() {}

	public static HistoriaClinicaDTO mapeoCoreDTO(HistoriaClinicaPersonaDTO generarHistoriaClinicaPersona) {
		List<VisitaDTO> visitasDTO = new ArrayList<>();
		List<Visita> visitas = generarHistoriaClinicaPersona.getVisitasDeLaPersona();
		if(!visitas.isEmpty()) {
			visitas.stream().forEach(visita ->{
				visitasDTO.add(VisitaFactory.mapeoCoreDTO(visita));
			});
		}
		List<AntecedenteMedico> antecedentes = generarHistoriaClinicaPersona.getAntecedenteMedicos();
		List<AntecedenteMedicoDTO> antecedentesDTO = (List<AntecedenteMedicoDTO>) AntecedenteMedicoFactory.mapeoCoreDTO(antecedentes);
		return new HistoriaClinicaDTO(PersonaFactory.mapeoCoreDTO(generarHistoriaClinicaPersona.getPersonaBuscada()),visitasDTO, antecedentesDTO); 
				
	}
	
}
