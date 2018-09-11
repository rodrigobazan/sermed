package ar.com.koodi.sermeddata.RepositorioData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.koodi.sermeddata.ModeloData.AfiliadoEntity;

@Repository
public interface IAfiliadoRepositorioCRUD extends CrudRepository<AfiliadoEntity, Integer>{

	AfiliadoEntity save(AfiliadoEntity afiliado);
}
