package ar.com.koodi.sermeddata.RepositorioData;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.koodi.sermeddata.ModeloData.MedicoEntity;

@Repository
public interface IMedicoRepositorioCRUD extends CrudRepository<MedicoEntity, Integer>{
	
	MedicoEntity save(MedicoEntity medico);
	
	MedicoEntity findByIdMedico(Integer id);
	
	MedicoEntity findByMatricula(int matricula);
	
	Collection<MedicoEntity> findAll();

	Collection<MedicoEntity> findByApellidoContainingIgnoreCase(String apellido);
		
}
