package ModeloApi;

import Modelo.Afiliado;
import Modelo.PeriodoPago;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class ComprobanteDTO {
    @JsonProperty("idComprobante")
    public final Integer idComprobante;
    @JsonProperty("numeroComprobante")
    public final String numeroComprobante;
    @JsonProperty("afiliafo")
    public final Afiliado afiliado;
    @JsonProperty("total")
    public final double total;
    @JsonProperty("fechaCreacion")
    public final LocalDate fechaCreacion;
    @JsonProperty("modoDePago")
    public final String modoDePago;
    @JsonProperty("activo")
    public final boolean activo;
    @JsonProperty("periodosAbonados")
    public final List<PeriodoPago> periodosAbonados;

    public ComprobanteDTO(   @JsonProperty("idComprobante") Integer idComprobante ,
                             @JsonProperty("numeroComprobante")String numeroComprobante,
                             @JsonProperty("afiliafo") Afiliado afiliado,
                             @JsonProperty("total") double total ,
                             @JsonProperty("fechaCreacion") LocalDate fechaCreacion,
                             @JsonProperty("modoDePago")String modoDePago,
                             @JsonProperty("activo")boolean activo ,
                             @JsonProperty("periodosAbonados") List<PeriodoPago> periodosAbonados) {
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
