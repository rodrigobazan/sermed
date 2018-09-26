package ar.com.koodi.sermeddata.IntegrationTest;

import Modelo.Afiliado;
import Modelo.Comprobante;
import Modelo.PeriodoPago;
import ar.com.koodi.sermeddata.RepositorioImplementacion.AfiliadoRepositorioImplementacion;
import ar.com.koodi.sermeddata.RepositorioImplementacion.ComprobanteImplementacion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:inicializarAfiliado.sql")
})
public class ComprobanteRepositorioIntegrationTest {

    @Autowired
    ComprobanteImplementacion comprobanteImplementacion;

    @Autowired
    AfiliadoRepositorioImplementacion afiliadoRepositorioImplementacion;

    @Test
    public void persistComprobante_SeGuardaCorrectamente_DevuelveTrue(){
        Comprobante comprobante = new Comprobante(null, "0001-000001", factoryAfiliado(),
                15000.0, LocalDate.now(), "Efectivo", true, factoryPeriodoDePago());
        boolean resultado = comprobanteImplementacion.persist(comprobante);
        Assert.assertTrue(resultado);
    }


    private List<PeriodoPago> factoryPeriodoDePago(){
        List<PeriodoPago> periodoPagos = new ArrayList<>();
        periodoPagos.add(new PeriodoPago(1, 5,2018));
        periodoPagos.add(new PeriodoPago(2, 6,2018));
        periodoPagos.add(new PeriodoPago(3, 7,2018));
        periodoPagos.add(new PeriodoPago(4, 8,2018));
        return periodoPagos;
    }

    private Afiliado factoryAfiliado(){
        return afiliadoRepositorioImplementacion.findById(1);
    }




}
