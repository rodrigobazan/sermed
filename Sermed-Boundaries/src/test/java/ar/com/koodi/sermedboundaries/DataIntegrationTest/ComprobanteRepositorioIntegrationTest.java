package ar.com.koodi.sermedboundaries.DataIntegrationTest;

import Modelo.Afiliado;
import Modelo.Comprobante;
import Modelo.PeriodoPago;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioData.IComprobanteRepositorioCRUD;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.AfiliadoRepositorioImplementacion;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.ComprobanteRepositorioImplementacion;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.PeriodoPagoRepositorioImplementacion;
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
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:inicializarAfiliado.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
})
public class ComprobanteRepositorioIntegrationTest {

    @Autowired
    ComprobanteRepositorioImplementacion comprobanteRepositorioImplementacion;

    @Autowired
    AfiliadoRepositorioImplementacion afiliadoRepositorioImplementacion;

    @Autowired
    PeriodoPagoRepositorioImplementacion periodoPagoRepositorioImplementacion;

    @Autowired
    IComprobanteRepositorioCRUD iComprobanteRepositorioCRUD;

    @Test
    public void persistComprobante_SeGuardaCorrectamente_DevuelveTrue(){
        Comprobante comprobante = new Comprobante(null, "0001-000001", factoryAfiliado(),
                15000.0, LocalDate.now(), "Efectivo", true, factoryPeriodoDePago());
        boolean resultado = comprobanteRepositorioImplementacion.persist(comprobante);
        Assert.assertEquals(3, comprobante.getPeriodosAbonados().size());
        Assert.assertTrue(resultado);
    }

    @Test
    public void findByNumero_ExisteNumero_DevuelveComprobante(){
        factoryComprobante();
        Comprobante buscado = comprobanteRepositorioImplementacion.findByNumero("1234-000001");
        Assert.assertEquals(15000.0, buscado.getTotal(), 2);
    }

    @Test
    public void findByNumero_NoExisteNumero_DevuelveNull(){
        factoryComprobante();
        Comprobante buscado = comprobanteRepositorioImplementacion.findByNumero("4321-000001");
        Assert.assertNull(buscado);
    }

    @Test
    public void findAll_ExistenComprobantes_DevuelveListaConDatos(){
        factoryComprobante();
        List<Comprobante> comprobantes = (List<Comprobante>) comprobanteRepositorioImplementacion.findAll();
        Assert.assertEquals(3, comprobantes.size());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
    public void findAll_NoExistenComprobantes_DevuelveListaVacia(){
        List<Comprobante> comprobantes = (List<Comprobante>) comprobanteRepositorioImplementacion.findAll();
        Assert.assertEquals(0, comprobantes.size());
    }

    @Test
    public void findByFechas_ExistenComprobantes_DevuelveListaConDatos(){
        factoryComprobante();
        List<Comprobante> comprobantes = (List<Comprobante>) comprobanteRepositorioImplementacion.findByFechas(LocalDate.of(2018,6,6), LocalDate.of(2018,8,10));
        Assert.assertEquals(2, comprobantes.size());
    }

    @Test
    public void findByFechas_NoExistenComprobantes_DevuelveListaVacia(){
        factoryComprobante();
        List<Comprobante> comprobantes = (List<Comprobante>) comprobanteRepositorioImplementacion.findByFechas(LocalDate.of(2018,4,6), LocalDate.of(2018,6,5));
        Assert.assertEquals(0, comprobantes.size());
    }

    @Test
    public void findByAfiliado_TieneComprobantes_DevuelveListaConDatos(){
        factoryComprobante();
        List<Comprobante> comprobantes = (List<Comprobante>) comprobanteRepositorioImplementacion.findByAfiliado(factoryAfiliado());
        Assert.assertEquals(3, comprobantes.size());
    }

    @Test
    public void findByAfiliado_NoTienComprobantes_DevuelveListaVacia(){
        List<Comprobante> comprobantes = (List<Comprobante>) comprobanteRepositorioImplementacion.findByAfiliado(factoryAfiliado());
        Assert.assertEquals(0, comprobantes.size());
    }

    @Test
    public void uptade_ActualizaCorrectamente_DevuelveTrue(){
        factoryComprobante();
        Comprobante comprobante = comprobanteRepositorioImplementacion.findByNumero("1234-000001");
        comprobante.anularComprobante();
        boolean resultado = comprobanteRepositorioImplementacion.update(comprobante);
        Assert.assertTrue(resultado);
    }

    private List<PeriodoPago> factoryPeriodoDePago(){
        List<PeriodoPago> periodoPagos = (List<PeriodoPago>) periodoPagoRepositorioImplementacion.findIntervalo(1,3,2017);
        return periodoPagos;
    }

    private Afiliado factoryAfiliado(){
        return afiliadoRepositorioImplementacion.findById(1);
    }

    private void factoryComprobante(){
        Comprobante comprobante = new Comprobante(null, "1234-000001", factoryAfiliado(),
                15000.0, LocalDate.now(), "Efectivo", true, factoryPeriodoDePago());
        Comprobante comprobante2 = new Comprobante(null, "1234-000002", factoryAfiliado(),
                15000.0, LocalDate.of(2018, 7, 9), "Efectivo", true, factoryPeriodoDePago());
        Comprobante comprobante3 = new Comprobante(null, "1234-000002", factoryAfiliado(),
                15000.0, LocalDate.of(2018,8,9), "Efectivo", true, factoryPeriodoDePago());
        comprobanteRepositorioImplementacion.persist(comprobante);
        comprobanteRepositorioImplementacion.persist(comprobante2);
        comprobanteRepositorioImplementacion.persist(comprobante3);
    }

}
