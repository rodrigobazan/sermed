package Interactor;

import Excepciones.ComprobanteExisteException;
import Modelo.Comprobante;
import Repositorio.IComprobanteRepositorio;

public class CrearComprobantePagoUseCase {
    private IComprobanteRepositorio repositorioComprobante;

    public CrearComprobantePagoUseCase(IComprobanteRepositorio repositorioComprobante) {
        this.repositorioComprobante = repositorioComprobante;
    }

    public boolean crearComprobante(Comprobante recibo) throws ComprobanteExisteException {
        if(!validarReciboExiste(recibo)){
            return repositorioComprobante.persist(recibo);
        }
        throw new ComprobanteExisteException();
    }

    private boolean validarReciboExiste(Comprobante recibo) {
        return repositorioComprobante.findByNumero(recibo.getNumero()) != null;
    }
}
