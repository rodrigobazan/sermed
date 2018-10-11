package Factorys;

import Excepciones.PlanIncompletoException;
import Modelo.Plan;
import ModeloApi.PlanDTO;

public class PlanFactory {

    private PlanFactory() {
    }

    static public Plan mapeoAdaptadorCore(PlanDTO plan) throws PlanIncompletoException {
        return Plan.instancia(plan.idPlan, plan.nombrePlan, plan.listaPrecios);
    }
}
