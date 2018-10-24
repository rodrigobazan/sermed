package ModeloApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PeriodoPagoDTO {

    @JsonProperty("idPeriodo")
    public final Integer idPeriodo;

    @JsonProperty("mes")
    public final int mes;

    @JsonProperty("anio")
    public final int anio;

    public PeriodoPagoDTO(@JsonProperty("idPeriodo") Integer idPeriodo,  @JsonProperty("mes") int mes, @JsonProperty("anio") int anio) {
    	this.idPeriodo = idPeriodo;
        this.mes = mes;
        this.anio = anio;
    }
}
