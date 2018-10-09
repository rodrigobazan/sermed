package Inputs;

import Excepciones.ComprobanteNoExisteException;
import ModeloReporte.ComprobanteAfiliadoDTO;

public interface GenerarComprobanteAfiliadoInput {

	ComprobanteAfiliadoDTO generarComprobanteAfiliadoReporte(String numeroComprobante) throws ComprobanteNoExisteException;
}
