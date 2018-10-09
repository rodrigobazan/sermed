package Inputs;

import Excepciones.ComprobanteNoExisteException;
import Excepciones.FechaIncorrectaException;
import Modelo.Comprobante;

import java.time.LocalDate;
import java.util.Collection;

public interface ConsultarComprobantesInput {

    Collection<Comprobante> consultarComprobantes();

    Comprobante consultarComprobantePorNumero(String numeroComprobante) throws ComprobanteNoExisteException;

    Collection<Comprobante> consultarComprobantesPorFechas(LocalDate fechaDesde, LocalDate fechaHasta) throws FechaIncorrectaException;
}
