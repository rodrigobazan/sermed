package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.*;


@Entity(name = "antecedentemedico")
@SequenceGenerator(name="antecedentemedico_idantecedentemedico_seq", initialValue = 1, sequenceName = "antecedentemedico_idantecedentemedico_seq", allocationSize = 1)
public class AntecedenteMedicoEntity {

    @Id
    @Column(name = "idantedecentemedico", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "antecedentemedico_idantecedentemedico_seq")
    private Integer idAntecedenteMedico;
    
    //@ManyToOne
    @ManyToOne
    @JoinColumn(name = "idafeccion" ,referencedColumnName = "idafeccion")
    private AfeccionEntity afeccion;

    @Column(name = "observacion")
    private String observacion;
    
    @ManyToOne
    @JoinColumn(name = "idpersona" ,referencedColumnName = "idpersona")
    private PersonaEntity persona;

    public AntecedenteMedicoEntity() {
    }

    public AntecedenteMedicoEntity(AfeccionEntity afeccion, String observacion) {
        this.afeccion = afeccion;
        this.observacion = observacion;
    }

    public Integer getIdAntecedenteMedico() {
        return idAntecedenteMedico;
    }

    public void setIdAntecedenteMedico(Integer idAntecedenteMedico) {
        this.idAntecedenteMedico = idAntecedenteMedico;
    }

    public AfeccionEntity getAfeccion() {
        return afeccion;
    }

    public void setAfeccion(AfeccionEntity afeccion) {
        this.afeccion = afeccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

	public PersonaEntity getPersona() {
		return persona;
	}

	public void setPersona(PersonaEntity persona) {
		this.persona = persona;
	}
    
    
}
