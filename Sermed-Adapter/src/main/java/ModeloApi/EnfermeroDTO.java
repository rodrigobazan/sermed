package ModeloApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EnfermeroDTO {

    @JsonProperty("idEnfermero")
    public final Integer idEnfermero;

    @JsonProperty("apellido")
    public final String apellido;

    @JsonProperty("nombre")
    public final String nombre;

    @JsonProperty("matricula")
    public final Integer matricula;

    @JsonProperty("telefono")
    public final String telefono;

    @JsonCreator
    public EnfermeroDTO(@JsonProperty("idEnfermero") Integer idEnfermero,
                        @JsonProperty("apellido") String apellido,
                        @JsonProperty("nombre") String nombre,
                        @JsonProperty("matricula") Integer matricula,
                        @JsonProperty("telefono") String telefono) {
        this.idEnfermero = idEnfermero;
        this.apellido = apellido;
        this.nombre = nombre;
        this.matricula = matricula;
        this.telefono = telefono;
    }
}
