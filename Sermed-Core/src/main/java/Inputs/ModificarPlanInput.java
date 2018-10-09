package Inputs;

import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Excepciones.UpdatePlanException;
import Modelo.Plan;

public interface ModificarPlanInput {
	
	Plan modificarPlan(Plan plan) throws PlanExisteException, PlanIncompletoException, UpdatePlanException;

}
