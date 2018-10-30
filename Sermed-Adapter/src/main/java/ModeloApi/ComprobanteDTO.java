package ModeloApi;

import Modelo.PeriodoPago;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class ComprobanteDTO {

    @JsonProperty("idComprobante")
    public final Integer idComprobante;

    @JsonProperty("numeroComprobante")
    public final String numeroComprobante;

    @JsonProperty("afiliado")
    public final AfiliadoDTO afiliado;

    @JsonProperty("total")
    public final double total;

    @JsonProperty("fechaCreacion")
    @JsonFormat(pattern = "yyyy-MM-dd")
    public final LocalDate fechaCreacion;

    @JsonProperty("modoPago")
    public final String modoDePago;

    @JsonProperty("activo")
    public final boolean activo;

    @JsonProperty("periodosAbonados")
    public final List<PeriodoPagoDTO> periodosAbonados;

    @JsonCreator
    public ComprobanteDTO(@JsonProperty("idComprobante") Integer idComprobante,
                          @JsonProperty("numeroComprobante") String numeroComprobante,
                          @JsonProperty("afiliado") AfiliadoDTO afiliado,
                          @JsonProperty("total") double total,
                          @JsonProperty("fechaCreacion") LocalDate fechaCreacion,
                          @JsonProperty("modoPago") String modoDePago,
                          @JsonProperty("activo") boolean activo,
                          @JsonProperty("periodosAbonados") List<PeriodoPagoDTO> periodosAbonados) {
        this.idComprobante = idComprobante;
        this.numeroComprobante = numeroComprobante;
        this.afiliado = afiliado;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.modoDePago = modoDePago;
        this.activo = activo;
        this.periodosAbonados = periodosAbonados;
    }
}
