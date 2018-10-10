package ModeloApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AfeccionDTO {

    @JsonProperty("idAfeccion")
    public final Integer idAfeccion;

    @JsonProperty("nombreAfeccion")
    public final String nombreAfeccion;

    @JsonCreator

    public AfeccionDTO(@JsonProperty("idAfeccion") Integer idAfeccion,
                       @JsonProperty("nombreAfeccion") String nombreAfeccion) {
        this.idAfeccion = idAfeccion;
        this.nombreAfeccion = nombreAfeccion;
    }
}
