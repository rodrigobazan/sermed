package InteractorTest;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import Excepciones.AfeccionNoExisteException;
import Excepciones.EnfermeroNoExisteException;
import Interactor.ConsultarAfeccionUseCase;
import Mockito.MockitoExtension;
import Modelo.Afeccion;
import Repositorio.IAfeccionRepositorio;

@ExtendWith(MockitoExtension.class)
public class ConsultarAfeccionUnitTest {
	
	@Mock
	IAfeccionRepositorio repositorioAfeccion;
	
	@Spy
    List<Afeccion> list = crearAfeccionesArray();

	@Test
	public void consultarAfecciones_ExistenDatos_DevuelveListaDatos() {
		ConsultarAfeccionUseCase consultarAfeccionUseCase= new ConsultarAfeccionUseCase(repositorioAfeccion);
		when(repositorioAfeccion.findAll()).thenReturn(list);
		List<Afeccion> afecciones = consultarAfeccionUseCase.consultarAfecciones();
		assertThat(afecciones, not(IsEmptyCollection.empty()));
		Assertions.assertEquals(5, afecciones.size());				
	}

	
	@Test
	public void consultarAfecciones_NoExistenDatos_DevuelveListaVacia() {
		ConsultarAfeccionUseCase consultarAfeccionUseCase= new ConsultarAfeccionUseCase(repositorioAfeccion);
		when(repositorioAfeccion.findAll()).thenReturn(new ArrayList<>());
		List<Afeccion> afecciones = consultarAfeccionUseCase.consultarAfecciones();
		assertThat(afecciones, IsEmptyCollection.empty());
		Assertions.assertEquals(0, afecciones.size());				
	}
	
	@Test
	public void consultarAfeccionesPorNombre_ExistenDatos_DevuelveListaDatos() {
		ConsultarAfeccionUseCase consultarAfeccionUseCase= new ConsultarAfeccionUseCase(repositorioAfeccion);
		when(repositorioAfeccion.findByNombre("Infección")).thenReturn(filtroAfecciones());
		List<Afeccion> afecciones = consultarAfeccionUseCase.consultarAfeccionesPorNombre("Infección");
		assertThat(afecciones, not(IsEmptyCollection.empty()));
		Assertions.assertEquals(2, afecciones.size());				
	}
	
	@Test
	public void consultarAfeccionesPorNombre_CadenaVacia_DevuelveTodasAfecciones() {
		ConsultarAfeccionUseCase consultarAfeccionUseCase= new ConsultarAfeccionUseCase(repositorioAfeccion);
		when(repositorioAfeccion.findByNombre("")).thenReturn(list);
		List<Afeccion> afecciones = consultarAfeccionUseCase.consultarAfeccionesPorNombre("");
		assertThat(afecciones, not(IsEmptyCollection.empty()));
		Assertions.assertEquals(5, afecciones.size());				
	}
	
	@Test
	public void consultarAfeccionPorNombre_AfeccionExiste_DevuelveAfeccion() throws AfeccionNoExisteException {
		ConsultarAfeccionUseCase consultarAfeccionUseCase= new ConsultarAfeccionUseCase(repositorioAfeccion);		
		when(repositorioAfeccion.findByNombreUnico("Infección")).thenReturn(new Afeccion(1, "Infección"));
		Afeccion resultado = consultarAfeccionUseCase.consultarAfeccionPorNombre("Infección");
		Assertions.assertEquals(1, resultado.getIdAfeccion());
		
	}
	
	@Test
	public void consultarAfeccionPorNombre_AfeccionNoExiste_AfeccionNoExisteException() {		
		when(repositorioAfeccion.findByNombreUnico("asd")).thenReturn(null);
		ConsultarAfeccionUseCase consultarAfeccionUseCase= new ConsultarAfeccionUseCase(repositorioAfeccion);		
		Assertions.assertThrows(AfeccionNoExisteException.class, () -> consultarAfeccionUseCase.consultarAfeccionPorNombre("asd"));
		
	}


	private List<Afeccion> filtroAfecciones(){
		List<Afeccion> lista = new ArrayList<Afeccion>();		
		lista.add(new Afeccion(1, "Infección"));
		lista.add(new Afeccion(2, "Infección Generalizada"));
		return lista;
	}
	
	private List<Afeccion> crearAfeccionesArray() {
		List<Afeccion> lista = new ArrayList<Afeccion>();
		lista.add(new Afeccion(1, "Migraña Cronica"));
		lista.add(new Afeccion(2, "Gripe"));
		lista.add(new Afeccion(3, "Apendicitis"));
		lista.add(new Afeccion(4, "Infección"));
		lista.add(new Afeccion(5, "Infección Generalizada"));
		return lista;
	}
	
	
}
