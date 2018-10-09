package Inputs;

import java.util.List;

import Modelo.Plan;

public interface ConsultarPlanInput {

	List<Plan> consultarPlanes();
	
	List<Plan> consultarPlanesPorNombre(String nombre);
}
