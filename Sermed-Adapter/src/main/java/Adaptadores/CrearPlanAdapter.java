package Adaptadores;

import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Factorys.PlanFactory;
import Inputs.CrearPlanInput;
import Modelo.Plan;
import ModeloApi.PlanDTO;

public class CrearPlanAdapter {

	private CrearPlanInput crearPlanInput;

	public CrearPlanAdapter(CrearPlanInput crearPlanInput) {
		this.crearPlanInput = crearPlanInput;
	}

	public boolean crearPlan(PlanDTO plan) throws PlanExisteException, PlanIncompletoException {
		return crearPlanInput.crearPlan(PlanFactory.mapeoAdaptadorCore(plan));
	}

}
