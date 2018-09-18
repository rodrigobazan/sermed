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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import Modelo.Afeccion;
import ar.com.koodi.sermeddata.ModeloData.AfeccionEntity;
import ar.com.koodi.sermeddata.RepositorioData.IAfeccionRepositorioCRUD;
import ar.com.koodi.sermeddata.RepositorioImplementacion.AfeccionRepositorioImplementacion;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AfeccionRepositorioIntegrationTest {		
			
	@Mock
	IAfeccionRepositorioCRUD iAfeccionRepositorioCRUD;
	
	@InjectMocks	
	AfeccionRepositorioImplementacion afeccionRepositorioImplementacion;
	
	@Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void findAll_ExistenAfecciones_DevuelveListaAfecciones() {		
		when(iAfeccionRepositorioCRUD.findAll()).thenReturn(factoryAfecciones());
		List<Afeccion> afecciones = afeccionRepositorioImplementacion.findAll();
		Assert.assertEquals(5, afecciones.size());
	}

	@Test
	public void findAll_NoExistenAfecciones_DevuelveListaVacia() {
		when(iAfeccionRepositorioCRUD.findAll()).thenReturn(new ArrayList<>());
		List<Afeccion> afecciones = this.afeccionRepositorioImplementacion.findAll();
		Assert.assertEquals(0, afecciones.size());
	}

	@Test
	public void findByNombreUnico_ExistenAfeccion_DevuelveAfeccion() {
		when(iAfeccionRepositorioCRUD.findByNombreAfeccionEquals("Gripe")).thenReturn(factoryAfecciones().get(1));
		Afeccion resultado = this.afeccionRepositorioImplementacion.findByNombreUnico("Gripe");
		Assert.assertEquals(2, resultado.getIdAfeccion());
	}

	@Test
	public void findByNombreUnico_NoExistenAfeccion_DevuelveNull() {
		when(iAfeccionRepositorioCRUD.findByNombreAfeccionEquals("asd")).thenReturn(null);
		Afeccion resultado = this.afeccionRepositorioImplementacion.findByNombreUnico("asd");
		Assert.assertNull(resultado);
	}

	@Test
	public void findByNombre_ExistenAfecciones_DevuelveListaAfecciones() {
		when(iAfeccionRepositorioCRUD.findByNombreAfeccionContains("Infección")).thenReturn(filtroAfecciones());
		List<Afeccion> afecciones = this.afeccionRepositorioImplementacion.findByNombre("Infección");
		Assert.assertEquals(2, afecciones.size());
	}

	@Test
	public void findByNombre_NoExistenAfecciones_DevuelveListaVacia() {
		when(iAfeccionRepositorioCRUD.findByNombreAfeccionContains("asd")).thenReturn(new ArrayList<>());
		List<Afeccion> afecciones = this.afeccionRepositorioImplementacion.findByNombre("asd");
		Assert.assertEquals(0, afecciones.size());
	}

	private List<AfeccionEntity> filtroAfecciones() {
		List<AfeccionEntity> lista = new ArrayList<AfeccionEntity>();
		lista.add(new AfeccionEntity(1,"Infección"));
		lista.add(new AfeccionEntity(2,"Infección Generalizada"));
		return lista;
	}

	private List<AfeccionEntity> factoryAfecciones() {
		List<AfeccionEntity> lista = new ArrayList<AfeccionEntity>();
		lista.add(new AfeccionEntity(1,"Migraña Cronica"));
		lista.add(new AfeccionEntity(2,"Gripe"));
		lista.add(new AfeccionEntity(3,"Apendicitis"));
		lista.add(new AfeccionEntity(4,"Infección"));
		lista.add(new AfeccionEntity(5,"Infección Generalizada"));
		return lista;
	}
}
