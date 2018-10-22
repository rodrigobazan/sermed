package ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Modelo.Persona;
import Modelo.Visita;
import Repositorio.IVisitaRepositorio;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.VisitaEntity;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioData.IVisitaRepositorioCRUD;

@Service
public class VisitaRepositorioImplementacion implements IVisitaRepositorio{

	@Autowired
	IVisitaRepositorioCRUD iVisitaRepositorioCRUD;
	
	@Autowired
	PersonaRepositorioImplementacion personaRepositorioImplementacion;
	
	@Autowired
	MedicoRepositorioImplementacion medicoRepositorioImplementacion; 
	
	@Autowired
	EnfermeroRepositorioImplementacion enfermeroRepositorioImplementacion;
	
	@Override
	@Transactional
	public boolean persist(Visita visita) {
		return iVisitaRepositorioCRUD.save(mapeoCoreData(visita)) !=null;
	}

	@Override
	@Transactional(readOnly=true)
	public Collection<Visita> findAll() {
		List<Visita> visitas = new ArrayList<>();
		this.iVisitaRepositorioCRUD.findAll().forEach(visitaEntity -> visitas.add(mapeoDataCore(visitaEntity)));
		return visitas;
	}

	@Override
	@Transactional(readOnly=true)
	public Visita findbyNumero(int numeroVisita) {
		VisitaEntity entity = iVisitaRepositorioCRUD.findByNumerovisita(numeroVisita);
		if(entity != null) return mapeoDataCore(entity);
		return null;		
	}


	@Override
	@Transactional(readOnly=true)
	public Collection<Visita> findByPersona(Persona laPersona) {
		List<Visita> visitas = new ArrayList<>();
		this.iVisitaRepositorioCRUD.findByPacienteIdPersona(laPersona.getIdPersona()).forEach(p -> visitas.add(mapeoDataCore(p)));
		return visitas;
	}
	
	private VisitaEntity mapeoCoreData(Visita visita) {
		if(visita.getIdVisita() != null) {
			return this.iVisitaRepositorioCRUD.findByNumerovisita(visita.getNumeroVisita());
		} else {
			return new VisitaEntity(visita.getNumeroVisita(), personaRepositorioImplementacion.mapeoCoreData(visita.getPaciente()),
					visita.getFechaHoraVisita(), visita.getMotivoConsulta(), visita.getAntecedentesPatologicos(), visita.getTensionArterial(),
					visita.getTemperatura(), visita.getFrecuenciaCardiaca(), visita.getSaturacionOxigeno(), visita.getMedicacionHabitual(),
					visita.getExamenClinico(), visita.getDiagnosticoPresuntivo(), visita.getTratamiento(), visita.getObservaciones(),
					medicoRepositorioImplementacion.mapeoCoreData(visita.getMedico()), enfermeroRepositorioImplementacion.mapeoCoreData(visita.getEnfermero()));
		}
		
	}
	
	private Visita mapeoDataCore(VisitaEntity visitaEntity) {
		return new Visita(visitaEntity.getIdvisita(),visitaEntity.getNumerovisita(), personaRepositorioImplementacion.mapeoDataCore(visitaEntity.getPaciente()), visitaEntity.getFechaHoraVisita()
				, visitaEntity.getMotivoConsulta(), visitaEntity.getAntecedentesPatologicos(), visitaEntity.getTensionArterial(), visitaEntity.getTemperatura()
				, visitaEntity.getFrecuenciaCardiaca(), visitaEntity.getSaturacionOxigeno(), visitaEntity.getMedicacionHabitual(), visitaEntity.getExamenClinico(), visitaEntity.getDiagnosticoPresuntivo()
				, visitaEntity.getTratamiento(), visitaEntity.getObservaciones(), medicoRepositorioImplementacion.mapeoDataCore(visitaEntity.getMedico()),enfermeroRepositorioImplementacion.mapeoDataCore(visitaEntity.getEnfermero()));
	}

}
