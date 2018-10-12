package Factorys;

import Excepciones.PlanIncompletoException;
import Modelo.Plan;
import ModeloApi.PlanDTO;

public class PlanFactory {

    private PlanFactory() {
    }

    public static Plan mapeoDTOCore(PlanDTO plan) throws PlanIncompletoException {
        return Plan.instancia(plan.idPlan, plan.nombrePlan, plan.listaPrecios);
    }

    public static PlanDTO mapeoCoreDTO(Plan plan){
        return new PlanDTO(plan.getIdPlan(), plan.getNombre(), plan.getListaPrecios());
    }
}
