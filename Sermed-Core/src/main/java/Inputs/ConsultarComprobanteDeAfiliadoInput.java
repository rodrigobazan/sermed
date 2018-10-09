package Inputs;

import Excepciones.FechaIncorrectaException;
import Modelo.Afiliado;
import Modelo.Comprobante;

import java.time.LocalDate;
import java.util.Collection;

public interface ConsultarComprobanteDeAfiliadoInput {

    Collection<Comprobante> consultarTodosLosComprobantes(Afiliado afiliado);

    Collection<Comprobante> consultarComprobantesAfiliadoPorFechas(Afiliado afiliado, LocalDate fechaDesde, LocalDate fechaHasta) throws FechaIncorrectaException;
}
