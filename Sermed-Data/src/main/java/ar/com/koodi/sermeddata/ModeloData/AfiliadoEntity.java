package ar.com.koodi.sermeddata.ModeloData;

import java.time.LocalDate;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import Modelo.Persona;

@Entity(name="afiliado")
@SequenceGenerator(name ="afiliado_idafiliado_seq", sequenceName = "afiliado_idafiliado_seq", allocationSize = 1)
public class AfiliadoEntity {

	@Id
    @Column(name = "idafiliado", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "afiliado_idafiliado_seq")
	private Integer idAfiliado;
	
	@Column(name="fechaafiliacion")
    private LocalDate fechaAfiliacion;
	
	@Column(name="numeroafiliado")
    private String numeroAfiliado;

	@OneToMany(mappedBy = "afiliado")	
    private Collection<PersonaEntity> miembros;
	
	@ManyToOne
	@JoinColumn(name = "idpersona", referencedColumnName="idPersona")
    private PersonaEntity titular;
	
	@Column(name="activo")
    private boolean activo;
	
	@Column(name="fechadebaja")
    private LocalDate fechaDeBaja;
	
	@Column(name="diadelmespagoacordado")
    private Integer diaDelMesPagoAcordado;
	
	@JoinColumn(name = "idplan", referencedColumnName = "idplan")
    @ManyToOne	
    private PlanEntity plan;
	
    
    public AfiliadoEntity() {
	}


	public AfiliadoEntity(LocalDate fechaAfiliacion, String numeroAfiliado, Collection<PersonaEntity> miembros,
			PersonaEntity titular, boolean activo, LocalDate fechaDeBaja, Integer diaDelMesPagoAcordado, PlanEntity plan) {
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


	public Collection<PersonaEntity> getMiembros() {
		return miembros;
	}


	public void setMiembros(Collection<PersonaEntity> miembros) {
		this.miembros = miembros;
	}


	public PersonaEntity getTitular() {
		return titular;
	}


	public void setTitular(PersonaEntity titular) {
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
