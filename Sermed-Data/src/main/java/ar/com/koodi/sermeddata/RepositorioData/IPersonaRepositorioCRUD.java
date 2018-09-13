package ar.com.koodi.sermeddata.RepositorioData;

import ar.com.koodi.sermeddata.ModeloData.PersonaEntity;
import ar.com.koodi.sermeddata.ModeloData.TipoDocumentoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IPersonaRepositorioCRUD extends CrudRepository<PersonaEntity, Integer> {

    PersonaEntity save(PersonaEntity persona);

    PersonaEntity findByIdPersona(Integer idPersona);

    PersonaEntity findByDocumentoandAndTipoDocumento(String documento, String tipoDocumento);

    Collection<PersonaEntity> findAll();

    Collection<PersonaEntity> findByApellidosContains(String apellido);

}
