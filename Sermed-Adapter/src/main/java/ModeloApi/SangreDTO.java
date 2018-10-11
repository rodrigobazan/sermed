package ModeloApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SangreDTO {

    @JsonProperty("idSangre")
    public static Integer idSangre;

    @JsonProperty("grupo")
    public static String grupo;

    @JsonProperty("factor")
    public static String factor;

    public SangreDTO(Integer idSangre, String grupo, String factor) {

        this.idSangre = idSangre;
        this.grupo = grupo;
        this.factor = factor;
    }
}
