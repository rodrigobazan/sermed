package ar.com.koodi.sermeddata.RepositorioData;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.koodi.sermeddata.ModeloData.ObraSocialEntity;

@Repository
public interface IObraSocialRepositorioCRUD extends CrudRepository<ObraSocialEntity, Integer>{
	ObraSocialEntity save(ObraSocialEntity obraSocial);
	
	ObraSocialEntity findByObraSocial(String obraSocial);
	
	ObraSocialEntity findByIdObraSocial(Integer idObrasocial);
	
	Collection<ObraSocialEntity> findByObraSocialContainingIgnoreCase(String nombre);
	
	Collection<ObraSocialEntity> findAll();	
	
}
