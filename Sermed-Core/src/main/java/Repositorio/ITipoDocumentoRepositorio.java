package Repositorio;

import Modelo.TipoDocumento;

import java.util.Collection;

public interface ITipoDocumentoRepositorio {
    Collection<TipoDocumento> findAll();

    Collection<TipoDocumento> findByNombre(String nombre);

    TipoDocumento findByNombreUnico(String nombre);
}
