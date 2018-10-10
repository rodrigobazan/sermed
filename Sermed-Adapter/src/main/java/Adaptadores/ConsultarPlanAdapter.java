package Adaptadores;

import java.util.ArrayList;
import java.util.List;

import Inputs.ConsultarPlanInput;
import Modelo.Plan;
import ModeloApi.PlanDTO;

public class ConsultarPlanAdapter {

	private ConsultarPlanInput consultarPlanInput;

	public ConsultarPlanAdapter(ConsultarPlanInput consultarPlanInput) {
		super();
		this.consultarPlanInput = consultarPlanInput;
	}

	public List<PlanDTO> consultarPlanes() {
		List<Plan> planes = consultarPlanInput.consultarPlanes();
		List<PlanDTO> planesDTO = new ArrayList<>();
		if (!planes.isEmpty()) {
			planes.stream().forEach(plan -> {
				planesDTO.add(mapeoCoreAdaptador(plan));
			});
		}
		return planesDTO;
	}

	public List<PlanDTO> consultarPlanesPorNombre(String nombre) {
		List<Plan> planes = consultarPlanInput.consultarPlanesPorNombre(nombre);
		List<PlanDTO> planesDTO = new ArrayList<>();
		if (!planes.isEmpty()) {
			planes.stream().forEach(plan -> {
				planesDTO.add(mapeoCoreAdaptador(plan));
			});
		}
		return planesDTO;
	}
	
	private PlanDTO mapeoCoreAdaptador(Plan plan) {
		return new PlanDTO(plan.getIdPlan(), plan.getNombre(), plan.getListaPrecios());
	}

}
