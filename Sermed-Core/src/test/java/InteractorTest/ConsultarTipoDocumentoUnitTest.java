package InteractorTest;

import Excepciones.TipoDocumentoNoExisteException;
import Interactor.ConsultarTipoDocumentoUseCase;
import Repositorio.ITipoDocumentoRepositorio;
import Mockito.MockitoExtension;
import Modelo.TipoDocumento;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarTipoDocumentoUnitTest {

    @Mock
    ITipoDocumentoRepositorio repositorioTipoDocumento;

    @Spy
    List<TipoDocumento> listaTipoDocumentos = crearTiposDocumento();

    @Test
    public void consultarTipoDocumento_ExistenDatos_DevulveCadenaConDatos() {
        when(repositorioTipoDocumento.findAll()).thenReturn(listaTipoDocumentos);
        ConsultarTipoDocumentoUseCase consultarTipoDocumentoUseCase = new ConsultarTipoDocumentoUseCase(repositorioTipoDocumento);
        List<TipoDocumento> tipoDocumentos = consultarTipoDocumentoUseCase.consultarTodosLosTiposDeDocumento();
        assertEquals(tipoDocumentos.size(), 4);
        assertThat(tipoDocumentos, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarTipoDocumento_NoExistenDatos_DevulveCadenaVacia() {
        when(repositorioTipoDocumento.findAll()).thenReturn(new ArrayList<>());
        ConsultarTipoDocumentoUseCase consultarTipoDocumentoUseCase = new ConsultarTipoDocumentoUseCase(repositorioTipoDocumento);
        List<TipoDocumento> tipoDocumentos = consultarTipoDocumentoUseCase.consultarTodosLosTiposDeDocumento();
        assertThat(tipoDocumentos, IsEmptyCollection.empty());
    }

    @Test
    public void consultarTiposDocumentosPorNombre_CriterioCadenaVacia_DevuelveTodos(){
        when(repositorioTipoDocumento.findByNombre("")).thenReturn(listaTipoDocumentos);
        ConsultarTipoDocumentoUseCase consultarTipoDocumentoUseCase = new ConsultarTipoDocumentoUseCase(repositorioTipoDocumento);
        List<TipoDocumento> tipoDocumentos = consultarTipoDocumentoUseCase.consultarTiposDocumentosPorNombre("");
        assertEquals(4, tipoDocumentos.size());
    }

    @Test
    public void consultarTiposDocumentosPorNombre_CriterioCadenaConDatos_DevuelveAlgunos(){
        when(repositorioTipoDocumento.findByNombre("Libreta")).thenReturn(crearFiltroArray());
        ConsultarTipoDocumentoUseCase consultarTipoDocumentoUseCase = new ConsultarTipoDocumentoUseCase(repositorioTipoDocumento);
        List<TipoDocumento> tipoDocumentos = consultarTipoDocumentoUseCase.consultarTiposDocumentosPorNombre("Libreta");
        assertEquals(2, tipoDocumentos.size());
    }

    @Test
    public void consultarTiposDocumentosPorNombre_ExisteTipoDocumento_DevuelveUnTipoDocumento() throws TipoDocumentoNoExisteException {
        when(repositorioTipoDocumento.findByNombreUnico("DNI")).thenReturn(new TipoDocumento(1, "DNI"));
        ConsultarTipoDocumentoUseCase consultarTipoDocumentoUseCase = new ConsultarTipoDocumentoUseCase(repositorioTipoDocumento);
        TipoDocumento tipoDocumentos = consultarTipoDocumentoUseCase.consultarTiposDocumentoUnicoPorNombre("DNI");
        assertEquals("DNI", tipoDocumentos.getNombre());
    }

    @Test
    public void consultarTiposDocumentosPorNombre_NoExisteTipoDocumento_TipoDocumentoNoExisteException(){
        when(repositorioTipoDocumento.findByNombreUnico("Cedula")).thenReturn(null);
        ConsultarTipoDocumentoUseCase consultarTipoDocumentoUseCase = new ConsultarTipoDocumentoUseCase(repositorioTipoDocumento);
        assertThrows(TipoDocumentoNoExisteException.class, () -> consultarTipoDocumentoUseCase.consultarTiposDocumentoUnicoPorNombre("cedula"));
    }

    private Collection<TipoDocumento> crearFiltroArray() {
        try {
            List<TipoDocumento> lista = new ArrayList<>();
            lista.add(new TipoDocumento(2,"Libreta Civica"));
            lista.add(new TipoDocumento(4,"Libreta de Enrolamiento"));
            return lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<TipoDocumento> crearTiposDocumento() {
        try {
            List<TipoDocumento> lista = new ArrayList<>();
            lista.add(new TipoDocumento(1,"DNI"));
            lista.add(new TipoDocumento(2,"Libreta Civica"));
            lista.add(new TipoDocumento(3,"Pasaporte"));
            lista.add(new TipoDocumento(4,"Libreta de Enrolamiento"));
            return lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
