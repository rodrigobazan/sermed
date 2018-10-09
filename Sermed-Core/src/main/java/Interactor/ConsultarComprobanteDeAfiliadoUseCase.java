package Interactor;

import Excepciones.FechaIncorrectaException;
import Inputs.ConsultarComprobanteDeAfiliadoInput;
import Modelo.Afiliado;
import Modelo.Comprobante;
import Repositorio.IComprobanteRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsultarComprobanteDeAfiliadoUseCase implements ConsultarComprobanteDeAfiliadoInput {
    private IComprobanteRepositorio repositorioComprobante;

    public ConsultarComprobanteDeAfiliadoUseCase(IComprobanteRepositorio repositorioComprobante) {
        this.repositorioComprobante = repositorioComprobante;
    }

    @Override
    public List<Comprobante> consultarTodosLosComprobantes(Afiliado afiliado) {
        List<Comprobante>comprobantes = (List<Comprobante>) this.repositorioComprobante.findByAfiliado(afiliado);
        if(!comprobantes.isEmpty()) return comprobantes;
        else return new ArrayList<>();
    }

    @Override
    public List<Comprobante> consultarComprobantesAfiliadoPorFechas(Afiliado afiliado, LocalDate fechaDesde, LocalDate fechaHasta) throws FechaIncorrectaException {
        if(fechaHasta.isBefore(fechaDesde)) throw new FechaIncorrectaException();
        List<Comprobante> comprobantes = (List<Comprobante>) this.repositorioComprobante.findByAfiliado(afiliado);
        if(!comprobantes.isEmpty()){
            return comprobantes.stream().filter(c-> (c.getFechaCreacion().isAfter(fechaDesde) || c.getFechaCreacion().isEqual(fechaDesde)) &&
                    (c.getFechaCreacion().isEqual(fechaHasta) || c.getFechaCreacion().isBefore(fechaHasta))).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
}
