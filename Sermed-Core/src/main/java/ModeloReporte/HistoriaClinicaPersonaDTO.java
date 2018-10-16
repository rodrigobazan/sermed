package ModeloReporte;

import Modelo.AntecedenteMedico;
import Modelo.Persona;
import Modelo.Visita;

import java.util.List;

public class HistoriaClinicaPersonaDTO {
    private Persona personaBuscada;
    private List<Visita> visitasDeLaPersona;
    private List<AntecedenteMedico> antecedenteMedicos;

    public HistoriaClinicaPersonaDTO(Persona personaBuscada, List<AntecedenteMedico> antecedenteMedicos, List<Visita> visitasDeLaPersona) {
        this.personaBuscada = personaBuscada;
        this.visitasDeLaPersona = visitasDeLaPersona;
        this.antecedenteMedicos = antecedenteMedicos;
    }

	public Persona getPersonaBuscada() {
		return personaBuscada;
	}
	
	public List<Visita> getVisitasDeLaPersona() {
		return visitasDeLaPersona;
	}

	public List<AntecedenteMedico> getAntecedenteMedicos() {
		return antecedenteMedicos;
	}

	
	    
}
