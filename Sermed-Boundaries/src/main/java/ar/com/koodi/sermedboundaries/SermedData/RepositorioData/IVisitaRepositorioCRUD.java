package ar.com.koodi.sermedboundaries.SermedData.RepositorioData;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.koodi.sermedboundaries.SermedData.ModeloData.VisitaEntity;

@Repository
public interface IVisitaRepositorioCRUD extends CrudRepository<VisitaEntity, Integer>{
	
	VisitaEntity save(VisitaEntity visita);
	
	VisitaEntity findByNumerovisita(int numero);
	
	Collection<VisitaEntity> findByPacienteIdPersona(Integer idPersona); 
}
