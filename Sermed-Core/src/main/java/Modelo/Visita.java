package Modelo;

import Excepciones.VisitaIncompletaException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Visita {

    private  int idVisita;
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

    private Visita(int idVisita, int numeroVisita, Persona elPaciente, LocalDateTime fechaHoraVisita, String motivoConsulta, String antecedentesPatologicos, String tensionArterial, float temperatura, int frecuenciaCardiaca, int saturacionOxigeno, String medicacionHabitual, String examenClinico, String diagnosticoPresuntivo, String tratamiento, String observaciones, Medico medico, Enfermero enfermero) {
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

    public Persona getPaciente() {
        return this.elPaciente;
    }

    public LocalDate getFecha() {
        return this.fechaHoraVisita.toLocalDate();
    }
}
