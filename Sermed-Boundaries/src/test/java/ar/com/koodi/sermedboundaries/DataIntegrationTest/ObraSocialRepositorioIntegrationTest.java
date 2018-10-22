package ar.com.koodi.sermedboundaries.DataIntegrationTest;

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

import Modelo.ObraSocial;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.ObraSocialRepositorioImplementacion;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@SqlGroup({
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
})
public class ObraSocialRepositorioIntegrationTest {
	
	@Autowired
	ObraSocialRepositorioImplementacion obraSocialRepositorioImplementacion;
	
	@Test
	public void persistObraSocial_SeGuardaCorrectamente_DevuelveTrue() {
		ObraSocial obraSocial = new ObraSocial(null,"OSFATUN");
		boolean resultado = obraSocialRepositorioImplementacion.persist(obraSocial);
		Assert.assertTrue(resultado);		
	}

	@Test
	public void findByNombreUnico_ExisteObraSocial_DevuelveObraSocial() {
		ObraSocial obraSocial = new ObraSocial(null,"OSFATUN");
		obraSocialRepositorioImplementacion.persist(obraSocial);
		ObraSocial resultado = obraSocialRepositorioImplementacion.findByNombreUnico("OSFATUN");
		Assert.assertEquals(1,resultado.getIdObraSocial().intValue());
	}
	
	@Test
	public void findAll_ExistenObrasSociales_DevuelveListaConDatos() {
		factoryObrasSociales();
		List<ObraSocial> obrasSociales = (List<ObraSocial>) obraSocialRepositorioImplementacion.findAll();
		Assert.assertEquals(5, obrasSociales.size());
	}
	
	@Test
	public void findAll_NoExistenObrasSociales_DevuelveListaVacia() {
		List<ObraSocial> obrasSociales = (List<ObraSocial>) obraSocialRepositorioImplementacion.findAll();
		Assert.assertEquals(0, obrasSociales.size());
	}
	
	@Test
	public void findByNombre_ExistenObrasSociales_DevuelveListaConDatos() {
		factoryObrasSociales();
		List<ObraSocial> obrasSociales = (List<ObraSocial>) obraSocialRepositorioImplementacion.findByNombre("oS");
		Assert.assertEquals(3, obrasSociales.size());
	}
	
	@Test
	public void findByNombre_NoExistenObrasSociales_DevuelveListaVacia() {		
		List<ObraSocial> obrasSociales = (List<ObraSocial>) obraSocialRepositorioImplementacion.findByNombre("oS");
		Assert.assertEquals(0, obrasSociales.size());
	}
	
	@Test
	public void findById_ExisteObraSocial_DevuelveObraSocial() {
		ObraSocial obraSocial = new ObraSocial(null,"OSFATUN");
		obraSocialRepositorioImplementacion.persist(obraSocial);
		ObraSocial resultado = obraSocialRepositorioImplementacion.findById(1);
		Assert.assertEquals("OSFATUN",resultado.getNombre());
	}
	
	@Test
	public void updateObraSocial_ActualizaCorrectamente_DevuelveTrue() {
		ObraSocial obraSocial = new ObraSocial(null,"OSFATUN");
		ObraSocial obraSocialModificada = new ObraSocial(1,"OSFACTURA"); 
		obraSocialRepositorioImplementacion.persist(obraSocial);
		boolean resultado = obraSocialRepositorioImplementacion.update(obraSocialModificada);
		ObraSocial rebusqueda = obraSocialRepositorioImplementacion.findById(1);
		Assert.assertEquals("OSFACTURA",rebusqueda.getNombre());
		Assert.assertTrue(resultado);
	}
	
	private void factoryObrasSociales() {
		try {
			obraSocialRepositorioImplementacion.persist(new ObraSocial(null, "OSFATUN"));
			obraSocialRepositorioImplementacion.persist(new ObraSocial(null, "OSDE"));
			obraSocialRepositorioImplementacion.persist(new ObraSocial(null, "APOS"));
			obraSocialRepositorioImplementacion.persist(new ObraSocial(null, "PAMI"));
			obraSocialRepositorioImplementacion.persist(new ObraSocial(null, "SANCOR SALUD"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
