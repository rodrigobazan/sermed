package ar.com.koodi.sermeddata.RepositorioData;

import ar.com.koodi.sermeddata.ModeloData.SangreEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface ISangreRepositorioCRUD extends CrudRepository<SangreEntity, Integer> {

    Collection<SangreEntity> findByGrupo(String grupo);

    Collection<SangreEntity> findByFactor(String factor);

    SangreEntity findByGrupoAndFactor(String grupo,String factor);

    Collection<SangreEntity> findAll();

}
