package Interactor;

import Excepciones.ComprobanteAnuladoException;
import Inputs.AnularComprobanteInput;
import Modelo.Comprobante;
import Repositorio.IComprobanteRepositorio;

public class AnularComprobantesUseCase implements AnularComprobanteInput {
    private IComprobanteRepositorio repositorioComprobante;

    public AnularComprobantesUseCase(IComprobanteRepositorio repositorioComprobante) {
        this.repositorioComprobante = repositorioComprobante;
    }

    @Override
    public boolean anularComprobante(Comprobante comprobanteAAnular) throws ComprobanteAnuladoException {
         if(comprobanteAAnular.getActivo()){
             comprobanteAAnular.anularComprobante();
             return repositorioComprobante.update(comprobanteAAnular);
         }
         throw new ComprobanteAnuladoException();
    }
}
