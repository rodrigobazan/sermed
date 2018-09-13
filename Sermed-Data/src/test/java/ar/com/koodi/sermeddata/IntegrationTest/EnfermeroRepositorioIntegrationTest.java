package ar.com.koodi.sermeddata.IntegrationTest;

import Excepciones.EnfermeroIncompletoException;
import Modelo.Enfermero;

import ar.com.koodi.sermeddata.ModeloData.EnfermeroEntity;
import ar.com.koodi.sermeddata.RepositorioImplementacion.EnfermeroRepositorioImplementacion;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EnfermeroRepositorioIntegrationTest {

    @Autowired
    EnfermeroRepositorioImplementacion enfermeroRepositorioImplementacion;

    @Test
    public void enfermeroPersist_AlmacenaCorrectamente_DevuelveTrue(){
        Enfermero enfermero = new Enfermero(1, "torres","geerman",12015,"as212321");
        boolean resultado = enfermeroRepositorioImplementacion.persist(enfermero);
        Assert.assertTrue(resultado);
    }

    @Test
    public void findById_ExisteEnfermero_DevuelveEnfermero(){
        Enfermero enfermero = new Enfermero(1, "torres","geerman",12015,"as212321");
        enfermeroRepositorioImplementacion.persist(enfermero);
        Enfermero enfermeroData = enfermeroRepositorioImplementacion.findById(1);
        Assert.assertNotNull(enfermeroData);
    }

    @Test
    public void findByMatricula_ExistenMatricula_DevuelveEnfermero(){
        Enfermero enfermero = new Enfermero(2, "Ruitti","Javier",123456,"38256789132");
        enfermeroRepositorioImplementacion.persist(enfermero);
        Enfermero enfermeroData = enfermeroRepositorioImplementacion.findByMatricula(123456);
        Assert.assertEquals("Ruitti", enfermeroData.getApellido());
    }

    @Test
    public void findByMatricula_NoExisteMatricula_DevuelveNull(){
        Enfermero enfermeroData = enfermeroRepositorioImplementacion.findByMatricula(987654321);
        Assert.assertNull(enfermeroData);
    }

    @Test
    public void findAll_ExistenEnfermeros_DevuelveListaDeEnfermeros(){
        factoryEnfermeros();
        List<Enfermero> enfermeros = enfermeroRepositorioImplementacion.findAll();
        Assert.assertEquals(5,enfermeros.size());
    }

    @Test
    public void findAll_NoExistenEnfermeros_DevuelveListaVacia(){
        List<Enfermero> enfermeros = enfermeroRepositorioImplementacion.findAll();
        Assert.assertEquals(0,enfermeros.size());
    }

    @Test
    public void findByApellido_ExisteEnfermero_DevuelveEnfermero(){
        factoryEnfermeros();
        List<Enfermero> enfermeroData = enfermeroRepositorioImplementacion.findByApellido("Torres");
        Assert.assertEquals(1, enfermeroData.size());
    }

    @Test
    public void findByApellido_NoExisteEnfermero_DevuelveListVacia(){
        List<Enfermero> enfermeros = enfermeroRepositorioImplementacion.findByApellido("asd");
        Assert.assertTrue(enfermeros.isEmpty());
    }

    @Test
    public void update_ActualizaCorrectamente_DevuelveTrue() {
        Enfermero enfermero = new Enfermero(1, "Ruitti","Javier",123456,"38256789132");
        Enfermero enfermeroModificado = new Enfermero(1, "itti","Alberto",123456,"38256789132");
        enfermeroRepositorioImplementacion.persist(enfermero);
        boolean resultado = enfermeroRepositorioImplementacion.update(enfermeroModificado);
        Enfermero rebusqueda=enfermeroRepositorioImplementacion.findById(1);
        Assert.assertTrue(resultado);
        Assert.assertEquals("itti", rebusqueda.getApellido());
    }


    @Test
    public void mapeoCoreData_MapeaCorrectamente_EnfermeroSinId(){
        Enfermero enfermero = new Enfermero(1, "torres","geerman",12015,"as212321");
        EnfermeroEntity enfermeroEntity = enfermeroRepositorioImplementacion.mapeoCoreData(enfermero);
        Assert.assertNull(enfermeroEntity.getIdEnfermero());
        boolean atributosMapeados=enfermero.getMatricula().intValue()==enfermeroEntity.getMatricula().intValue() &&
                enfermero.getApellido().equals(enfermeroEntity.getApellido()) &&
                enfermero.getNombre().equals(enfermeroEntity.getNombre()) &&
                enfermero.getTelefono().equals(enfermeroEntity.getTelefono());
        Assert.assertTrue(atributosMapeados);
    }

    @Test
    public void mapeoDataCore_MapeaCorrectamente_EnfermeroConId(){
        EnfermeroEntity enfermeroEntity = new EnfermeroEntity("Torres", "German", 12015, "as212321");
        enfermeroEntity.setIdEnfermero(1);
        Enfermero enfermero = enfermeroRepositorioImplementacion.mapeoDataCore(enfermeroEntity);
        boolean atributosMapeados=enfermero.getMatricula().intValue()==enfermeroEntity.getMatricula().intValue() &&
                enfermero.getApellido().equals(enfermeroEntity.getApellido()) &&
                enfermero.getNombre().equals(enfermeroEntity.getNombre()) &&
                enfermero.getTelefono().equals(enfermeroEntity.getTelefono());
        Assert.assertTrue(atributosMapeados);
        Assert.assertEquals(1, enfermero.getIdEnfermero().intValue());
    }


    public void factoryEnfermeros(){
        try {
            enfermeroRepositorioImplementacion.persist(new Enfermero(1, "Torres","German",123,"3825679414"));
            enfermeroRepositorioImplementacion.persist(new Enfermero(2, "Paez Yañez","Martin",456,"3825679414"));
            enfermeroRepositorioImplementacion.persist(new Enfermero(3, "Ruitti","Javier",789,"3825679414"));
            enfermeroRepositorioImplementacion.persist(new Enfermero(4, "Vega","Romina",987,"3825679414"));
            enfermeroRepositorioImplementacion.persist(new Enfermero(5, "Bazan","Rodrigo",654,"3825679414"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
