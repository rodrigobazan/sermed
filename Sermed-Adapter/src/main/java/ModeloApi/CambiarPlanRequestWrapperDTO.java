package ModeloApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CambiarPlanRequestWrapperDTO {

    @JsonProperty("afiliado")
    public final  AfiliadoDTO afiliado;

    @JsonProperty("plan")
    public final PlanDTO plan;

    @JsonCreator
    public CambiarPlanRequestWrapperDTO(@JsonProperty("afiliado") AfiliadoDTO afiliado,
                                        @JsonProperty("plan") PlanDTO plan) {
        this.afiliado = afiliado;
        this.plan = plan;
    }
}
