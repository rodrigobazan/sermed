package ar.com.koodi.sermedadaptador;

import Adaptadores.ConsultarAfeccionAdapter;
import Excepciones.AfeccionNoExisteException;
import Inputs.ConsultarAfeccionInput;
import Mockito.MockitoExtension;
import Modelo.Afeccion;
import ModeloApi.AfeccionDTO;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarAfeccionAdapterTest {

    @Mock
    ConsultarAfeccionInput consultarAfeccionInput;

    @Spy
    List<Afeccion> list = crearAfeccionesArray();

    @Test
    public void consultarAfecciones_ExistenDatos_DevuelveListaConDatos(){
        ConsultarAfeccionAdapter consultarAfeccionAdapter = new ConsultarAfeccionAdapter(consultarAfeccionInput);
        when(consultarAfeccionInput.consultarAfecciones()).thenReturn(list);
        List<AfeccionDTO> afecciones = consultarAfeccionAdapter.consultarAfecciones();
        assertThat(afecciones, not(IsEmptyCollection.empty()));
        Assertions.assertEquals(5, afecciones.size());
    }

    @Test
    public void consultarAfecciones_NoExistenDatos_DevuelveListaVacia() {
        ConsultarAfeccionAdapter consultarAfeccionAdapter= new ConsultarAfeccionAdapter(consultarAfeccionInput);
        when(consultarAfeccionInput.consultarAfecciones()).thenReturn(new ArrayList<>());
        List<AfeccionDTO> afecciones = consultarAfeccionAdapter.consultarAfecciones();
        assertThat(afecciones, IsEmptyCollection.empty());
        Assertions.assertEquals(0, afecciones.size());
    }

    @Test
    public void consultarAfeccionesPorNombre_ExistenDatos_DevuelveListaDatos() {
        ConsultarAfeccionAdapter consultarAfeccionAdapter= new ConsultarAfeccionAdapter(consultarAfeccionInput);
        when(consultarAfeccionInput.consultarAfeccionesPorNombre("Infeccion")).thenReturn(filtroAfecciones());
        List<AfeccionDTO> afecciones = consultarAfeccionAdapter.consultarAfeccionesPorNombre("Infeccion");
        assertThat(afecciones, not(IsEmptyCollection.empty()));
        Assertions.assertEquals(2, afecciones.size());
    }

    @Test
    public void consultarAfeccionesPorNombre_CadenaVacia_DevuelveTodasAfecciones() {
        ConsultarAfeccionAdapter consultarAfeccionAdapter= new ConsultarAfeccionAdapter(consultarAfeccionInput);
        when(consultarAfeccionInput.consultarAfeccionesPorNombre("")).thenReturn(list);
        List<AfeccionDTO> afecciones = consultarAfeccionAdapter.consultarAfeccionesPorNombre("");
        assertThat(afecciones, not(IsEmptyCollection.empty()));
        Assertions.assertEquals(5, afecciones.size());
    }

    @Test
    public void consultarAfeccionPorNombre_AfeccionExiste_DevuelveAfeccion() throws AfeccionNoExisteException{
        ConsultarAfeccionAdapter consultarAfeccionAdapter= new ConsultarAfeccionAdapter(consultarAfeccionInput);
        when(consultarAfeccionInput.consultarAfeccionPorNombre("Infeccion")).thenReturn(new Afeccion(1, "Infeccion"));
        AfeccionDTO resultado = consultarAfeccionAdapter.consultarAfeccionPorNombre("Infeccion");
        Assertions.assertEquals(1, resultado.idAfeccion.intValue());

    }

    @Test
    public void consultarAfeccionPorNombre_AfeccionNoExiste_AfeccionNoExisteException() throws AfeccionNoExisteException {
        when(consultarAfeccionInput.consultarAfeccionPorNombre("asd")).thenThrow(AfeccionNoExisteException.class);
        ConsultarAfeccionAdapter consultarAfeccionAdapter= new ConsultarAfeccionAdapter(consultarAfeccionInput);
        Assertions.assertThrows(AfeccionNoExisteException.class, () -> consultarAfeccionAdapter.consultarAfeccionPorNombre("asd"));

    }


    private List<Afeccion> filtroAfecciones(){
        List<Afeccion> lista = new ArrayList<Afeccion>();
        lista.add(new Afeccion(1, "Infeccion"));
        lista.add(new Afeccion(2, "Infeccion Generalizada"));
        return lista;
    }

    private List<Afeccion> crearAfeccionesArray() {
        List<Afeccion> lista = new ArrayList<Afeccion>();
        lista.add(new Afeccion(1, "Migraña Cronica"));
        lista.add(new Afeccion(2, "Gripe"));
        lista.add(new Afeccion(3, "Apendicitis"));
        lista.add(new Afeccion(4, "Infeccion"));
        lista.add(new Afeccion(5, "Infeccion Generalizada"));
        return lista;
    }

}
