package ar.com.koodi.sermeddata.RepositorioData;

import ar.com.koodi.sermeddata.ModeloData.EnfermeroEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IEnfermeroRepositorioCRUD extends CrudRepository<EnfermeroEntity, Integer> {

    EnfermeroEntity save(EnfermeroEntity enfermero);

    EnfermeroEntity findByIdEnfermero(Integer idEnfermero);

    EnfermeroEntity findByMatricula(Integer matricula);

    Collection<EnfermeroEntity> findAll();

    Collection<EnfermeroEntity> findByApellidoContainingIgnoreCase(String apellido);

}
