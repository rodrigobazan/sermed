package ar.com.koodi.sermeddata.RepositorioData;

import ar.com.koodi.sermeddata.ModeloData.EnfermeroEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnfermeroRepositorioCRUD extends CrudRepository<EnfermeroEntity, Integer> {

    EnfermeroEntity save(EnfermeroEntity enfermero);
}
