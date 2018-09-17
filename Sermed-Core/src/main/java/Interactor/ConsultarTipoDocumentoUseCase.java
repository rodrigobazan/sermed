package Interactor;

import Excepciones.TipoDocumentoNoExisteException;
import Modelo.TipoDocumento;
import Repositorio.ITipoDocumentoRepositorio;

import java.util.List;

public class ConsultarTipoDocumentoUseCase {
    private ITipoDocumentoRepositorio repositorioTipoDocumento;

    public ConsultarTipoDocumentoUseCase(ITipoDocumentoRepositorio repositorioTipoDocumento) {
        this.repositorioTipoDocumento = repositorioTipoDocumento;
    }

    public List<TipoDocumento> consultarTodosLosTiposDeDocumento() {
        return (List<TipoDocumento>) repositorioTipoDocumento.findAll();
    }

    public List<TipoDocumento> consultarTiposDocumentosPorNombre(String nombre) {
        return (List<TipoDocumento>) repositorioTipoDocumento.findByNombre(nombre);
    }

    public TipoDocumento consultarTiposDocumentoUnicoPorNombre(String dni) throws TipoDocumentoNoExisteException {
        TipoDocumento buscado = repositorioTipoDocumento.findByNombreUnico(dni);
        if(buscado != null) return buscado;
        throw  new TipoDocumentoNoExisteException();
    }
}
