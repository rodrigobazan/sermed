package Inputs;

import Excepciones.ComprobanteExisteException;
import Modelo.Comprobante;

public interface CrearComprobanteDePagoInput {

    boolean crearComprobante(Comprobante comprobante) throws ComprobanteExisteException;

}
