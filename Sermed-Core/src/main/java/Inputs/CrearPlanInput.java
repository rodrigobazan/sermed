package Inputs;

import Excepciones.PlanExisteException;
import Modelo.Plan;

public interface CrearPlanInput {

	boolean crearPlan(Plan plan) throws PlanExisteException;
}
