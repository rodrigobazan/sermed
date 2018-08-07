package Interactor;

import Excepciones.ComprobanteNoExisteException;
import Excepciones.FechaIncorrectaException;
import Modelo.Comprobante;
import Repositorio.IComprobanteRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultarComprobantesUseCase {
    private IComprobanteRepositorio repositorioComprobante;

    public ConsultarComprobantesUseCase(IComprobanteRepositorio repositorioComprobante) {
        this.repositorioComprobante = repositorioComprobante;
    }

    public List<Comprobante> consultarComprobantes() {
        return (List<Comprobante>) this.repositorioComprobante.findAll();
    }

    public Comprobante consultarComprobantePorNumero(String numeroComprobante) throws ComprobanteNoExisteException {
        Comprobante comprobanteBuscado = this.repositorioComprobante.findByNumero(numeroComprobante);
        if(comprobanteBuscado != null) return comprobanteBuscado;
        throw new ComprobanteNoExisteException();
    }

    public List<Comprobante> consultarComprobantesPorFechas(LocalDate fechaDesde, LocalDate fechaHasta) throws FechaIncorrectaException {
        if(fechaHasta.isBefore(fechaDesde)) throw new FechaIncorrectaException();

        return (List<Comprobante>) this.repositorioComprobante.findByFechas(fechaDesde, fechaHasta);
    }
}
