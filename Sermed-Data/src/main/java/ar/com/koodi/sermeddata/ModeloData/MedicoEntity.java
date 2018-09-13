package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "Medico")
@SequenceGenerator(name = "medico_idmedico_seq", initialValue = 1, sequenceName = "medico_idmedico_seq", allocationSize = 1)
public class MedicoEntity {

	@Id
	@Column(name = "idMedico", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medico_idmedico_seq")
	private Integer idMedico;
	
	@Column(name="apellido")
	private String apellido;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="matricula")
	private int matricula;
	
	@Column(name="telefono")
	private String telefono;

	public MedicoEntity(String apellido, String nombre, int matricula, String telefono) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.matricula = matricula;
		this.telefono = telefono;
	}

	public MedicoEntity() {
	}

	public Integer getIdMedico() {
		return idMedico;
	}

	public void setIdMedico(Integer idMedico) {
		this.idMedico = idMedico;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getMatricula() {
		return matricula;
	}

	public void setMatricula(int matricula) {
		this.matricula = matricula;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

}
