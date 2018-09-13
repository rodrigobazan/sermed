package Modelo;

import Excepciones.MedicoIncompletoException;

public class Medico {
    private Integer idMedico;
    private String apellido;
    private String nombre;
    private int matricula;
    private String telefono;

    public Medico(Integer idMedico, String apellido, String nombre, int matricula, String telefono) {
        this.idMedico = idMedico;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
        this.telefono = telefono;
    }

    public Integer getIdMedico() {
        return idMedico;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getApellido() {
		return apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String mostrarMedico() {
        return this.apellido+", "+this.nombre+". Matricula "+this.matricula+". Tel. "+this.telefono;
    }

    public void modificarDatos(Medico nuevo) throws MedicoIncompletoException {
        if(nuevo.apellido==null || nuevo.nombre==null || nuevo.telefono==null){
            throw new MedicoIncompletoException();
        }
            this.apellido = nuevo.apellido;
            this.nombre = nuevo.nombre;
            this.telefono = nuevo.telefono;
            this.matricula = nuevo.matricula;


    }
}
