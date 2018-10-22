package ar.com.koodi.sermedboundaries.DataIntegrationTest;


import Modelo.Sangre;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.SangreRepositorioImplementacion;

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
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:inicializar.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:limpiarbase.sql"),
})
public class SangreRepositorioIntegrationTest {

    @Autowired
    SangreRepositorioImplementacion sangreRepositorioImplementacion;

    @Test
    public void findbyGrupo_ExisteSangre_DevuelveLista(){
        List<Sangre> sangres = (List<Sangre>) sangreRepositorioImplementacion.findByGrupo("B");
        Assert.assertEquals(2,sangres.size());
    }

    @Test
    public void findByFactor_ExisteSangre_DevuelveLista(){
        List<Sangre> sangres = (List<Sangre>) sangreRepositorioImplementacion.findByFactor("RH-");
        Assert.assertEquals(4,sangres.size());
    }

    @Test
    public void findByGrupoFactor_ExisteSangre_DevuelveSangre(){
        Sangre sangreBuscada = sangreRepositorioImplementacion.findByGrupoFactor("B","RH-");
        Assert.assertEquals("B",sangreBuscada.getGrupo());
        Assert.assertEquals("RH-",sangreBuscada.getFactor());
    }

    @Test
    public void findByGrupoFactor_NoExisteSangre_DevuelveNull(){
        Sangre sangreBuscada = sangreRepositorioImplementacion.findByGrupoFactor("asdasd","asdasd");
        Assert.assertNull(sangreBuscada);
    }

    @Test
    public void findAll_ExistenDatos_DevuelveListaSangres(){
        List<Sangre> sangreList = (List<Sangre>) sangreRepositorioImplementacion.findAll();
        Assert.assertEquals(8, sangreList.size());
    }

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
    public void findAll_NoExistenDatos_DevuelveListaVacia(){
        List<Sangre> sangreList = (List<Sangre>) sangreRepositorioImplementacion.findAll();
        Assert.assertEquals(0, sangreList.size());
    }

}
