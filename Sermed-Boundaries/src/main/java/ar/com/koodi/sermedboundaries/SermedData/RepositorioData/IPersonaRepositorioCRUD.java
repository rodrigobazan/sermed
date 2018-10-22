package ar.com.koodi.sermedboundaries.SermedData.RepositorioData;

import ar.com.koodi.sermedboundaries.SermedData.ModeloData.PersonaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IPersonaRepositorioCRUD extends CrudRepository<PersonaEntity, Integer> {

    PersonaEntity save(PersonaEntity persona);

    PersonaEntity findByIdPersona(Integer idPersona);

    PersonaEntity findByDocumentoAndTipoDocumentoNombre(String documento, String tipoDocumento);

    Collection<PersonaEntity> findAll();

    Collection<PersonaEntity> findByApellidosContainingIgnoreCase(String apellido);

    PersonaEntity findByNroAfiliadoEqualsAndNroOrden(String nroAfiliado, int nroOrden);
}
