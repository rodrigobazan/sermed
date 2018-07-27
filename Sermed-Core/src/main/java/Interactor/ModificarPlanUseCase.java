package Interactor;

import Excepciones.PlanIncompletoException;
import Modelo.Plan;
import Repositorio.IPlanRepositorio;

public class ModificarPlanUseCase {
    private IPlanRepositorio repositorioPlan;

    public ModificarPlanUseCase(IPlanRepositorio repositorioPlan) {
        this.repositorioPlan = repositorioPlan;
    }

    public boolean modificarPlan(Plan nuevosDatos) {
        try {
            Plan elPlanOriginal = repositorioPlan.findById(nuevosDatos.getIdPlan());
            if (repositorioPlan.findUnicoByNombre(nuevosDatos.getNombre()) == null || nuevosDatos.getNombre().equals(elPlanOriginal.getNombre())) {
                elPlanOriginal.modificarDatos(nuevosDatos);
                return this.repositorioPlan.update(elPlanOriginal);
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
