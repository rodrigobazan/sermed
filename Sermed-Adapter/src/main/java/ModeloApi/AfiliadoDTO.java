package ModeloApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.Collection;

public class AfiliadoDTO {

    @JsonProperty("idAfiliado")
    public final Integer idAfiliado;

    @JsonProperty("fechaAfiliacion")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public final LocalDate fechaAfiliacion;

    @JsonProperty("numeroAfiliado")
    public final String numeroAfiliado;

    @JsonProperty("miembros")
    public final Collection<PersonaDTO> miembros;

    @JsonProperty("titular")
    public final PersonaDTO titular;

    @JsonProperty("activo")
    public final boolean activo;

    @JsonProperty("fechaDeBaja")
    public final LocalDate fechaDeBaja;

    @JsonProperty("diaDelMesPagoAcordado")
    public final Integer diaDelMesPagoAcordado;

    @JsonProperty("plan")
    public final PlanDTO plan;

    @JsonCreator
    public AfiliadoDTO(@JsonProperty("idAfiliado") Integer idAfiliado,
                       @JsonProperty("fechaAfiliacion") LocalDate fechaAfiliacion,
                       @JsonProperty("numeroAfiliado") String numeroAfiliado,
                       @JsonProperty("miembros") Collection<PersonaDTO> miembros,
                       @JsonProperty("titular") PersonaDTO titular,
                       @JsonProperty("activo") boolean activo,
                       @JsonProperty("fechaDeBaja") LocalDate fechaDeBaja,
                       @JsonProperty("diaDelMesPagoAcordado") Integer diaDelMesPagoAcordado,
                       @JsonProperty("plan") PlanDTO plan) {
        this.idAfiliado = idAfiliado;
        this.fechaAfiliacion = fechaAfiliacion;
        this.numeroAfiliado = numeroAfiliado;
        this.miembros = miembros;
        this.titular = titular;
        this.activo = activo;
        this.fechaDeBaja = fechaDeBaja;
        this.diaDelMesPagoAcordado = diaDelMesPagoAcordado;
        this.plan = plan;
    }
}
