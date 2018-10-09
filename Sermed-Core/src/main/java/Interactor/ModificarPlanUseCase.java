package Interactor;

import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Excepciones.UpdatePlanException;
import Inputs.ModificarPlanInput;
import Modelo.Plan;
import Repositorio.IPlanRepositorio;

public class ModificarPlanUseCase implements ModificarPlanInput {
    private IPlanRepositorio repositorioPlan;

    public ModificarPlanUseCase(IPlanRepositorio repositorioPlan) {
        this.repositorioPlan = repositorioPlan;
    }

    public Plan modificarPlan(Plan nuevosDatos) throws PlanExisteException, PlanIncompletoException, UpdatePlanException {
        Plan elPlanAModificar = repositorioPlan.findById(nuevosDatos.getIdPlan());
        if (repositorioPlan.findUnicoByNombre(nuevosDatos.getNombre()) == null || nuevosDatos.getNombre().equals(elPlanAModificar.getNombre())) {
            elPlanAModificar.modificarDatos(nuevosDatos);
            if(this.repositorioPlan.update(elPlanAModificar))
                return elPlanAModificar;
            throw new UpdatePlanException();
        }
        throw new PlanExisteException();
    }
}
