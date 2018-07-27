package Interactor;

import Modelo.Plan;
import Repositorio.IPlanRepositorio;

public class CrearPlanUseCase {
    private IPlanRepositorio repositorioPlan;

    public CrearPlanUseCase(IPlanRepositorio repositorioPlan) {
        this.repositorioPlan = repositorioPlan;
    }

    public boolean crearPlan(Plan plan) {
        if(!validarPlanExiste(plan)){
            return repositorioPlan.persist(plan);
        }
        return false;
    }

    private boolean validarPlanExiste(Plan plan) {
        if(repositorioPlan.findUnicoByNombre(plan.getNombre()) != null) {
            return true;
        }
        return false;
    }
}
