package Modelo;

public class Enfermero {
    private int idEnfermero;
    private String apellido;
    private String nombre;
    private int matricula;
    private String telefono;

    public Enfermero(int idEnfermero, String apellido, String nombre, int matricula, String telefono) {
        this.idEnfermero = idEnfermero;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
        this.telefono = telefono;
    }

    public int getIdEnfermero() {
        return idEnfermero;
    }

    public int getMatricula() {
        return matricula;
    }

    public String mostrarEnfermero() {
        return this.apellido + ", " + this.nombre + ". Matricula " + this.matricula + ". Tel. " + this.telefono;
    }

    public void modificarDatos(Enfermero nuevo) throws Exception {
        if (nuevo.apellido == null || nuevo.nombre == null || nuevo.telefono == null) {
            throw new Exception();
        }
        this.apellido = nuevo.apellido;
        this.nombre = nuevo.nombre;
        this.telefono = nuevo.telefono;
        this.matricula = nuevo.matricula;


    }
}
