package InteractorTest;

import Interactor.ConsultarPeriodoPagoUseCase;
import Mockito.MockitoExtension;
import Modelo.PeriodoPago;
import Repositorio.IPeriodoPagoRepositorio;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarPeriodoPagoUnitTest {

    @Mock
    IPeriodoPagoRepositorio iPeriodoPagoRepositorio;

    @Spy
    List<PeriodoPago> periodoPagos = factoryPeriodoPago();

    @Test
    public void consultarPeriodoPago_ExistenDatos_ColleccionConDatos(){
        when(iPeriodoPagoRepositorio.findAll()).thenReturn(periodoPagos);
        ConsultarPeriodoPagoUseCase consultarPeriodoPago = new ConsultarPeriodoPagoUseCase(iPeriodoPagoRepositorio);
        List<PeriodoPago> periodoPagos = consultarPeriodoPago.consultarPeriodoPago();
        assertThat(periodoPagos, not(IsEmptyCollection.empty()));
        assertEquals(periodoPagos.size(),12);
    }

    @Test
    public void consultarPeriodoPago_NoExistenDatos_ColleccionVacia(){
        when(iPeriodoPagoRepositorio.findAll()).thenReturn(new ArrayList<>());
        ConsultarPeriodoPagoUseCase consultarPeriodoPago = new ConsultarPeriodoPagoUseCase(iPeriodoPagoRepositorio);
        List<PeriodoPago> periodoPagos = consultarPeriodoPago.consultarPeriodoPago();
        assertThat(periodoPagos, IsEmptyCollection.empty());
        assertEquals(periodoPagos.size(),0);
    }

    @Test
    public void consultarPeriodoPagoPorAnio_CriterioConDatos_DevuelveUnPeriodoDePago(){
        when(iPeriodoPagoRepositorio.findByAnio(2017)).thenReturn(crearPeriodoPagoFiltro());
        ConsultarPeriodoPagoUseCase consultarPeriodoPagoUseCase = new ConsultarPeriodoPagoUseCase(iPeriodoPagoRepositorio);
        List<PeriodoPago> periodoPagos = consultarPeriodoPagoUseCase.consultarPeriodoPagoPorAnio(2017);
        assertEquals(3, periodoPagos.size());
    }

    @Test
    public void consultarPeriodoPagoPorMesAnio_CriterioConDatos_DevuelveUnPeriodoDePago(){
        when(iPeriodoPagoRepositorio.findByMesAnio(3,2017)).thenReturn(new PeriodoPago(10,3,2017));
        ConsultarPeriodoPagoUseCase consultarPeriodoPagoUseCase = new ConsultarPeriodoPagoUseCase(iPeriodoPagoRepositorio);
        PeriodoPago buscado = consultarPeriodoPagoUseCase.consultarPeriodoPagoPorMesAnio(3,2017);
        assertNotNull(buscado);
    }

    @Test
    public void consultarPeriodoPagoPorMes_CriterioConDatos_DevuelveTodosLosDelAnio(){
        when(iPeriodoPagoRepositorio.findByMes(1)).thenReturn(factoryFiltroPorMes());
        ConsultarPeriodoPagoUseCase consultarPeriodoPagoUseCase = new ConsultarPeriodoPagoUseCase(iPeriodoPagoRepositorio);
        List<PeriodoPago> buscado = consultarPeriodoPagoUseCase.consultarPeriodoPagoPorMes(1);
        assertEquals(2, buscado.size());
    }

    @Test
    public void consultarPeriodoDePagoPorIntevalo_CriterioConDatos_DevuelveListaConDatos(){
        when(iPeriodoPagoRepositorio.findIntervalo(1, 4, 2017)).thenReturn(crearPeriodoPagoFiltro());
        ConsultarPeriodoPagoUseCase consultarPeriodoPagoUseCase = new ConsultarPeriodoPagoUseCase(iPeriodoPagoRepositorio);
        List<PeriodoPago> buscados = consultarPeriodoPagoUseCase.consultarPeriodoPagoPorIntervalo(1,4,2017);
        assertEquals(3, buscados.size());

    }

    private List<PeriodoPago> factoryPeriodoPago() {
        try {
            List<PeriodoPago> lista = new ArrayList<>();
            lista.add(new PeriodoPago(1, 1, 2018));
            lista.add(new PeriodoPago(2, 2, 2018));
            lista.add(new PeriodoPago(3, 3, 2018));
            lista.add(new PeriodoPago(4, 4, 2018));
            lista.add(new PeriodoPago(5, 5, 2018));
            lista.add(new PeriodoPago(6, 6, 2018));
            lista.add(new PeriodoPago(7, 7, 2018));
            lista.add(new PeriodoPago(8, 8, 2018));
            lista.add(new PeriodoPago(9, 9, 2018));
            lista.add(new PeriodoPago(10, 10, 2018));
            lista.add(new PeriodoPago(11, 11, 2018));
            lista.add(new PeriodoPago(12, 12, 2018));
            return lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<PeriodoPago> crearPeriodoPagoFiltro() {
        try {
            List<PeriodoPago> lista = new ArrayList<>();
            lista.add(new PeriodoPago(10, 1, 2017));
            lista.add(new PeriodoPago(11, 2, 2017));
            lista.add(new PeriodoPago(12, 3, 2017));
            return lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<PeriodoPago> factoryFiltroPorMes() {
        List<PeriodoPago> lista = new ArrayList<>();
        lista.add(new PeriodoPago(10, 1, 2017));
        lista.add(new PeriodoPago(10, 1, 2018));
        return lista;
    }
}
