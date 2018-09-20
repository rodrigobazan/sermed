package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.*;

@Entity(name = "enfermero")
@SequenceGenerator(name="enfermero_idenfermero_seq", initialValue = 1, sequenceName = "enfermero_idenfermero_seq", allocationSize = 1)
public class EnfermeroEntity {

    @Id
    @Column(name = "idenfermero", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enfermero_idenfermero_seq")
    private Integer idEnfermero;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "matricula")
    private Integer matricula;

    @Column(name = "telefono")
    private String telefono;

    public EnfermeroEntity(String apellido, String nombre, Integer matricula, String telefono) {
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
        this.telefono = telefono;
    }

    public EnfermeroEntity() {
    }

    public Integer getIdEnfermero() {
        return idEnfermero;
    }

    public void setIdEnfermero(Integer idEnfermero) {
        this.idEnfermero = idEnfermero;
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

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
