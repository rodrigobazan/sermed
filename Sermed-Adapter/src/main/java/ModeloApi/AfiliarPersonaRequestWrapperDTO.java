package ModeloApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AfiliarPersonaRequestWrapperDTO {

    @JsonProperty("afiliado")
    public final AfiliadoDTO afiliado;

    @JsonProperty("persona")
    public final PersonaDTO persona;

    @JsonCreator
    public AfiliarPersonaRequestWrapperDTO(@JsonProperty("afiliado") AfiliadoDTO afiliado,
                                           @JsonProperty("persona") PersonaDTO persona) {
        this.afiliado = afiliado;
        this.persona = persona;
    }
}
