package ar.com.koodi.sermedboundaries.SermedData.RepositorioData;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.koodi.sermedboundaries.SermedData.ModeloData.MedicoEntity;

@Repository
public interface IMedicoRepositorioCRUD extends CrudRepository<MedicoEntity, Integer>{
	
	MedicoEntity save(MedicoEntity medico);
	
	MedicoEntity findByIdMedico(Integer id);
	
	MedicoEntity findByMatricula(int matricula);
	
	Collection<MedicoEntity> findAll();

	Collection<MedicoEntity> findByApellidoContainingIgnoreCase(String apellido);
		
}
