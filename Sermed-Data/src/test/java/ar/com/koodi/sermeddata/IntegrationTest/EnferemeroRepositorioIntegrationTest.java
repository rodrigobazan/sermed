package ar.com.koodi.sermeddata.IntegrationTest;

import Modelo.Enfermero;

import ar.com.koodi.sermeddata.RepositorioImplementacion.EnfermeroRepositorioImplementacion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnferemeroRepositorioIntegrationTest {

    @Autowired
    EnfermeroRepositorioImplementacion enfermeroRepositorioImplementacion;

    @Test
    public void enfermeroPersist_SeGuardaCorrectamente_DevuelveTrue(){
        Enfermero enfermero = new Enfermero(5, "torres","geerman",12015,"as212321");
        boolean resultado = enfermeroRepositorioImplementacion.persist(enfermero);
        Assert.assertTrue(resultado);
    }



}
