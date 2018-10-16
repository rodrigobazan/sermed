package Factorys;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Modelo.Visita;
import ModeloApi.VisitaDTO;

public class VisitaFactory {

	public static Visita mapeoDTOCore(VisitaDTO visita) throws PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		return new Visita(visita.idVisita, visita.numeroVisita, PersonaFactory.mapeoDTOCore(visita.elPaciente), visita.fechaHoraVisita, 
				visita.motivoConsulta, visita.antecedentesPatologicos, visita.tensionArterial, visita.temperatura, visita.frecuenciaCardiaca, 
				visita.saturacionOxigeno, visita.medicacionHabitual, visita.examenClinico, visita.diagnosticoPresuntivo, visita.tratamiento, 
				visita.observaciones, MedicoFactory.mapeoDTOCore(visita.medico), EnfermeroFactory.mapeoDTOCore(visita.enfermero));
	}

	public static VisitaDTO mapeoCoreDTO(Visita visita) {
		return new VisitaDTO(visita.getIdVisita(), visita.getNumeroVisita(), PersonaFactory.mapeoCoreDTO(visita.getPaciente()), 
				visita.getFechaHoraVisita(), visita.getMotivoConsulta(), visita.getAntecedentesPatologicos(), visita.getTensionArterial(),
				visita.getTemperatura(), visita.getFrecuenciaCardiaca(), visita.getSaturacionOxigeno(), visita.getMedicacionHabitual(), 
				visita.getExamenClinico(), visita.getDiagnosticoPresuntivo(), visita.getTratamiento(), visita.getObservaciones(),
				MedicoFactory.mapeoCoreDTO(visita.getMedico()), EnfermeroFactory.mapeoCoreDTO(visita.getEnfermero()));
	}

}
