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
}
