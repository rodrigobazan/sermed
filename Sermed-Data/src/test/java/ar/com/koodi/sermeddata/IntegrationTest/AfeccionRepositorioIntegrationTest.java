package ar.com.koodi.sermeddata.IntegrationTest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import Modelo.Afeccion;
import ar.com.koodi.sermeddata.ModeloData.AfeccionEntity;
import ar.com.koodi.sermeddata.RepositorioData.IAfeccionRepositorioCRUD;
import ar.com.koodi.sermeddata.RepositorioImplementacion.AfeccionRepositorioImplementacion;

@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:inicializar.sql"),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
})
public class AfeccionRepositorioIntegrationTest {		

	@Autowired
    AfeccionRepositorioImplementacion afeccionRepositorioImplementacion;
	
	@Test
	public void findAll_ExistenAfecciones_DevuelveListaAfecciones() {
		List<Afeccion> afecciones = afeccionRepositorioImplementacion.findAll();
		Assert.assertEquals(5, afecciones.size());
	}

	@Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:limpiarbase.sql")
	public void findAll_NoExistenAfecciones_DevuelveListaVacia() {
		List<Afeccion> afecciones = this.afeccionRepositorioImplementacion.findAll();
		Assert.assertEquals(0, afecciones.size());
	}

	@Test
	public void findByNombreUnico_ExistenAfeccion_DevuelveAfeccion() {
		Afeccion resultado = this.afeccionRepositorioImplementacion.findByNombreUnico("Gripe");
		Assert.assertEquals(2, resultado.getIdAfeccion().intValue());
	}

	@Test
	public void findByNombreUnico_NoExistenAfeccion_DevuelveNull() {
		Afeccion resultado = this.afeccionRepositorioImplementacion.findByNombreUnico("asd");
		Assert.assertNull(resultado);
	}

	@Test
	public void findByNombre_ExistenAfecciones_DevuelveListaAfecciones() {
		List<Afeccion> afecciones = this.afeccionRepositorioImplementacion.findByNombre("Infección");
		Assert.assertEquals(2, afecciones.size());
	}

	@Test
	public void findByNombre_NoExistenAfecciones_DevuelveListaVacia() {
		List<Afeccion> afecciones = this.afeccionRepositorioImplementacion.findByNombre("asd");
		Assert.assertEquals(0, afecciones.size());
	}
}
