package InteractorTest;

import Excepciones.PlanIncompletoException;
import Interactor.ModificarPlanUseCase;
import Mockito.MockitoExtension;
import Modelo.Plan;
import Repositorio.IPlanRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModificarPlanUnitTest {

    @Mock
    IPlanRepositorio repositorioPlan;

    @Test
    public void modificarPlan_DatosPlan_DevuelveTrue() throws PlanIncompletoException {
        ModificarPlanUseCase modificarPlanUseCase = new ModificarPlanUseCase(repositorioPlan);

        when(repositorioPlan.findById(1)).thenReturn(Plan.instancia(1, "Plan Basico",factoryListaPreciosOriginal()));
        when(repositorioPlan.findUnicoByNombre("Plan Nuevo Basico")).thenReturn(null);
        when(repositorioPlan.update(any(Plan.class))).thenReturn(true);

        Plan planAModificar = repositorioPlan.findById(1);
        Plan nuevosDatos = Plan.instancia(1, "Plan Nuevo Basico",factoryListaPreciosModificada());
        boolean resultado = modificarPlanUseCase.modificarPlan(nuevosDatos);
        Assertions.assertTrue(resultado);
        Assertions.assertEquals(nuevosDatos.mostrarPlan(), planAModificar.mostrarPlan());
        Assertions.assertEquals(1, planAModificar.getIdPlan().intValue());
    }

    @Test
    public void modificarPlan_PlanExiste_NoActualiza() throws PlanIncompletoException {
        ModificarPlanUseCase modificarPlanUseCase = new ModificarPlanUseCase(repositorioPlan);
        when(repositorioPlan.findById(1)).thenReturn(Plan.instancia(1, "Plan Basico",factoryListaPreciosOriginal()));
        when(repositorioPlan.findUnicoByNombre("Plan Especial")).thenReturn(Plan.instancia(2, "Plan Especial", factoryListaPreciosOriginal()));

        Plan planNuevo = Plan.instancia(1, "Plan Especial", factoryListaPreciosModificada());
        boolean resultado = modificarPlanUseCase.modificarPlan(planNuevo);
        Assertions.assertFalse(resultado);

    }


    @Test
    public void modificarPlan_NoModificaNombre_ActualizaPlan() throws PlanIncompletoException {
        ModificarPlanUseCase modificarPlanUseCase = new ModificarPlanUseCase(repositorioPlan);
        when(repositorioPlan.findById(1)).thenReturn(Plan.instancia(1, "Plan Basico",factoryListaPreciosOriginal()));
        when(repositorioPlan.findUnicoByNombre("Plan Basico")).thenReturn(Plan.instancia(1, "Plan Basico", factoryListaPreciosOriginal()));
        when(repositorioPlan.update(any(Plan.class))).thenReturn(true);
        Plan planNuevo = Plan.instancia(1, "Plan Basico", factoryListaPreciosModificada());
        boolean resultado = modificarPlanUseCase.modificarPlan(planNuevo);
        Assertions.assertTrue(resultado);
    }


    private HashMap<String, Double> factoryListaPreciosOriginal(){
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

    private HashMap<String, Double> factoryListaPreciosModificada(){
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 400);
        listaPrecios.put("2", (double) 500);
        listaPrecios.put("3", (double) 600);
        listaPrecios.put("4", (double) 700);
        listaPrecios.put("5", (double) 800);
        listaPrecios.put("6", (double) 900);
        listaPrecios.put("7", (double) 1000);

        return listaPrecios;
    }
}
