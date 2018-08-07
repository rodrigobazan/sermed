package ModeloReporte;

import Modelo.AntecedenteMedico;
import Modelo.Persona;
import Modelo.Visita;

import java.util.List;

public class HistoriaClinicaPersonaDTO {
    private Persona personaBuscada;
    private List<Visita> visitasDeLaPersona;

    public HistoriaClinicaPersonaDTO(Persona personaBuscada, List<AntecedenteMedico> antecedenteMedicos, List<Visita> visitasDeLaPersona) {
        this.personaBuscada = personaBuscada;
        this.visitasDeLaPersona = visitasDeLaPersona;
    }
}
