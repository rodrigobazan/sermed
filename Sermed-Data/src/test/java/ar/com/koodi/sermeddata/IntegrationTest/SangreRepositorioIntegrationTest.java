package ar.com.koodi.sermeddata.IntegrationTest;


import Modelo.Sangre;
import ar.com.koodi.sermeddata.ModeloData.SangreEntity;
import ar.com.koodi.sermeddata.RepositorioData.ISangreRepositorioCRUD;
import ar.com.koodi.sermeddata.RepositorioImplementacion.SangreRepositorioImplementacion;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SangreRepositorioIntegrationTest {

    @InjectMocks
    SangreRepositorioImplementacion sangreRepositorioImplementacion;

    @Mock
    ISangreRepositorioCRUD iSangreRepositorioCRUD;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findbyGrupo_ExisteSangre_DevuelveLista(){
        when(iSangreRepositorioCRUD.findByGrupo("B")).thenReturn(filtroFactorySangre());
        List<Sangre> sangres = (List<Sangre>) sangreRepositorioImplementacion.findByGrupo("B");
        Assert.assertEquals(2,sangres.size());
    }

    @Test
    public void findByFactor_ExisteSangre_DevuelveLista(){
        when(iSangreRepositorioCRUD.findByFactor("RH-")).thenReturn(filtroFactorySangreFactor());
        List<Sangre> sangres = (List<Sangre>) sangreRepositorioImplementacion.findByFactor("RH-");
        Assert.assertEquals(2,sangres.size());
    }

    @Test
    public void findByGrupoFactor_ExisteSangre_DevuelveSangre(){
        when(iSangreRepositorioCRUD.findByGrupoAndFactor("B","RH-")).thenReturn(crearSangre(2,"B","RH-"));
        Sangre sangreBuscada = sangreRepositorioImplementacion.findByGrupoFactor("B","RH-");
        Assert.assertEquals("B",sangreBuscada.getGrupo());
        Assert.assertEquals("RH-",sangreBuscada.getFactor());
    }

    @Test
    public void findByGrupoFactor_NoExisteSangre_DevuelveNull(){
        when(iSangreRepositorioCRUD.findByGrupoAndFactor("asdasd","asdasd")).thenReturn(null);
        Sangre sangreBuscada = sangreRepositorioImplementacion.findByGrupoFactor("asdasd","asdasd");
        Assert.assertNull(sangreBuscada);
    }

    @Test
    public void findAll_ExistenDatos_DevuelveListaSangres(){
        when(iSangreRepositorioCRUD.findAll()).thenReturn(factorySangre());
        List<Sangre> sangreList = (List<Sangre>) sangreRepositorioImplementacion.findAll();
        Assert.assertEquals(5, sangreList.size());
    }

    @Test
    public void findAll_NoExistenDatos_DevuelveListaVacia(){
        when(iSangreRepositorioCRUD.findAll()).thenReturn(new ArrayList<>());
        List<Sangre> sangreList = (List<Sangre>) sangreRepositorioImplementacion.findAll();
        Assert.assertEquals(0, sangreList.size());
    }

    private Collection<SangreEntity> factorySangre(){
        List<SangreEntity> tiposSangre = new ArrayList<>();
        tiposSangre.add(crearSangre(1,"A","RH+"));
        tiposSangre.add(crearSangre(2,"B","RH-"));
        tiposSangre.add(crearSangre(3,"AB","RH+"));
        tiposSangre.add(crearSangre(4,"0","RH-"));
        tiposSangre.add(crearSangre(5,"B","RH+"));
        return tiposSangre;
    }

    private Collection<SangreEntity> filtroFactorySangre(){
        List<SangreEntity> tiposSangre = new ArrayList<>();
        tiposSangre.add(crearSangre(2,"B","RH-"));
        tiposSangre.add(crearSangre(5,"B","RH+"));
        return tiposSangre;
    }

    private Collection<SangreEntity> filtroFactorySangreFactor(){
        List<SangreEntity> tiposSangre = new ArrayList<>();
        tiposSangre.add(crearSangre(2,"B","RH-"));
        tiposSangre.add(crearSangre(4,"0","RH-"));
        return tiposSangre;
    }



    private SangreEntity crearSangre(Integer id, String grupo, String factor) {
        SangreEntity sangreEntity = new SangreEntity(grupo,factor);
        sangreEntity.setIdSangre(id);
        return sangreEntity;
    }


}
