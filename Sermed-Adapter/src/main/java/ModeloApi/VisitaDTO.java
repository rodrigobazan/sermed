package ModeloApi;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import Modelo.Enfermero;
import Modelo.Medico;
import Modelo.Persona;

public class VisitaDTO {

	@JsonProperty("elPaciente")
    public final PersonaDTO elPaciente;
	
	@JsonProperty("fechaHoraVisita")
    public final LocalDateTime fechaHoraVisita;
	
	@JsonProperty("motivoConsulta")
    public final String motivoConsulta;
	
	@JsonProperty("idVisita")
    public final Integer idVisita;
	
	@JsonProperty("antecedentesPatologicos")
    public final String antecedentesPatologicos;
	
	@JsonProperty("tensionArterial")
    public final String tensionArterial;
	
	@JsonProperty("numeroVisita")
    public final int numeroVisita;
	
	@JsonProperty("temperatura")
    public final float temperatura;
	
	@JsonProperty("frecuenciaCardiaca")
    public final int frecuenciaCardiaca;
	
	@JsonProperty("saturacionOxigeno")
    public final int saturacionOxigeno;
	
	@JsonProperty("medicacionHabitual")
    public final String medicacionHabitual;
	
	@JsonProperty("examenClinico")
    public final String examenClinico;
	
	@JsonProperty("diagnosticoPresuntivo")
    public final String diagnosticoPresuntivo;
	
	@JsonProperty("tratamiento")
    public final String tratamiento;
	
	@JsonProperty("observaciones")
    public final String observaciones;
	
	@JsonProperty("medico")
    public final MedicoDTO medico;
	
	@JsonProperty("enfermero")
    public final EnfermeroDTO enfermero;

	public VisitaDTO(@JsonProperty("idVisita") Integer idVisita,  @JsonProperty("numeroVisita") int numeroVisita, @JsonProperty("elPaciente") PersonaDTO personaDTO, @JsonProperty("fechaHoraVisita") LocalDateTime fechaHoraVisita, @JsonProperty("motivoConsulta") String motivoConsulta, 
			@JsonProperty("antecedentesPatologicos") String antecedentesPatologicos, @JsonProperty("tensionArterial") String tensionArterial, @JsonProperty("temperatura") float temperatura,
			@JsonProperty("frecuenciaCardiaca") int frecuenciaCardiaca, @JsonProperty("saturacionOxigeno") int saturacionOxigeno, @JsonProperty("medicacionHabitual") String medicacionHabitual, @JsonProperty("examenClinico") String examenClinico,
			@JsonProperty("diagnosticoPresuntivo") String diagnosticoPresuntivo, 	@JsonProperty("tratamiento") String tratamiento, @JsonProperty("observaciones") String observaciones, @JsonProperty("medico") MedicoDTO medico,
			@JsonProperty("enfermero") EnfermeroDTO enfermero) {
		super();
		this.idVisita = idVisita;
		this.numeroVisita = numeroVisita;
		this.elPaciente = personaDTO;
		this.fechaHoraVisita = fechaHoraVisita;
		this.motivoConsulta = motivoConsulta;		
		this.antecedentesPatologicos = antecedentesPatologicos;
		this.tensionArterial = tensionArterial;		
		this.temperatura = temperatura;
		this.frecuenciaCardiaca = frecuenciaCardiaca;
		this.saturacionOxigeno = saturacionOxigeno;
		this.medicacionHabitual = medicacionHabitual;
		this.examenClinico = examenClinico;
		this.diagnosticoPresuntivo = diagnosticoPresuntivo;
		this.tratamiento = tratamiento;
		this.observaciones = observaciones;
		this.medico = medico;
		this.enfermero = enfermero;
	}
	

	
}
