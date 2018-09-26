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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarPeriodoPagoUnitTest {

    @Mock
    IPeriodoPagoRepositorio iPeriodoPagoRepositorio;

    @Spy
    List<PeriodoPago> periodoPagos = crearPeriodoPago();

    @Test
    public void consultarPeriodoPago_ExistenDatos_ColleccionConDatos(){
        when(iPeriodoPagoRepositorio.findAll()).thenReturn(periodoPagos);
        ConsultarPeriodoPagoUseCase consultarPeriodoPago = new ConsultarPeriodoPagoUseCase(iPeriodoPagoRepositorio);
        List<PeriodoPago> periodoPagos = consultarPeriodoPago.consultarPeriodoPago();
        assertThat(periodoPagos, not(IsEmptyCollection.empty()));
        assertEquals(periodoPagos.size(),5);
    }

    @Test
    public void consultarPeriodoPago_NoExistenDatos_ColleccionVacia(){
        when(iPeriodoPagoRepositorio.findAll()).thenReturn(new ArrayList<>());
        ConsultarPeriodoPagoUseCase consultarPeriodoPago = new ConsultarPeriodoPagoUseCase(iPeriodoPagoRepositorio);
        List<PeriodoPago> periodoPagos = consultarPeriodoPago.consultarPeriodoPago();
        assertThat(periodoPagos, IsEmptyCollection.empty());
        assertEquals(periodoPagos.size(),0);
    }


    private List<PeriodoPago> crearPeriodoPago() {
        try {
            List<PeriodoPago> lista = new ArrayList<>();
            lista.add(new PeriodoPago(1, 5, 2018));
            lista.add(new PeriodoPago(2, 6, 2018));
            lista.add(new PeriodoPago(3, 7, 2018));
            lista.add(new PeriodoPago(4, 8, 2018));
            lista.add(new PeriodoPago(5, 9, 2018));
            return lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
