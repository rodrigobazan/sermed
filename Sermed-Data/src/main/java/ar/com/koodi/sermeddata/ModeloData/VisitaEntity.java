package ar.com.koodi.sermeddata.ModeloData;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.springframework.data.annotation.Reference;


@Entity(name = "visita")
@SequenceGenerator(name = "visita_idvisita_seq", initialValue = 1, sequenceName = "visita_idvisita_seq", allocationSize = 1)
public class VisitaEntity {

	@Id
	@Column(name = "idvisita")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "visita_idvisita_seq")
	private Integer idvisita;

	@Column(name = "numerovisita")
	private int numerovisita;

	@ManyToOne
	@JoinColumn(name="idpersona", referencedColumnName="idpersona")
	private PersonaEntity paciente;

	@Column(name = "fechahoravisita")
	private LocalDateTime fechaHoraVisita;

	@Column(name = "motivoconsulta")
	private String motivoConsulta;

	@Column(name = "antecedentespatologicos")
	private String antecedentesPatologicos;

	@Column(name = "tesionarterial")
	private String tensionArterial;

	@Column(name = "temperatura")
	private float temperatura;

	@Column(name = "frecuenciacardiaca")
	private int frecuenciaCardiaca;

	@Column(name = "saturacionoxigeno")
	private int saturacionOxigeno;

	@Column(name = "medicacionhabitual")
	private String medicacionHabitual;

	@Column(name = "examenclinico")
	private String examenClinico;

	@Column(name = "diagnosticopresuntivo")
	private String diagnosticoPresuntivo;

	@Column(name = "tratamiento")
	private String tratamiento;

	@Column(name = "observaciones")
	private String observaciones;

	@ManyToOne
	@JoinColumn(name="idmedico", referencedColumnName="idmedico")
	private MedicoEntity medico;

	@ManyToOne
	@JoinColumn(name="idenfermero", referencedColumnName="idenfermero")
	private EnfermeroEntity enfermero;
	
	public VisitaEntity() {}

	public VisitaEntity(int numerovisita, PersonaEntity paciente, LocalDateTime fechaHoraVisita,
			String motivoConsulta, String antecedentesPatologicos, String tensionArterial, float temperatura,
			int frecuenciaCardiaca, int saturacionOxigeno, String medicacionHabitual, String examenClinico,
			String diagnosticoPresuntivo, String tratamiento, String observaciones, MedicoEntity medico,
			EnfermeroEntity enfermero) {	
		this.numerovisita = numerovisita;
		this.paciente = paciente;
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

	public Integer getIdvisita() {
		return idvisita;
	}

	public void setIdvisita(Integer idvisita) {
		this.idvisita = idvisita;
	}

	public int getNumerovisita() {
		return numerovisita;
	}

	public void setNumerovisita(int numerovisita) {
		this.numerovisita = numerovisita;
	}

	public PersonaEntity getPaciente() {
		return paciente;
	}

	public void setPaciente(PersonaEntity paciente) {
		this.paciente = paciente;
	}

	public LocalDateTime getFechaHoraVisita() {
		return fechaHoraVisita;
	}

	public void setFechaHoraVisita(LocalDateTime fechaHoraVisita) {
		this.fechaHoraVisita = fechaHoraVisita;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public String getAntecedentesPatologicos() {
		return antecedentesPatologicos;
	}

	public void setAntecedentesPatologicos(String antecedentesPatologicos) {
		this.antecedentesPatologicos = antecedentesPatologicos;
	}

	public String getTensionArterial() {
		return tensionArterial;
	}

	public void setTensionArterial(String tensionArterial) {
		this.tensionArterial = tensionArterial;
	}

	public float getTemperatura() {
		return temperatura;
	}

	public void setTemperatura(float temperatura) {
		this.temperatura = temperatura;
	}

	public int getFrecuenciaCardiaca() {
		return frecuenciaCardiaca;
	}

	public void setFrecuenciaCardiaca(int frecuenciaCardiaca) {
		this.frecuenciaCardiaca = frecuenciaCardiaca;
	}

	public int getSaturacionOxigeno() {
		return saturacionOxigeno;
	}

	public void setSaturacionOxigeno(int saturacionOxigeno) {
		this.saturacionOxigeno = saturacionOxigeno;
	}

	public String getMedicacionHabitual() {
		return medicacionHabitual;
	}

	public void setMedicacionHabitual(String medicacionHabitual) {
		this.medicacionHabitual = medicacionHabitual;
	}

	public String getExamenClinico() {
		return examenClinico;
	}

	public void setExamenClinico(String examenClinico) {
		this.examenClinico = examenClinico;
	}

	public String getDiagnosticoPresuntivo() {
		return diagnosticoPresuntivo;
	}

	public void setDiagnosticoPresuntivo(String diagnosticoPresuntivo) {
		this.diagnosticoPresuntivo = diagnosticoPresuntivo;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public MedicoEntity getMedico() {
		return medico;
	}

	public void setMedico(MedicoEntity medico) {
		this.medico = medico;
	}

	public EnfermeroEntity getEnfermero() {
		return enfermero;
	}

	public void setEnfermero(EnfermeroEntity enfermero) {
		this.enfermero = enfermero;
	}

}
