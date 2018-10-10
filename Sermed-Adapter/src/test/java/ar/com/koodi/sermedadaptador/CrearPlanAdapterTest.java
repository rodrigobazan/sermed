package ar.com.koodi.sermedadaptador;

import java.util.HashMap;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.CrearPlanAdapter;
import Excepciones.PlanExisteException;
import Excepciones.PlanIncompletoException;
import Inputs.CrearPlanInput;
import Mockito.MockitoExtension;
import Modelo.Plan;
import ModeloApi.PlanDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearPlanAdapterTest {

	@Mock
	private CrearPlanInput crearPlanInput;
	
	@Test
	public void crearPlan_SeGuardaCorrectamente_ReturnTrue() throws PlanExisteException, PlanIncompletoException {
		PlanDTO plan = factoryPlan();
		CrearPlanAdapter crearPlanAdapter = new CrearPlanAdapter(crearPlanInput);
		when(crearPlanInput.crearPlan(any(Plan.class))).thenReturn(true);
		boolean resultado = crearPlanAdapter.crearPlan(plan);
		Assertions.assertTrue(resultado);
	}

	private PlanDTO factoryPlan() {
		return new PlanDTO(1, "Basico", factoryListaPrecios());
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
