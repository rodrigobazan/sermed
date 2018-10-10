package ar.com.koodi.sermedadaptador;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.ModificarPlanAdapter;
import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Excepciones.UpdatePlanException;
import Inputs.ModificarPlanInput;
import Mockito.MockitoExtension;
import Modelo.Plan;
import ModeloApi.PlanDTO;

@ExtendWith(MockitoExtension.class)
public class ModificarPlanAdapterTest {

	@Mock
	private ModificarPlanInput modificarPlanInput;
	
	@Test
	public void modificarPlan_ActualizaCorrectamente_ReturnObjetoModificado() throws PlanExisteException, PlanIncompletoException, UpdatePlanException {
		PlanDTO plan = new PlanDTO(1, "Plan Básico", factoryListaPrecios());
		ModificarPlanAdapter modificarPlanAdapter = new ModificarPlanAdapter(modificarPlanInput); 
		when(modificarPlanInput.modificarPlan(any(Plan.class))).thenReturn(factoryPlanModificado());
		PlanDTO planModificado = modificarPlanAdapter.modificarPlan(plan);
		Assertions.assertEquals("Plan Básico", planModificado.nombrePlan);
	}

	private Plan factoryPlanModificado() throws PlanIncompletoException {
		return Plan.instancia(1, "Plan Básico", factoryListaPrecios());
	}
	
	private HashMap<String, Double> factoryListaPrecios(){
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 380);
        listaPrecios.put("2", (double) 480);
        listaPrecios.put("3", (double) 550);
        listaPrecios.put("4", (double) 600);
        listaPrecios.put("5", (double) 650);
        listaPrecios.put("6", (double) 700);
        listaPrecios.put("7", (double) 750);

        return listaPrecios;
    }
}
