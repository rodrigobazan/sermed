package ModeloApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MedicoDTO {

    @JsonProperty("idMedico")
    public final Integer idMedico;

    @JsonProperty("apellido")
    public final String apellido;

    @JsonProperty("nombre")
    public final String nombre;

    @JsonProperty("matricula")
    public final int matricula;

    @JsonProperty("telefono")
    public final String telefono;

    @JsonCreator

    public MedicoDTO(@JsonProperty("idMedico") Integer idMedico,
                     @JsonProperty("apellido") String apellido,
                     @JsonProperty("nombre") String nombre,
                     @JsonProperty("matricula") int matricula,
                     @JsonProperty("telefono") String telefono) {
        this.idMedico = idMedico;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
        this.telefono = telefono;
    }
}
