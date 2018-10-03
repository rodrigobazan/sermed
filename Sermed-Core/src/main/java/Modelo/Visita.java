package Modelo;

import Excepciones.VisitaIncompletaException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Visita {

    private  Integer idVisita;
    private  int numeroVisita;
    private  Persona elPaciente;
    private  LocalDateTime fechaHoraVisita;
    private  String motivoConsulta;
    private  String antecedentesPatologicos;
    private  String tensionArterial;
    private  float temperatura;
    private  int frecuenciaCardiaca;
    private  int saturacionOxigeno;
    private  String medicacionHabitual;
    private  String examenClinico;
    private  String diagnosticoPresuntivo;
    private  String tratamiento;
    private  String observaciones;
    private  Medico medico;
    private Enfermero enfermero;

    public Visita(Integer idVisita, int numeroVisita, Persona elPaciente, LocalDateTime fechaHoraVisita, String motivoConsulta, String antecedentesPatologicos, String tensionArterial, float temperatura, int frecuenciaCardiaca, int saturacionOxigeno, String medicacionHabitual, String examenClinico, String diagnosticoPresuntivo, String tratamiento, String observaciones, Medico medico, Enfermero enfermero) {
        this.idVisita = idVisita;
        this.numeroVisita = numeroVisita;
        this.elPaciente = elPaciente;
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



	public static Visita instancia(int idVisita, int numeroVisita, Persona elPaciente, LocalDateTime fechaHoraVisita, String motivoConsulta, String antecedentesPatologicos, String tensionArterial, float temperatura, int frecuenciaCardiaca, int saturacionOxigeno, String medicacionHabitual, String examenClinico, String diagnosticoPresuntivo, String tratamiento, String observaciones, Medico medico, Enfermero enfermero) throws VisitaIncompletaException {
        if(fechaHoraVisita==null || elPaciente ==null || enfermero==null){
            throw new VisitaIncompletaException();
        }

        Visita laVisita = new Visita(idVisita,numeroVisita, elPaciente,fechaHoraVisita,motivoConsulta,antecedentesPatologicos,tensionArterial,temperatura,frecuenciaCardiaca,saturacionOxigeno,medicacionHabitual,examenClinico,diagnosticoPresuntivo,tratamiento,observaciones,medico,enfermero);
        return laVisita;

    }
	
	
	
    public Persona getElPaciente() {
		return elPaciente;
	}



	public void setElPaciente(Persona elPaciente) {
		this.elPaciente = elPaciente;
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



	public Medico getMedico() {
		return medico;
	}



	public void setMedico(Medico medico) {
		this.medico = medico;
	}



	public Enfermero getEnfermero() {
		return enfermero;
	}



	public void setEnfermero(Enfermero enfermero) {
		this.enfermero = enfermero;
	}



	public int getNumeroVisita() {
		return numeroVisita;
	}

	public void setNumeroVisita(int numeroVisita) {
		this.numeroVisita = numeroVisita;
	}



	public Integer getIdVisita() {
		return idVisita;
	}

	public void setIdVisita(Integer idVisita) {
		this.idVisita = idVisita;
	}


	public Persona getPaciente() {
        return this.elPaciente;
    }

	public LocalDate getFecha() {
		return this.fechaHoraVisita.toLocalDate();
	}

}
