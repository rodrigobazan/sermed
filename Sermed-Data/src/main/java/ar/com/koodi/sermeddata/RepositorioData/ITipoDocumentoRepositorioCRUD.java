package ar.com.koodi.sermeddata.RepositorioData;

import ar.com.koodi.sermeddata.ModeloData.TipoDocumentoEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ITipoDocumentoRepositorioCRUD extends CrudRepository<TipoDocumentoEntity, Integer> {

    Collection<TipoDocumentoEntity> findAll();

    Collection<TipoDocumentoEntity> findByNombreContainingIgnoreCase(String nombre);

    TipoDocumentoEntity findByNombreEqualsIgnoreCase(String nombre);
}
