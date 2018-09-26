package ar.com.koodi.sermeddata.IntegrationTest;

import Modelo.PeriodoPago;
import ar.com.koodi.sermeddata.RepositorioImplementacion.PeriodoPagoRepositorioImplementacion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:inicializar.sql")
})
public class PeriodoPagoIntegrationTest {

    @Autowired
    PeriodoPagoRepositorioImplementacion periodoPagoRepositorioImplementacion;

    @Test
    public void findAll_ExistenPeriodos_DevuelveListaConDatos(){
        List<PeriodoPago> periodoPagoList = (List<PeriodoPago>) periodoPagoRepositorioImplementacion.findAll();
        Assert.assertEquals(15, periodoPagoList.size());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
    public void findAll_NoExistenPeriodos_DevuelveListaVacia(){
        List<PeriodoPago> periodoPagoList = (List<PeriodoPago>) periodoPagoRepositorioImplementacion.findAll();
        Assert.assertEquals(0, periodoPagoList.size());
    }

    @Test
    public void findByAnio_ExistenPeriodos_DevuelveListaConDatos(){
        List<PeriodoPago> periodoPagoList = (List<PeriodoPago>) periodoPagoRepositorioImplementacion.findByAnio(2017);
        Assert.assertEquals(3, periodoPagoList.size());
    }

    @Test
    public void findByAnio_NoExistenPeriodos_DevuelveListaVacia(){
        List<PeriodoPago> periodoPagoList = (List<PeriodoPago>) periodoPagoRepositorioImplementacion.findByAnio(2019);
        Assert.assertEquals(0, periodoPagoList.size());
    }

    @Test
    public void findByMes_ExistenPeriodos_DevuelveListaConDatos(){
        List<PeriodoPago> periodoPagoList = (List<PeriodoPago>) periodoPagoRepositorioImplementacion.findByMes(3);
        Assert.assertEquals(2, periodoPagoList.size());
    }

    @Test
    public void findByMesAnio_ExistenPeriodos_DevuelvePeriodo(){
        PeriodoPago periodoPago = periodoPagoRepositorioImplementacion.findByMesAnio(1, 2017);
        Assert.assertNotNull(periodoPago);
    }

    @Test
    public void findByMesAnio_NoExistenPeriodos_DevuelveNull(){
        PeriodoPago periodoPago = periodoPagoRepositorioImplementacion.findByMesAnio(1, 2019);
        Assert.assertNull(periodoPago);
    }

    @Test
    public void findIntervalo_ExistenPeriodos_DevuelveListaConDatos(){
        List<PeriodoPago> periodoPagos = (List<PeriodoPago>) periodoPagoRepositorioImplementacion.findIntervalo(1,4, 2018);
        Assert.assertEquals(4, periodoPagos.size());
    }
}
