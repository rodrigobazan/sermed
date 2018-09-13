package ar.com.koodi.sermeddata.IntegrationTest;

import Modelo.*;
import ar.com.koodi.sermeddata.RepositorioImplementacion.PersonaRepositorioImplementacion;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class PersonaRepositorioIntegrationTest{

    @Autowired
    PersonaRepositorioImplementacion personaRepositorioImplementacion;

    @Test
    public void personaPersist_SeGuardaCorrectamente_DevuleveTrue(){
        Persona persona = new Persona(1,"Perez","Juan", LocalDate.of(2011,9,3),"julian amatte 21",new TipoDocumento(1,"dni"),"12332123",new Sangre(1,"b","+"),"423220",new ObraSocial(1,"pami"),"123", factoryAntecedentesMedicos(), 1);
        boolean resultado = personaRepositorioImplementacion.persist(persona);
        Assert.assertTrue(resultado);
    }

    public Collection<AntecedenteMedico> factoryAntecedentesMedicos(){
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1, "Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2, new Afeccion(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3, new Afeccion(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }

}