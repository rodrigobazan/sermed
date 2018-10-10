package Adaptadores;

import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Excepciones.UpdatePlanException;
import Inputs.ModificarPlanInput;
import Modelo.Plan;
import ModeloApi.PlanDTO;

public class ModificarPlanAdapter {

	private ModificarPlanInput modificarPlanInput;

	public ModificarPlanAdapter(ModificarPlanInput modificarPlanInput) {
		super();
		this.modificarPlanInput = modificarPlanInput;
	}

	public PlanDTO modificarPlan(PlanDTO planDTO) throws PlanExisteException, PlanIncompletoException, UpdatePlanException {
		return mapeoCoreAdaptador(modificarPlanInput.modificarPlan(mapeoAdaptadorCore(planDTO)));
	}

	private PlanDTO mapeoCoreAdaptador(Plan plan) {
		return new PlanDTO(plan.getIdPlan(),plan.getNombre(), plan.getListaPrecios());
	}

	private Plan mapeoAdaptadorCore(PlanDTO planDTO) throws PlanIncompletoException {
		return Plan.instancia(planDTO.idPlan, planDTO.nombrePlan, planDTO.listaPrecios);
	}
	
	
}
