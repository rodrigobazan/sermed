package Interactor;

import Excepciones.TipoDocumentoNoExisteException;
import Inputs.ConsultarTipoDocumentoInput;
import Modelo.TipoDocumento;
import Repositorio.ITipoDocumentoRepositorio;

import java.util.List;

public class ConsultarTipoDocumentoUseCase implements ConsultarTipoDocumentoInput {
    private ITipoDocumentoRepositorio repositorioTipoDocumento;

    public ConsultarTipoDocumentoUseCase(ITipoDocumentoRepositorio repositorioTipoDocumento) {
        this.repositorioTipoDocumento = repositorioTipoDocumento;
    }

    @Override
    public List<TipoDocumento> consultarTodosLosTiposDeDocumento() {
        return (List<TipoDocumento>) repositorioTipoDocumento.findAll();
    }

    @Override
    public List<TipoDocumento> consultarTiposDocumentosPorNombre(String nombre) {
        return (List<TipoDocumento>) repositorioTipoDocumento.findByNombre(nombre);
    }

    @Override
    public TipoDocumento consultarTiposDocumentoUnicoPorNombre(String nombre) throws TipoDocumentoNoExisteException {
        TipoDocumento buscado = repositorioTipoDocumento.findByNombreUnico(nombre);
        if(buscado != null) return buscado;
        throw  new TipoDocumentoNoExisteException();
    }
}
