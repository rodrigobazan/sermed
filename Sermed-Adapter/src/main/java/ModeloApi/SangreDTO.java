package ModeloApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SangreDTO {

    @JsonProperty("idSangre")
    public final Integer idSangre;

    @JsonProperty("grupo")
    public final String grupo;

    @JsonProperty("factor")
    public final String factor;

    @JsonCreator
    public SangreDTO(@JsonProperty("idSangre") Integer idSangre,
                     @JsonProperty("grupo") String grupo,
                     @JsonProperty("factor") String factor) {

        this.idSangre = idSangre;
        this.grupo = grupo;
        this.factor = factor;
    }
}
