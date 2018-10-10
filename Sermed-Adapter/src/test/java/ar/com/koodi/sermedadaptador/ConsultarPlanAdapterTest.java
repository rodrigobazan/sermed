package ar.com.koodi.sermedadaptador;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import Adaptadores.ConsultarPlanAdapter;
import Excepciones.PlanIncompletoException;
import Inputs.ConsultarPlanInput;
import Mockito.MockitoExtension;
import Modelo.Plan;
import ModeloApi.PlanDTO;

@ExtendWith(MockitoExtension.class)
public class ConsultarPlanAdapterTest {

	@Mock 
	private ConsultarPlanInput consultarPlanInput;
	
	@Test
	public void consultarPlanes_ExistenPlanes_ReturnListaPlanes() throws PlanIncompletoException {		
		ConsultarPlanAdapter consultarPlanAdapter = new ConsultarPlanAdapter(consultarPlanInput);
		when(consultarPlanInput.consultarPlanes()).thenReturn(factoryPlanes());
		List<PlanDTO> planes = consultarPlanAdapter.consultarPlanes();
		Assertions.assertEquals(4, planes.size());		
	}
	
	@Test
	public void consultarPlanes_NoExistenPlanes_ReturnListaVacia() throws PlanIncompletoException {		
		ConsultarPlanAdapter consultarPlanAdapter = new ConsultarPlanAdapter(consultarPlanInput);
		when(consultarPlanInput.consultarPlanes()).thenReturn(new ArrayList<>());
		List<PlanDTO> planes = consultarPlanAdapter.consultarPlanes();			
		assertThat(planes, IsEmptyCollection.empty());
	}
	
	@Test
	public void consultarPlanesPorNombre_ExistenPlanes_ReturnListaPlanes() throws PlanIncompletoException {
		ConsultarPlanAdapter consultarPlanAdapter = new ConsultarPlanAdapter(consultarPlanInput);
		when(consultarPlanInput.consultarPlanesPorNombre("basico")).thenReturn(factoryPlanesFiltro());
		List<PlanDTO> planes = consultarPlanAdapter.consultarPlanesPorNombre("basico");			
		Assertions.assertEquals(2, planes.size());
	}

	private List<Plan> factoryPlanesFiltro() throws PlanIncompletoException{
        List<Plan> planes = new ArrayList<>();
        planes.add(Plan.instancia(1, "Plan Basico", factoryListaPrecios()));
        planes.add(Plan.instancia(2, "Plan Basico con tarjeta", factoryListaPrecios()));		
		return planes;
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
    
    private List<Plan> factoryPlanes(){
        try {
            List<Plan> planes = new ArrayList<>();
            planes.add(Plan.instancia(1, "Plan Basico", factoryListaPrecios()));
            planes.add(Plan.instancia(2, "Plan Basico con tarjeta", factoryListaPrecios()));
            planes.add(Plan.instancia(3, "Plan Especial", factoryListaPrecios()));
            planes.add(Plan.instancia(4, "Plan Especial con tarjeta", factoryListaPrecios()));
            return planes;
        } catch (PlanIncompletoException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


}
