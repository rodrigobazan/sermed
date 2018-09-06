package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.*;

@Entity(name = "Enfermero")
@SequenceGenerator(name="enfermero_idenfermero_seq", initialValue = 1, sequenceName = "enfermero_idenfermero_seq", allocationSize = 1)
public class EnfermeroEntity {

    @Id
    @Column(name = "idEnfermero", nullable = false)
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

}
