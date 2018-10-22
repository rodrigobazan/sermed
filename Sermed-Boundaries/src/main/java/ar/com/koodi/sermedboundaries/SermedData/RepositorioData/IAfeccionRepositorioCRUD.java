package ar.com.koodi.sermedboundaries.SermedData.RepositorioData;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.koodi.sermedboundaries.SermedData.ModeloData.AfeccionEntity;

@Repository
public interface IAfeccionRepositorioCRUD extends CrudRepository<AfeccionEntity, Integer>{
	
	Collection<AfeccionEntity> findAll();
	
	AfeccionEntity findByNombreAfeccionEquals(String afeccion);
	
	Collection<AfeccionEntity> findByNombreAfeccionContainingIgnoreCase(String afeccion);
}
