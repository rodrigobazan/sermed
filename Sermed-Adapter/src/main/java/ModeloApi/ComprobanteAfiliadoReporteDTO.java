package ModeloApi;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class ComprobanteAfiliadoReporteDTO {

    @JsonProperty("fechaCreacion")
    @JsonFormat(pattern = "dd/MM/yyyy")
    public final LocalDate fechaCreacion;

    @JsonProperty("numeroComprobante")
    public final String numeroComprobante;

    @JsonProperty("nombreApellidoAfiliado")
    public final String nombreApellidoAfiliado;

    @JsonProperty("domicilio")
    public final String domicilio;

    @JsonProperty("total")
    public final Double total;

    @JsonProperty("formaDePago")
    public final String formaDePago;

    public ComprobanteAfiliadoReporteDTO(@JsonProperty("fechaCreacion") LocalDate fechaCreacion,
                                         @JsonProperty("numeroComprobante") String numeroComprobante,
                                         @JsonProperty("nombreApellidoAfiliado") String nombreApellidoAfiliado,
                                         @JsonProperty("domicilio") String domicilio,
                                         @JsonProperty("total") Double total,
                                         @JsonProperty("formaDePago") String formaDePago) {
        this.fechaCreacion = fechaCreacion;
        this.numeroComprobante = numeroComprobante;
        this.nombreApellidoAfiliado = nombreApellidoAfiliado;
        this.domicilio = domicilio;
        this.total = total;
        this.formaDePago = formaDePago;
    }
}
