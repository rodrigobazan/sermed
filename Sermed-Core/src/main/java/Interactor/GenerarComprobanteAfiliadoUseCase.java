package Interactor;

import Excepciones.ComprobanteNoExisteException;
import Modelo.Comprobante;
import ModeloReporte.ComprobanteAfiliadoDTO;
import Repositorio.IComprobanteRepositorio;

public class GenerarComprobanteAfiliadoUseCase {
    private IComprobanteRepositorio repositorioComprobante;

    public GenerarComprobanteAfiliadoUseCase(IComprobanteRepositorio repositorioComprobante) {
        this.repositorioComprobante = repositorioComprobante;
    }

    public ComprobanteAfiliadoDTO generarComprobanteAfiliadoReporte(String numeroComprobante) throws ComprobanteNoExisteException {
        Comprobante comprobante = this.repositorioComprobante.findByNumero(numeroComprobante);
        if(comprobante!= null){
            ComprobanteAfiliadoDTO comprobanteAfiliadoDTO = new ComprobanteAfiliadoDTO();
            comprobanteAfiliadoDTO.generarComprobanteDTO(comprobante);
            return comprobanteAfiliadoDTO;
        }
        throw new ComprobanteNoExisteException();
    }
}
