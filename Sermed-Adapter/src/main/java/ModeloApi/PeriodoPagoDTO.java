package ModeloApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeriodoPagoDTO {

    @JsonProperty("idPeriodo")
    public static Integer idPeriodo;

    @JsonProperty("mes")
    public static int mes;

    @JsonProperty("anio")
    public static int anio;

    public PeriodoPagoDTO(@JsonProperty("idPeriodo") Integer idPeriodo,  @JsonProperty("mes") int mes, @JsonProperty("anio") int anio) {
        this.mes = mes;
        this.anio = anio;
    }
}
