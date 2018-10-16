package ModeloReporte;

import Modelo.Comprobante;

import java.time.LocalDate;

public class ComprobanteAfiliadoDTO {

    private LocalDate fechaCreacion;
    private String numeroComprobante;
    private String nombreApellidoAfiliado;
    private String domicilio;
    private Double total;
    private String formaDePago;


    public void generarComprobanteDTO(Comprobante comprobante) {
        this.fechaCreacion = comprobante.getFechaCreacion();
        this.numeroComprobante = comprobante.getNumero();
        this.nombreApellidoAfiliado = comprobante.nombreApellidoTitularAfiliado();
        this.domicilio = comprobante.domicilioTitularAfiliado();
        this.total = comprobante.getTotal();
        this.formaDePago = comprobante.getModoPago();
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public String getNombreApellidoAfiliado() {
        return nombreApellidoAfiliado;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public Double getTotal() {
        return total;
    }

    public String getFormaDePago() {
        return formaDePago;
    }
}
