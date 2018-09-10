package ar.com.koodi.sermeddata.RepositorioData;

import ar.com.koodi.sermeddata.ModeloData.PlanEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface IPlanRepositorioCRUD extends CrudRepository<PlanEntity, Integer> {

    PlanEntity save(PlanEntity plan);

    PlanEntity findByNombrePlanEquals(String nombrePlan);

    Collection<PlanEntity> findByNombrePlanContainingIgnoreCase(String nombre);

    Collection<PlanEntity> findAll();

    PlanEntity findByIdPlan(Integer idPlan);
}
