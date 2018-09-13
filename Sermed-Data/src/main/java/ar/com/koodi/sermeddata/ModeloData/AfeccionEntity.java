package ar.com.koodi.sermeddata.ModeloData;


import java.util.Collection;

import javax.persistence.*;

@Entity(name = "afeccion")
@SequenceGenerator(name="afeccion_idafeccion_seq", initialValue = 1, sequenceName = "afeccion_idafeccion_seq", allocationSize = 1)
public class AfeccionEntity {

    @Id
    @Column(name = "idAfeccion", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "afeccion_idafeccion_seq")
    private Integer idAfeccion;

    @Column(name = "nombreafeccion")
    private String nombreAfeccion;    
    
    @OneToMany(mappedBy="afeccion")
    private Collection<AntecedenteMedicoEntity> antecedentes;

    public AfeccionEntity() {
    }

    public AfeccionEntity(String nombreAfeccion) {
        this.nombreAfeccion = nombreAfeccion;
    }

    public Integer getIdAfeccion() {
        return idAfeccion;
    }

    public void setIdAfeccion(Integer idAfeccion) {
        this.idAfeccion = idAfeccion;
    }

    public String getNombreAfeccion() {
        return nombreAfeccion;
    }

    public void setNombreAfeccion(String nombreAfeccion) {
        this.nombreAfeccion = nombreAfeccion;
    }

	public Collection<AntecedenteMedicoEntity> getAntecedentes() {
		return antecedentes;
	}

	public void setAntecedentes(Collection<AntecedenteMedicoEntity> antecedentes) {
		this.antecedentes = antecedentes;
	}
    
    
}
