package ar.com.koodi.sermeddata.IntegrationTest;

import static org.mockito.Mockito.framework;

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

import Modelo.Medico;
import Modelo.Medico;
import Modelo.Medico;
import ar.com.koodi.sermeddata.ModeloData.MedicoEntity;
import ar.com.koodi.sermeddata.ModeloData.MedicoEntity;
import ar.com.koodi.sermeddata.RepositorioImplementacion.MedicoRepositorioImplementacion;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
})
public class MedicoRepositorioIntegrationTest {

	@Autowired
	MedicoRepositorioImplementacion medicoRepositorioImplementacion;

	@Test
	public void medicoPersist_SeAlmacenaCorrectamente_DevuelveTrue() {
		Medico medico = new Medico(null, "Vega", "Romina", 123123, "4564564");
		boolean resultado = medicoRepositorioImplementacion.persist(medico);
		Assert.assertTrue(resultado);
	}

	@Test
	public void findById_ExisteMedico_DevuelveMedico() {
		Medico medico = new Medico(null, "Vega", "Romina", 123123, "456456");
		medicoRepositorioImplementacion.persist(medico);
		Medico medicoData = medicoRepositorioImplementacion.findById(1);
		Assert.assertNotNull(medicoData);
	}
	
//	@Test
//	public void findById_NoExisteMedico_DevuelveNull() {		
//		Medico medicoData = medicoRepositorioImplementacion.findById(10);
//		Assert.assertNull(medicoData);
//	}	

	@Test
	public void findByMatricula_ExisteMatricula_DevuelveMedico() {
		Medico medico = new Medico(null, "Vega", "Romina", 123123, "456456");
		medicoRepositorioImplementacion.persist(medico);
		Medico medicoData = medicoRepositorioImplementacion.findByMatricula(123123);
		Assert.assertEquals("Vega", medicoData.getApellido());
	}
	
	@Test
	public void findByMatricula_NoExisteMatricula_DevuelveNull() {
		Medico medicoData = medicoRepositorioImplementacion.findByMatricula(123123);
		Assert.assertNull(medicoData);
	}

	@Test
	public void findAll_ExistenMedicos_DevuelveListaDeMedicos() {
		factoryMedicos();
		List<Medico> medicos = medicoRepositorioImplementacion.findAll();
		Assert.assertEquals(5, medicos.size());

	}
	
	@Test
	public void findAll_NoExistenMedicos_DevuelveListaVacia() {		
		List<Medico> medicos = medicoRepositorioImplementacion.findAll();
		Assert.assertEquals(0, medicos.size());
	}
		
	@Test
	public void findByApellido_ExistenMedicos_DevuelveListaMedicos() {
		factoryMedicos();
		List<Medico> medicos = medicoRepositorioImplementacion.findByApellido("Vega");
		Assert.assertEquals(1, medicos.size());
	}

	@Test
	public void findByApellido_NoExistenMedicos_DevuelveListaVacia() {
		factoryMedicos();
		List<Medico> medicos = medicoRepositorioImplementacion.findByApellido("ASdasd");
		Assert.assertEquals(0, medicos.size());
	}
	
	@Test
	public void update_ActualizaCorrectamente_DevuelveTrue() {
		Medico medico = new Medico(null, "Vega", "Romina", 123123, "456456");
		Medico medicoModificado = new Medico(1, "Vega Saavedra", "Romina", 123123, "456456");
		medicoRepositorioImplementacion.persist(medico);
		boolean resultado = medicoRepositorioImplementacion.update(medicoModificado);
		Medico rebusqueda = medicoRepositorioImplementacion.findById(1);
		Assert.assertTrue(resultado);
		Assert.assertEquals("Vega Saavedra", rebusqueda.getApellido());
	}

    @Test
    public void mapeoCoreData_MapeaCorrectamente_MedicoSinId(){
        Medico medico = new Medico(null, "torres","geerman",12015,"as212321");
        MedicoEntity enfermeroEntity = medicoRepositorioImplementacion.mapeoCoreData(medico);
        Assert.assertNull(enfermeroEntity.getIdMedico());
        boolean atributosMapeados=medico.getMatricula()==enfermeroEntity.getMatricula() &&
        		medico.getApellido().equals(enfermeroEntity.getApellido()) &&
        		medico.getNombre().equals(enfermeroEntity.getNombre()) &&
        		medico.getTelefono().equals(enfermeroEntity.getTelefono());
        Assert.assertTrue(atributosMapeados);
    }
	
    @Test
    public void mapeoDataCore_MapeaCorrectamente_MedicoConId(){
        MedicoEntity enfermeroEntity = new MedicoEntity("Torres", "German", 12015, "as212321");
        enfermeroEntity.setIdMedico(1);
        Medico enfermero = medicoRepositorioImplementacion.mapeoDataCore(enfermeroEntity);
        boolean atributosMapeados=enfermero.getMatricula()==enfermeroEntity.getMatricula() &&
                enfermero.getApellido().equals(enfermeroEntity.getApellido()) &&
                enfermero.getNombre().equals(enfermeroEntity.getNombre()) &&
                enfermero.getTelefono().equals(enfermeroEntity.getTelefono());
        Assert.assertTrue(atributosMapeados);
        Assert.assertEquals(1, enfermero.getIdMedico().intValue());
    }
    
	private void factoryMedicos() {
		try {
			medicoRepositorioImplementacion.persist(new Medico(null, "Vega", "Romina", 123123, "456456"));
			medicoRepositorioImplementacion.persist(new Medico(null, "Torres", "German", 123, "456456"));
			medicoRepositorioImplementacion.persist(new Medico(null, "Paez Yañez", "Martin", 456, "456456"));
			medicoRepositorioImplementacion.persist(new Medico(null, "Ruitti", "Javier", 789, "456456"));
			medicoRepositorioImplementacion.persist(new Medico(null, "Bazan", "Rodrigo", 654, "456456"));
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}

	}
}
