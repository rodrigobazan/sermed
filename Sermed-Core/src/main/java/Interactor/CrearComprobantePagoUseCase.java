package Interactor;

import Excepciones.ComprobanteExisteException;
import Inputs.CrearComprobanteDePagoInput;
import Modelo.Comprobante;
import Repositorio.IComprobanteRepositorio;

public class CrearComprobantePagoUseCase implements CrearComprobanteDePagoInput {
    private IComprobanteRepositorio repositorioComprobante;

    public CrearComprobantePagoUseCase(IComprobanteRepositorio repositorioComprobante) {
        this.repositorioComprobante = repositorioComprobante;
    }

    public boolean crearComprobante(Comprobante comprobante) throws ComprobanteExisteException {
        if(!validarComprobanteExiste(comprobante)){
            return repositorioComprobante.persist(comprobante);
        }
        throw new ComprobanteExisteException();
    }

    private boolean validarComprobanteExiste(Comprobante comprobante) {
        return repositorioComprobante.findByNumero(comprobante.getNumero()) != null;
    }
}
