package Interactor;

import Modelo.Plan;
import Repositorio.IPlanRepositorio;

import java.util.List;

import Inputs.ConsultarPlanInput;

public class ConsultarPlanUseCase implements ConsultarPlanInput {
    private IPlanRepositorio repositorioPlan;

    public ConsultarPlanUseCase(IPlanRepositorio repositorioPlan) {
        this.repositorioPlan = repositorioPlan;
    }

    public List<Plan> consultarPlanes() {
        return (List<Plan>) repositorioPlan.findAll();
    }

    public List<Plan> consultarPlanesPorNombre(String nombrePlan) {
        return (List<Plan>) repositorioPlan.findByNombre(nombrePlan);

    }
}
