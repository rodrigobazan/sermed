package ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion;

import Excepciones.PlanIncompletoException;
import Modelo.Plan;
import Repositorio.IPlanRepositorio;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.PlanEntity;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioData.IPlanRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

@Service
public class PlanRepositorioImplementacion implements IPlanRepositorio {

    @Autowired
    IPlanRepositorioCRUD iPlanRepositorioCRUD;

    @Override
    @Transactional(readOnly = true)
    public Plan findUnicoByNombre(String nombre) {
        PlanEntity planEntity = this.iPlanRepositorioCRUD.findByNombrePlanEquals(nombre);
        if (planEntity != null) return mapeoDataCore(planEntity);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Plan> findByNombre(String nombre) {
        Collection<Plan> planes = new ArrayList<>();
        this.iPlanRepositorioCRUD.findByNombrePlanContainingIgnoreCase(nombre).forEach(planEntity -> planes.add(mapeoDataCore(planEntity)));
        return planes;
    }

    @Override
    @Transactional
    public boolean persist(Plan plan) {
        return this.iPlanRepositorioCRUD.save(mapeoCoreData(plan)) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Plan> findAll() {
        Collection<Plan> planes = new ArrayList<>();
        this.iPlanRepositorioCRUD.findAll().forEach(planEntity -> planes.add(mapeoDataCore(planEntity)));
        return planes;
    }

    @Override
    @Transactional(readOnly = true)
    public Plan findById(Integer idPlan) {
        return mapeoDataCore(this.iPlanRepositorioCRUD.findByIdPlan(idPlan));
    }

    @Override
    @Transactional
    public boolean update(Plan plan) {
        PlanEntity planEntity = new PlanEntity(plan.getNombre(), plan.getListaPrecios());
        planEntity.setIdPlan(plan.getIdPlan());
        return this.iPlanRepositorioCRUD.save(planEntity) != null;

    }

    public PlanEntity mapeoCoreData(Plan plan) {
        if (plan.getIdPlan() == null) {
            return new PlanEntity(plan.getNombre(), plan.getListaPrecios());
        }
        return this.iPlanRepositorioCRUD.findByIdPlan(plan.getIdPlan());
    }

    public Plan mapeoDataCore(PlanEntity planEntity) {
        try {
            return Plan.instancia(planEntity.getIdPlan(), planEntity.getNombrePlan(), new HashMap<>(planEntity.getListaPrecios()));
        } catch (PlanIncompletoException pie) {
            pie.printStackTrace();
            return null;
        }
    }
}
