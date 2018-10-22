package ar.com.koodi.sermedboundaries.SermedData.RepositorioData;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.com.koodi.sermedboundaries.SermedData.ModeloData.AfiliadoEntity;

import java.util.Collection;

@Repository
public interface IAfiliadoRepositorioCRUD extends CrudRepository<AfiliadoEntity, Integer>{

	AfiliadoEntity save(AfiliadoEntity afiliado);

	AfiliadoEntity findByIdAfiliado(Integer id);

	Collection<AfiliadoEntity> findAll();

	Collection<AfiliadoEntity> findByNumeroAfiliadoContainsAndActivoTrue(String numero);

	AfiliadoEntity findByNumeroAfiliadoEqualsAndActivoTrue(String numero);

	Collection<AfiliadoEntity> findByActivoIsTrue();

	Collection<AfiliadoEntity> findByActivoIsFalse();

}
