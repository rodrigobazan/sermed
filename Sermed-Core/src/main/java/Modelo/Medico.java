package Modelo;

public class Medico {
    private int idMedico;
    private String apellido;
    private String nombre;
    private int matricula;
    private String telefono;

    public Medico(int idMedico, String apellido, String nombre, int matricula, String telefono) {
        this.idMedico = idMedico;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
        this.telefono = telefono;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public Integer getMatricula() {
        return matricula;
    }
}
