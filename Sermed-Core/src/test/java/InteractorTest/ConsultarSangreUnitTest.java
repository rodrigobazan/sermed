package InteractorTest;

import Interactor.ConsultarSangreUseCase;
import Mockito.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import Modelo.Sangre;
import Repositorio.ISangreRepositorio;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ConsultarSangreUnitTest {

    @Mock
    ISangreRepositorio iSangreRepositorio;
    @Spy
    List<Sangre> sangres = crearSangresArray();



    @Test
    public void consultarSangre_ExistenDatos_ColeccionConDatos(){
        when(iSangreRepositorio.findAll()).thenReturn(sangres);
        ConsultarSangreUseCase consultarSangreUseCase = new ConsultarSangreUseCase(iSangreRepositorio);
        List<Sangre> list = (List<Sangre>) consultarSangreUseCase.consultarSangre();
        assertThat(list, not(IsEmptyCollection.empty()));
        assertEquals(list.size(), 6);
    }

    @Test
    public void consultarSangre_NoExistenDatos_ColeccionVacia(){
        when(iSangreRepositorio.findAll()).thenReturn(new ArrayList<>());
        ConsultarSangreUseCase consultarSangreUseCase = new ConsultarSangreUseCase(iSangreRepositorio);
        List<Sangre> list = (List<Sangre>) consultarSangreUseCase.consultarSangre();
        assertThat(list, IsEmptyCollection.empty());
    }

    @Test
    public void consultarSangrePorGrupo_CriterioCadenaVacia_DevuelveTodos(){
        when(iSangreRepositorio.findByGrupo("")).thenReturn(sangres);
        ConsultarSangreUseCase consultarSangreUseCase = new ConsultarSangreUseCase(iSangreRepositorio);
        List<Sangre> sangreList = consultarSangreUseCase.consultarSangrePorGrupo("");
        assertThat(sangreList, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarSangrePorGrupo_CriterioConDatos_DevuelveAlgunos(){
        when(iSangreRepositorio.findByGrupo("a")).thenReturn(sangres);
        ConsultarSangreUseCase consultarSangreUseCase = new ConsultarSangreUseCase(iSangreRepositorio);
        List<Sangre> sangreList = consultarSangreUseCase.consultarSangrePorGrupo("a");
        assertThat(sangreList, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarSangrePorFactor_CriterioCadenaVacia_DevuelveTodos() {
        when(iSangreRepositorio.findByFactor("")).thenReturn(sangres);
        ConsultarSangreUseCase consultarSangreUseCase = new ConsultarSangreUseCase(iSangreRepositorio);
        List<Sangre> sangreList = consultarSangreUseCase.consultarSangrePorFactor("");
        assertThat(sangreList, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarSangrePorFactor_CriterioConDatos_DevuelveAlgunos(){
        when(iSangreRepositorio.findByFactor("negativo")).thenReturn(sangres);
        ConsultarSangreUseCase consultarSangreUseCase = new ConsultarSangreUseCase(iSangreRepositorio);
        List<Sangre> sangreList = consultarSangreUseCase.consultarSangrePorFactor("negativo");
        assertThat(sangreList, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarSangrePorFactorGrupo_CriterioCadenaVacia_DevuelveTodos(){
        when(iSangreRepositorio.findByGrupoFactor("","")).thenReturn(sangres);
        ConsultarSangreUseCase consultarSangreUseCase = new ConsultarSangreUseCase(iSangreRepositorio);
        List<Sangre> sangres = consultarSangreUseCase.consultarSangrePorGrupoFactor("","");
        assertThat(sangres, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarSangrePorFactorGrupo_CriteriaDatos_DevuelveAlgunos(){
        when(iSangreRepositorio.findByGrupoFactor("","")).thenReturn(sangres);
        ConsultarSangreUseCase consultarSangreUseCase = new ConsultarSangreUseCase(iSangreRepositorio);
        List<Sangre> sangres = consultarSangreUseCase.consultarSangrePorGrupoFactor("","");
        assertThat(sangres, not(IsEmptyCollection.empty()));
    }






    private List<Sangre> crearSangresArray() {
        try {
            List<Sangre> lista = new ArrayList<>();
            lista.add(new Sangre(1,"a","negativo"));
            lista.add(new Sangre(2,"a","positivo"));
            lista.add(new Sangre(3,"b","negativo"));
            lista.add(new Sangre(4,"b","positivo"));
            lista.add(new Sangre(5,"0","negativo"));
            lista.add(new Sangre(6,"0","positivo"));
            return  lista;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
