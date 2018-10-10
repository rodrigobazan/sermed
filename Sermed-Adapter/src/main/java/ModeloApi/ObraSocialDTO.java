package ModeloApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ObraSocialDTO {

    @JsonProperty("idObraSocial")
    public final Integer idObraSocial;

    @JsonProperty("obraSocial")
    public final String obraSocial;

    @JsonCreator
    public ObraSocialDTO(@JsonProperty("idObraSocial") Integer idObraSocial,
                         @JsonProperty("obraSocial") String obraSocial) {
        this.idObraSocial = idObraSocial;
        this.obraSocial = obraSocial;
    }
}
