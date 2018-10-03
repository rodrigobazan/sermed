package ar.com.koodi.sermeddata.IntegrationTest;

import Excepciones.PlanIncompletoException;
import Modelo.Plan;
import ar.com.koodi.sermeddata.ModeloData.PlanEntity;
import ar.com.koodi.sermeddata.RepositorioImplementacion.PlanRepositorioImplementacion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:limpiarbase.sql"),
})
public class PlanRepositorioIntegrationTest {

    @Autowired
    PlanRepositorioImplementacion planRepositorioImplementacion;

    @Test
    public void planPersist_AlmacenaCorrectamente_DevuelveTrue() throws PlanIncompletoException {
        Plan plan = Plan.instancia(null, "Plan Basico", factoryListaPrecios());
        boolean resultado = planRepositorioImplementacion.persist(plan);
        Assert.assertTrue(resultado);
    }

    @Test
    public void findUnicoByNombre_ExisteNombre_DevuelvePlan() {
        factoryPlanes();
        Plan planBuscado = planRepositorioImplementacion.findUnicoByNombre("Plan Especial");
        Assert.assertNotNull(planBuscado);
    }

    @Test
    public void findUnicoByNombre_NoExisteNombre_DevuelveNull(){
        factoryPlanes();
        Plan planBuscado = planRepositorioImplementacion.findUnicoByNombre("Plan");
        Assert.assertNull(planBuscado);
    }

    @Test
    public void findByNombre_ExistenPlanes_DevuelveListaDePlanes(){
        factoryPlanes();
        List<Plan> planess = (List<Plan>) planRepositorioImplementacion.findByNombre("basico");
        Assert.assertEquals(2, planess.size());
    }

    @Test
    public void findByNombre_NoExistenPlanes_DevuelveListaVacia(){
        factoryPlanes();
        List<Plan> planes = (List<Plan>) planRepositorioImplementacion.findByNombre("efectiv");
        Assert.assertTrue(planes.isEmpty());
    }

    @Test
    public void findById_ExisteId_DevuelvePlan() throws PlanIncompletoException {
        Plan plan = Plan.instancia(null, "Plan Basico", factoryListaPrecios());
        planRepositorioImplementacion.persist(plan);
        Plan planBuscado = planRepositorioImplementacion.findById(1);
        Assert.assertNotNull(planBuscado);
    }

    @Test
    public void update_ActualizaCorrectamente_Devuelvetrue() throws PlanIncompletoException {
        Plan plan = Plan.instancia(null, "Plan Basico", factoryListaPrecios());
        Plan planModificado = Plan.instancia(1, "Plan Basico Nuevo", factoryListaPrecios());
        planRepositorioImplementacion.persist(plan);
        boolean resultado = planRepositorioImplementacion.update(planModificado);
        Plan rebusquedaPlan = planRepositorioImplementacion.findById(1);
        Assert.assertTrue(resultado);
        Assert.assertEquals("Plan Basico Nuevo", rebusquedaPlan.mostrarPlan());
    }


    @Test
    public void mapeoCoreData_MapeaCorrectamente_PlanSinId() throws PlanIncompletoException {
        Plan plan = Plan.instancia(null, "Plan Basico", factoryListaPrecios());
        PlanEntity planEntity = planRepositorioImplementacion.mapeoCoreData(plan);
        boolean atributosMapeados = planEntity.getNombrePlan().equals(plan.getNombre()) && !planEntity.getListaPrecios().isEmpty();
        Assert.assertNull(planEntity.getIdPlan());
        Assert.assertTrue(atributosMapeados);
    }

    private void factoryPlanes() {
        try {
            planRepositorioImplementacion.persist(Plan.instancia(null, "Plan Basico", factoryListaPrecios()));
            planRepositorioImplementacion.persist(Plan.instancia(null, "Plan Basico con tarjeta", factoryListaPrecios()));
            planRepositorioImplementacion.persist(Plan.instancia(null, "Plan Especial", factoryListaPrecios()));
            planRepositorioImplementacion.persist(Plan.instancia(null, "Plan Especial con tarjeta", factoryListaPrecios()));
        } catch (PlanIncompletoException e) {
            e.printStackTrace();
        }
    }


    private HashMap<String, Double> factoryListaPrecios() {
        try {
            HashMap<String, Double> listaPrecios = new HashMap<>();
            listaPrecios.put("1", (double) 400);
            listaPrecios.put("2", (double) 480);
            listaPrecios.put("3", (double) 550);
            listaPrecios.put("4", (double) 600);
            listaPrecios.put("5", (double) 650);
            listaPrecios.put("6", (double) 700);
            listaPrecios.put("7", (double) 750);
            return listaPrecios;
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<>();
        }
    }

}
