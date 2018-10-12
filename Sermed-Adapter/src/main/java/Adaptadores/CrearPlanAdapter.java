package Adaptadores;

import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Factorys.PlanFactory;
import Inputs.CrearPlanInput;
import ModeloApi.PlanDTO;

public class CrearPlanAdapter {

	private CrearPlanInput crearPlanInput;

	public CrearPlanAdapter(CrearPlanInput crearPlanInput) {
		this.crearPlanInput = crearPlanInput;
	}

	public boolean crearPlan(PlanDTO plan) throws PlanExisteException, PlanIncompletoException {
		return crearPlanInput.crearPlan(PlanFactory.mapeoDTOCore(plan));
	}

}
