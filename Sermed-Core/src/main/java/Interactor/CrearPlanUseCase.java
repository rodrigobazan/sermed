package Interactor;

import Excepciones.PlanExisteException;
import Inputs.CrearPlanInput;
import Modelo.Plan;
import Repositorio.IPlanRepositorio;

public class CrearPlanUseCase implements CrearPlanInput{
    private IPlanRepositorio repositorioPlan;

    public CrearPlanUseCase(IPlanRepositorio repositorioPlan) {
        this.repositorioPlan = repositorioPlan;
    }

    public boolean crearPlan(Plan plan) throws PlanExisteException {
        if(!validarPlanExiste(plan)){
            return repositorioPlan.persist(plan);
        }
        throw new PlanExisteException();
    }

    private boolean validarPlanExiste(Plan plan) {
        return repositorioPlan.findUnicoByNombre(plan.getNombre()) != null;
    }
}
