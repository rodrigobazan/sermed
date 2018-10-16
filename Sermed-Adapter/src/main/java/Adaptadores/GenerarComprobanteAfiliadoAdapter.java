package Adaptadores;

import Excepciones.ComprobanteNoExisteException;
import Inputs.GenerarComprobanteAfiliadoInput;
import ModeloApi.ComprobanteAfiliadoReporteDTO;
import ModeloReporte.ComprobanteAfiliadoDTO;

public class GenerarComprobanteAfiliadoAdapter {
    private GenerarComprobanteAfiliadoInput generarComprobanteAfiliadoInput;

    public GenerarComprobanteAfiliadoAdapter(GenerarComprobanteAfiliadoInput generarComprobanteAfiliadoInput) {
        this.generarComprobanteAfiliadoInput = generarComprobanteAfiliadoInput;
    }

    public ComprobanteAfiliadoReporteDTO generarComprobanteAfiliadoReporte(String numeroComprobante) throws ComprobanteNoExisteException {
        ComprobanteAfiliadoDTO comprobante = generarComprobanteAfiliadoInput.generarComprobanteAfiliadoReporte(numeroComprobante);
        return new ComprobanteAfiliadoReporteDTO(comprobante.getFechaCreacion(), comprobante.getNumeroComprobante(),
                comprobante.getNombreApellidoAfiliado(), comprobante.getDomicilio(), comprobante.getTotal(), comprobante.getFormaDePago());
    }
}
