package Interactor;

import Excepciones.ComprobanteAnuladoException;
import Modelo.Comprobante;
import Repositorio.IComprobanteRepositorio;

public class AnularComprobantesUseCase {
    private IComprobanteRepositorio repositorioComprobante;

    public AnularComprobantesUseCase(IComprobanteRepositorio repositorioComprobante) {
        this.repositorioComprobante = repositorioComprobante;
    }

    public boolean anularComprobante(Comprobante comprobanteAAnular) throws ComprobanteAnuladoException {
         if(comprobanteAAnular.getActivo()){
             comprobanteAAnular.anularComprobante();
             return repositorioComprobante.update(comprobanteAAnular);
         }
         throw new ComprobanteAnuladoException();
    }
}
