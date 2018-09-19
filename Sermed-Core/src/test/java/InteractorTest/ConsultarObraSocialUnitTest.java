package InteractorTest;

import Excepciones.ObraSocialNoExisteException;
import Interactor.ConsultarObrasSocialesUseCase;
import Mockito.MockitoExtension;
import Modelo.ObraSocial;
import Repositorio.IObraSocialRepositorio;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarObraSocialUnitTest {

    @Mock
    IObraSocialRepositorio repositorioObraSocial;

    @Spy
    List<ObraSocial> listaObrasSociales = crearObrasSocialesArray();

    @Test
    public void consultarObrasSociales_ExistenDatos_ColeccionConDatos(){
        when(repositorioObraSocial.findAll()).thenReturn(listaObrasSociales);
        ConsultarObrasSocialesUseCase consultarObrasSocialesUseCase = new ConsultarObrasSocialesUseCase(repositorioObraSocial);
        List<ObraSocial> obrasSociales = (List<ObraSocial>) consultarObrasSocialesUseCase.consultarObrasSociales();
        assertThat(obrasSociales, not(IsEmptyCollection.empty()));
        assertEquals(obrasSociales.size(), 5);
    }

    @Test
    public void consultarObrasSociales_NoExistenDatos_ColeccionVacia(){
        when(repositorioObraSocial.findAll()).thenReturn(new ArrayList<>());
        ConsultarObrasSocialesUseCase consultarObrasSocialesUseCase = new ConsultarObrasSocialesUseCase(repositorioObraSocial);
        List<ObraSocial> obrasSociales = (List<ObraSocial>) consultarObrasSocialesUseCase.consultarObrasSociales();
        assertThat(obrasSociales, IsEmptyCollection.empty());
    }

    @Test
    public void consultarObrasSocialesPorNombre_CriterioCadenaVacia_DevuelveTodos(){
        when(repositorioObraSocial.findByNombre("")).thenReturn(listaObrasSociales);
        ConsultarObrasSocialesUseCase consultarObrasSocialesUseCase = new ConsultarObrasSocialesUseCase(repositorioObraSocial);
        List<ObraSocial> obrasSociales = consultarObrasSocialesUseCase.consultarObrasSocialesPorNombre("");
        assertThat(obrasSociales, not(IsEmptyCollection.empty()));
        assertEquals(obrasSociales.size(), 5);
    }

    @Test
    public void consultarObrasSocialesPorNombre_CriterioCadenaConDatos_DevuelveAlgunos(){
        when(repositorioObraSocial.findByNombre("OS")).thenReturn(crearFiltroObrasSociales());
        ConsultarObrasSocialesUseCase consultarObrasSocialesUseCase = new ConsultarObrasSocialesUseCase(repositorioObraSocial);
        List<ObraSocial> obrasSociales = consultarObrasSocialesUseCase.consultarObrasSocialesPorNombre("OS");
        assertThat(obrasSociales, not(IsEmptyCollection.empty()));
        assertEquals(obrasSociales.size(), 3);
    }

    @Test
    public void consultarObraSocialPorNombre_CriterioCadenaConDatos_DevuelveObraSocial() throws ObraSocialNoExisteException {
        when(repositorioObraSocial.findByNombreUnico("OSFATUN")).thenReturn(new ObraSocial(1,"OSFATUN"));
        ConsultarObrasSocialesUseCase consultarObrasSocialesUseCase = new ConsultarObrasSocialesUseCase(repositorioObraSocial);
        ObraSocial resultado=consultarObrasSocialesUseCase.consultarObraSocialPorNombre("OSFATUN");
        Assertions.assertEquals(1,resultado.getIdObraSocial().intValue());

    }

    @Test
    public void consultarObraSocialPorNombre_NoExisteObraSocial_ObraSocialNoExisteException(){
        when(repositorioObraSocial.findByNombreUnico("OSFATUN")).thenReturn(null);
        ConsultarObrasSocialesUseCase consultarObrasSocialesUseCase = new ConsultarObrasSocialesUseCase(repositorioObraSocial);
        Assertions.assertThrows(ObraSocialNoExisteException.class,()->consultarObrasSocialesUseCase.consultarObraSocialPorNombre("OSFATUN"));

    }

    private List<ObraSocial> crearFiltroObrasSociales() {
        try {
            List<ObraSocial> lista = new ArrayList<>();
            lista.add(new ObraSocial(1, "OSFATUN"));
            lista.add(new ObraSocial(2, "OSDE"));
            lista.add(new ObraSocial(3, "APOS"));
            return lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<ObraSocial> crearObrasSocialesArray() {
        try {
            List<ObraSocial> lista = new ArrayList<>();
            lista.add(new ObraSocial(1, "OSFATUN"));
            lista.add(new ObraSocial(2, "OSDE"));
            lista.add(new ObraSocial(3, "APOS"));
            lista.add(new ObraSocial(4, "PAMI"));
            lista.add(new ObraSocial(5, "SANCOR SALUD"));
            return lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }




}
