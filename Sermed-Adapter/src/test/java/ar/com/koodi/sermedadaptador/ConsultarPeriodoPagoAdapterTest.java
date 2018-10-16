package ar.com.koodi.sermedadaptador;

import Adaptadores.ConsultarPeriodoPagoAdapter;
import Inputs.ConsultarPeriodoPagoInput;
import Mockito.MockitoExtension;


import Modelo.PeriodoPago;
import ModeloApi.PeriodoPagoDTO;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarPeriodoPagoAdapterTest {

    @Mock
    ConsultarPeriodoPagoInput consultarPeriodoPagoInput;

    @Spy
    Collection<PeriodoPago> periodoPago = factoryPeriodoPago();

    @Test
    public void consultarPeriodoPago_ExistenDatos_ColeccionConDatos(){
        when(consultarPeriodoPagoInput.consultarPeriodoPago()).thenReturn(periodoPago);
        ConsultarPeriodoPagoAdapter consultarPeriodoPagoAdapter = new ConsultarPeriodoPagoAdapter(consultarPeriodoPagoInput);
        List<PeriodoPagoDTO> periodoPagoDTOList = consultarPeriodoPagoAdapter.consultarPeriodoPago();
        assertThat(periodoPagoDTOList, not(IsEmptyCollection.empty()));
        assertEquals(periodoPagoDTOList.size(), periodoPago.size());
    }


    @Test
    public void consultarPeriodoPago_NoExistenDatos_ColeccionVacia(){
        when(consultarPeriodoPagoInput.consultarPeriodoPago()).thenReturn(new ArrayList<>());
        ConsultarPeriodoPagoAdapter consultarPeriodoPagoAdapter = new ConsultarPeriodoPagoAdapter(consultarPeriodoPagoInput);
        List<PeriodoPagoDTO> periodoPagoDTOList = consultarPeriodoPagoAdapter.consultarPeriodoPago();
        assertThat(periodoPagoDTOList, IsEmptyCollection.empty());
    }

    @Test
    public void consultarPeriodoPagoPorMes_CadenaDatos_DevolverAlgunos(){
        when(consultarPeriodoPagoInput.consultarPeriodoPagoPorMes(1)).thenReturn( factoryFiltroPorMes());
        ConsultarPeriodoPagoAdapter consultarPeriodoPagoAdapter = new ConsultarPeriodoPagoAdapter(consultarPeriodoPagoInput);
        List<PeriodoPagoDTO> periodoPagoDTOList = consultarPeriodoPagoAdapter.consultarPeriodoPagoPorMes(1);
        assertThat(periodoPagoDTOList, hasSize(2));
    }

    @Test
    public void consultarPeriodoPagoPorAnio_CadenaDatos_DevolverAlgunos(){
        when(consultarPeriodoPagoInput.consultarPeriodoPagoPorAnio(1)).thenReturn( factoryFiltroPorMes());
        ConsultarPeriodoPagoAdapter consultarPeriodoPagoAdapter = new ConsultarPeriodoPagoAdapter(consultarPeriodoPagoInput);
        List<PeriodoPagoDTO> periodoPagoDTOList = consultarPeriodoPagoAdapter.consultarPeriodoPagoPorAnio(1);
        assertThat(periodoPagoDTOList, hasSize(2));
    }

    @Test
    public void consultarPeriodoPagoPorIntervalo_CadenaDatos_DevolverAlgunos(){
        when(consultarPeriodoPagoInput.consultarPeriodoPagoPorIntervalo(1,4,2018)).thenReturn(crearPeriodoPagoFiltro());
        ConsultarPeriodoPagoAdapter consultarPeriodoPagoAdapter = new ConsultarPeriodoPagoAdapter(consultarPeriodoPagoInput);
        List<PeriodoPagoDTO> periodoPagoDTOList = consultarPeriodoPagoAdapter.consultarPeriodoPagoPorIntervalo(1,4,2018);
        assertThat(periodoPagoDTOList, hasSize(3));
    }





    private Collection<PeriodoPago> factoryPeriodoPago() {
        try {
            Collection<PeriodoPago> lista = new ArrayList<>();
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

    private Collection<PeriodoPago> crearPeriodoPagoFiltro() {
        try {
            Collection<PeriodoPago> lista = new ArrayList<>();
            lista.add(new PeriodoPago(10, 1, 2017));
            lista.add(new PeriodoPago(11, 2, 2017));
            lista.add(new PeriodoPago(12, 3, 2017));
            return lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Collection<PeriodoPago> factoryFiltroPorMes() {
        Collection<PeriodoPago> lista = new ArrayList<>();
        lista.add(new PeriodoPago(10, 1, 2017));
        lista.add(new PeriodoPago(10, 1, 2018));
        return lista;
    }
}
