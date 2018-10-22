package ar.com.koodi.sermedboundaries.DataIntegrationTest;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import Modelo.Enfermero;
import Modelo.Medico;
import Modelo.Persona;
import Modelo.Visita;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.EnfermeroRepositorioImplementacion;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.MedicoRepositorioImplementacion;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.PersonaRepositorioImplementacion;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.VisitaRepositorioImplementacion;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:inicializarVisita.sql"),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
})
public class VisitaRepositorioIntegrationTest {

	@Autowired
	PersonaRepositorioImplementacion personaRepositorioImplementacion;
	
	@Autowired
	MedicoRepositorioImplementacion medicoRepositorioImplementacion; 
	
	@Autowired
	EnfermeroRepositorioImplementacion enfermeroRepositorioImplementacion;

	@Autowired
	VisitaRepositorioImplementacion visitaRepositorioImplementacion; 	
	
	@Test
	public void persist_GuardaCorrectamente_DevuelveTrue() {
		Visita visita = new Visita(null,10, factoryPersona(), LocalDateTime.now(),"Fiebre", "", "12", 38.5f, 40, 15, "","Ganglios inflamados", "Paperas", "Analgesicos y reposo", ""
				, factoryMedico(), factoryEnfermero());
		boolean resultado = visitaRepositorioImplementacion.persist(visita);
		Assert.assertEquals(true, resultado);		
	}
	
	@Test
	public void findByNumeroVisita_ExisteVisita_DevuelveVisita() {
		factoryVisita();
		Visita visitaFind= visitaRepositorioImplementacion.findbyNumero(10);
		Assert.assertEquals(10, visitaFind.getNumeroVisita());
		Assert.assertNotNull(visitaFind);		
	}
	
	@Test
	public void findByNumeroVisita_NoExisteVisita_DevuelveNull() {
		factoryVisita();
		Visita visitaFind= visitaRepositorioImplementacion.findbyNumero(888);		
		Assert.assertNull(visitaFind);		
	}
	
	@Test
	public void findByPersona_ExisteVisita_DevuelveVisita() {
		factoryVisita();		
		List<Visita> visitas = (List<Visita>) visitaRepositorioImplementacion.findByPersona(factoryPersona());
		Assert.assertEquals(3, visitas.size());
	}
	
	@Test
	public void findAll_ExistenVisitas_DevuelveListaConDatos() {
		factoryVisita();
		List<Visita> visitas = (List<Visita>) visitaRepositorioImplementacion.findAll();
		Assert.assertEquals(3, visitas.size());
	}
	
	@Test
	@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
	public void findAll_NoExistenVisitas_DevuelveListaVacia() {		
		List<Visita> visitas = (List<Visita>) visitaRepositorioImplementacion.findAll();
		Assert.assertEquals(0, visitas.size());
	}
	
	private Enfermero factoryEnfermero() {
		return enfermeroRepositorioImplementacion.findById(1);
	}

	private Persona factoryPersona() {
		return personaRepositorioImplementacion.findById(1);
	}
	
	private Medico factoryMedico() {
		return medicoRepositorioImplementacion.findById(1);
	}
	
	private void factoryVisita() {
		Visita visita = new Visita(null,10, factoryPersona(), LocalDateTime.now(),"Fiebre", "", "12", 38.5f, 40, 15, "","Ganglios inflamados", "Paperas", "Analgesicos y reposo", ""
				, factoryMedico(), factoryEnfermero());
		Visita visita2 = new Visita(null,11, factoryPersona(), LocalDateTime.of(2018, 7, 7, 18, 45),"Fiebre", "", "12", 38.5f, 40, 15, "", "Fiebre","Neumonia", "Analgesicos y reposo", ""
				, factoryMedico(), factoryEnfermero());
		Visita visita3 = new Visita(null,12, factoryPersona(), LocalDateTime.of(2018, 9, 9, 15, 30),"Fiebre", "", "12", 38.5f, 40, 15, "","Dolor de garganta", "Anginas", "Analgesicos y reposo", ""
				, factoryMedico(), factoryEnfermero());
		visitaRepositorioImplementacion.persist(visita);		
		visitaRepositorioImplementacion.persist(visita2);
		visitaRepositorioImplementacion.persist(visita3);
	}
}
