package ar.com.koodi.sermeddata.IntegrationTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import Excepciones.AfiliadoSinPlanException;
import Excepciones.AfiliadoSinTitularException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Modelo.*;
import ar.com.koodi.sermeddata.RepositorioImplementacion.PersonaRepositorioImplementacion;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import Excepciones.PlanIncompletoException;
import ar.com.koodi.sermeddata.RepositorioImplementacion.AfiliadoRepositorioImplementacion;
import ar.com.koodi.sermeddata.RepositorioImplementacion.PlanRepositorioImplementacion;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:inicializarAfiliado.sql")
})
public class AfiliadoRepositorioIntegrationTest {

	@Autowired
	AfiliadoRepositorioImplementacion afiliadoRepositorioImplementacion;

	@Autowired
	PersonaRepositorioImplementacion personaRepositorioImplementacion;

	@Autowired
	PlanRepositorioImplementacion planRepositorioImplementacion;

	@Test
	public void persistAfiliado_TitularNoExisteSeAlmacenaCorrectamente_DevuelveTrue() throws AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
		Afiliado afiliado = Afiliado.instancia(1,LocalDate.of(2010,9,9), "190000",
				factoryPersonaNuevaEnLaBD(), new ArrayList<>(), true, null, 15, factoryPlan());
		boolean resultado = afiliadoRepositorioImplementacion.persist(afiliado);
		Persona persona = personaRepositorioImplementacion.findById(2);
		Assert.assertTrue(resultado);
		Assert.assertEquals("Perez",persona.getApellidos());
	}
	
	@Test
	public void persistAfiliado_TitularExisteSeAlmacenaCorrectamente_DevuelveTrue() throws AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
		Afiliado afiliado = Afiliado.instancia(1,LocalDate.of(2010,9,9), "190000",
				factoryPersonaTitular(), new ArrayList<>(), true, null, 15, factoryPlan());
		boolean resultado = afiliadoRepositorioImplementacion.persist(afiliado);
		Assert.assertTrue(resultado);
	}

	private Persona factoryPersonaNuevaEnLaBD(){
		return new Persona(null, "Perez", "Juan", LocalDate.of(2011, 9, 3), "julian amatte 21",
				new TipoDocumento(1, "DNI"), "12332123", new Sangre(1, "A", "RH+"), "423220",
				new ObraSocial(1, "OSFATUN"), "123", new ArrayList<>(), 1);
	}


	private Persona factoryPersonaTitular() {
		return personaRepositorioImplementacion.findById(1);
	}

	private Plan factoryPlan(){
		return planRepositorioImplementacion.findById(1);
	}

}


