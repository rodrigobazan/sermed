package ar.com.koodi.sermeddata.ModeloData;

import java.time.LocalDate;
import java.util.Collection;

import Modelo.Persona;

public class AfiliadoEntity {

	private Integer idAfiliado;
    private LocalDate fechaAfiliacion;
    private String numeroAfiliado;
    private Collection<Persona> miembros;
    private Persona titular;
    private boolean activo;
    private LocalDate fechaDeBaja;
    private Integer diaDelMesPagoAcordado;
    private PlanEntity plan;
	
    
    public AfiliadoEntity() {
	}


	public AfiliadoEntity(LocalDate fechaAfiliacion, String numeroAfiliado, Collection<Persona> miembros,
			Persona titular, boolean activo, LocalDate fechaDeBaja, Integer diaDelMesPagoAcordado, PlanEntity plan) {
		this.fechaAfiliacion = fechaAfiliacion;
		this.numeroAfiliado = numeroAfiliado;
		this.miembros = miembros;
		this.titular = titular;
		this.activo = activo;
		this.fechaDeBaja = fechaDeBaja;
		this.diaDelMesPagoAcordado = diaDelMesPagoAcordado;
		this.plan = plan;
	}


	public Integer getIdAfiliado() {
		return idAfiliado;
	}


	public void setIdAfiliado(Integer idAfiliado) {
		this.idAfiliado = idAfiliado;
	}


	public LocalDate getFechaAfiliacion() {
		return fechaAfiliacion;
	}


	public void setFechaAfiliacion(LocalDate fechaAfiliacion) {
		this.fechaAfiliacion = fechaAfiliacion;
	}


	public String getNumeroAfiliado() {
		return numeroAfiliado;
	}


	public void setNumeroAfiliado(String numeroAfiliado) {
		this.numeroAfiliado = numeroAfiliado;
	}


	public Collection<Persona> getMiembros() {
		return miembros;
	}


	public void setMiembros(Collection<Persona> miembros) {
		this.miembros = miembros;
	}


	public Persona getTitular() {
		return titular;
	}


	public void setTitular(Persona titular) {
		this.titular = titular;
	}


	public boolean isActivo() {
		return activo;
	}


	public void setActivo(boolean activo) {
		this.activo = activo;
	}


	public LocalDate getFechaDeBaja() {
		return fechaDeBaja;
	}


	public void setFechaDeBaja(LocalDate fechaDeBaja) {
		this.fechaDeBaja = fechaDeBaja;
	}


	public Integer getDiaDelMesPagoAcordado() {
		return diaDelMesPagoAcordado;
	}


	public void setDiaDelMesPagoAcordado(Integer diaDelMesPagoAcordado) {
		this.diaDelMesPagoAcordado = diaDelMesPagoAcordado;
	}


	public PlanEntity getPlan() {
		return plan;
	}


	public void setPlan(PlanEntity plan) {
		this.plan = plan;
	}
	
	
	
	
    
    
    
}
