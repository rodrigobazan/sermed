package ar.com.koodi.sermeddata.IntegrationTest;

import Modelo.TipoDocumento;
import ar.com.koodi.sermeddata.ModeloData.TipoDocumentoEntity;
import ar.com.koodi.sermeddata.RepositorioData.ITipoDocumentoRepositorioCRUD;
import ar.com.koodi.sermeddata.RepositorioImplementacion.TipoDocumentoRepositorioImplementacion;
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
public class TipoDocumentoRepositorioIntegrationTest {

    @Mock
    ITipoDocumentoRepositorioCRUD iTipoDocumentoRepositorioCRUD;

    @InjectMocks
    TipoDocumentoRepositorioImplementacion tipoDocumentoRepositorioImplementacion;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void findAll_HayDatos_DevuelveListaConDatos() {
        when(iTipoDocumentoRepositorioCRUD.findAll()).thenReturn(factoryTiposDocumentoEntity());
        List<TipoDocumento> tipoDocumentoList = (List<TipoDocumento>) tipoDocumentoRepositorioImplementacion.findAll();
        Assert.assertEquals(4, tipoDocumentoList.size());
    }

    @Test
    public void findAll_NoExistenDatos_DevuelveListaVacia() {
        when(iTipoDocumentoRepositorioCRUD.findAll()).thenReturn(new ArrayList<>());
        List<TipoDocumento> tipoDocumentoList = (List<TipoDocumento>) tipoDocumentoRepositorioImplementacion.findAll();
        Assert.assertEquals(0, tipoDocumentoList.size());
    }

    @Test
    public void findByNombre_ExistenCoincidencia_DevuelveListaConDatos() {
        when(iTipoDocumentoRepositorioCRUD.findByNombreContainingIgnoreCase("libreta")).thenReturn(factoryTiposDocumentoEntityFiltro());
        List<TipoDocumento> tipoDocumentoList = (List<TipoDocumento>) tipoDocumentoRepositorioImplementacion.findByNombre("libreta");
        Assert.assertEquals(2, tipoDocumentoList.size());
    }

    @Test
    public void findByNombre_NoExistenCoincidencia_DevuelveListaVacia() {
        when(iTipoDocumentoRepositorioCRUD.findByNombreContainingIgnoreCase("cedula")).thenReturn(new ArrayList<>());
        List<TipoDocumento> tipoDocumentoList = (List<TipoDocumento>) tipoDocumentoRepositorioImplementacion.findByNombre("cedula");
        Assert.assertEquals(0, tipoDocumentoList.size());
    }

    @Test
    public void findByNombreUnico_ExisteCoincidencia_DevuelveTipoDocumento() {
        TipoDocumentoEntity tipoDocumentoEntity = new TipoDocumentoEntity("Libreta Civica");
        tipoDocumentoEntity.setIdTipoDocumento(2);
        when(iTipoDocumentoRepositorioCRUD.findByNombreEqualsIgnoreCase("libreta civica")).thenReturn(tipoDocumentoEntity);
        TipoDocumento tipoDocumento = tipoDocumentoRepositorioImplementacion.findByNombreUnico("libreta civica");
        Assert.assertEquals("Libreta Civica", tipoDocumento.getNombre());
    }

    @Test
    public void findByNombreUnico_NoExisteCoincidencia_DevuelveNull() {
        when(iTipoDocumentoRepositorioCRUD.findByNombreEqualsIgnoreCase("cedula")).thenReturn(null);
        TipoDocumento tipoDocumento = tipoDocumentoRepositorioImplementacion.findByNombreUnico("cedula");
        Assert.assertNull(tipoDocumento);
    }


    @Test
    public void mapeoDataCore_MapeaCorrectamente_TipoDocumentoConId() {
        TipoDocumentoEntity tipoDocumentoEntity = new TipoDocumentoEntity("DNI");
        tipoDocumentoEntity.setIdTipoDocumento(1);
        TipoDocumento tipoDocumento = tipoDocumentoRepositorioImplementacion.mapeoDataCore(tipoDocumentoEntity);
        boolean atributosMapeados = tipoDocumento.getNombre().equals(tipoDocumentoEntity.getNombre());
        Assert.assertTrue(atributosMapeados);
        Assert.assertEquals(1, tipoDocumento.getIdTipoDocumento().intValue());
    }


    private Collection<TipoDocumentoEntity> factoryTiposDocumentoEntityFiltro() {
        try {
            List<TipoDocumentoEntity> lista = new ArrayList<>();
            lista.add(crearTipoDocumento(2,"Libreta Civica"));
            lista.add(crearTipoDocumento(4,"Libreta de Enrolamiento"));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Collection<TipoDocumentoEntity> factoryTiposDocumentoEntity() {
        try {
            List<TipoDocumentoEntity> lista = new ArrayList<>();
            lista.add(crearTipoDocumento(1,"DNI"));
            lista.add(crearTipoDocumento(2, "Libreta Civica"));
            lista.add(crearTipoDocumento(3, "Pasaporte"));
            lista.add(crearTipoDocumento(4, "Libreta de Enrolamiento"));
            return lista;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private TipoDocumentoEntity crearTipoDocumento(int idTipoDocumento, String nombre) {
        TipoDocumentoEntity tipoDocumentoEntity = new TipoDocumentoEntity(nombre);
        tipoDocumentoEntity.setIdTipoDocumento(idTipoDocumento);
        return tipoDocumentoEntity;
    }


}
