package Inputs;

import Excepciones.TipoDocumentoNoExisteException;
import Modelo.TipoDocumento;

import java.util.Collection;

public interface ConsultarTipoDocumentoInput {

    Collection<TipoDocumento> consultarTodosLosTiposDeDocumento();

    Collection<TipoDocumento> consultarTiposDocumentosPorNombre(String nombre);

    TipoDocumento consultarTiposDocumentoUnicoPorNombre(String nombre) throws TipoDocumentoNoExisteException;


}
