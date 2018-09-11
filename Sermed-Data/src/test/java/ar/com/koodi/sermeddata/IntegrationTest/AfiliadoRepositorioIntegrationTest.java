package ar.com.koodi.sermeddata.IntegrationTest;

import java.time.LocalDate;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import Excepciones.PlanIncompletoException;
import Modelo.Plan;
import ar.com.koodi.sermeddata.RepositorioImplementacion.AfiliadoRepositorioImplementacion;
import ar.com.koodi.sermeddata.RepositorioImplementacion.PlanRepositorioImplementacion;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AfiliadoRepositorioIntegrationTest {

	@Autowired
	PlanRepositorioImplementacion planRepositorioImplementacion; 
	
	@Autowired
	AfiliadoRepositorioImplementacion afiliadoRepositorioImplementacion;
	
//	@Test
	public void persistAfiliado_SeAlmacenaCorrectamente_DevuelveTrue() {
//		Afiliado afiliado = new Afiliado(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, null, factoryPlan());
	}
	
	private void factoryPlan() throws PlanIncompletoException {
		HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 400);
        listaPrecios.put("2", (double) 480);
        listaPrecios.put("3", (double) 550);
        listaPrecios.put("4", (double) 600);
        listaPrecios.put("5", (double) 650);
        listaPrecios.put("6", (double) 700);
        listaPrecios.put("7", (double) 750);
		Plan plan = Plan.instancia(1, "Plan Basico", listaPrecios);
		planRepositorioImplementacion.persist(plan);
	}
}


