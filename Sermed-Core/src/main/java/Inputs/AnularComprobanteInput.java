package Inputs;

import Excepciones.ComprobanteAnuladoException;
import Modelo.Comprobante;

public interface AnularComprobanteInput {

    boolean anularComprobante(Comprobante comprobante) throws ComprobanteAnuladoException;
}
