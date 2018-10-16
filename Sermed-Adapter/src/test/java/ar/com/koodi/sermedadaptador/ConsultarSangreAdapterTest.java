package ar.com.koodi.sermedadaptador;

import Adaptadores.ConsultarSangreAdapter;
import Inputs.ConsultarSangreInput;
import Mockito.MockitoExtension;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

import Modelo.Sangre;
import ModeloApi.SangreDTO;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ConsultarSangreAdapterTest {

    @Mock
    private ConsultarSangreInput consultarSangreInput;

    @Spy
    List<Sangre> sangresLista = crearSangresArray();


    @Test
    public void consultarSangre_ExistenDatos_RetornarColeccion(){
        ConsultarSangreAdapter consultarSangreAdapter = new ConsultarSangreAdapter(consultarSangreInput);
        when(consultarSangreInput.consultarSangre()).thenReturn(crearSangresArray());
        List<SangreDTO> sangres = consultarSangreAdapter.consultarSangre();
        assertThat(sangres, not(IsEmptyCollection.empty()));
        Assertions.assertEquals(sangres.size(), sangresLista.size());
    }

    @Test
    public void consultarSangre_NoExistenDatos_CollecionVacia(){
        ConsultarSangreAdapter consultarSangreAdapter = new ConsultarSangreAdapter(consultarSangreInput);
        when(consultarSangreInput.consultarSangre()).thenReturn(new ArrayList<>());
        List<SangreDTO> sangres = consultarSangreAdapter.consultarSangre();
        assertThat(sangres, IsEmptyCollection.empty());
    }

    @Test
    public void consultarSangrePorGrupo_CriteriaCadenaVacia_DevuelveTodos() {
        when(consultarSangreInput.consultarSangrePorGrupo("")).thenReturn(sangresLista);
        ConsultarSangreAdapter consultarSangreAdapter = new ConsultarSangreAdapter(consultarSangreInput);
        List<SangreDTO> sangreDTOS = consultarSangreAdapter.consultarSangrePorGrupo("");
        assertThat(sangreDTOS, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarSangrePorFactor_CriterioCadenaVacia_DevuelveTodos() {
        when(consultarSangreInput.consultarSangrePorFactor("")).thenReturn(sangresLista);
        ConsultarSangreAdapter consultarSangreAdapter = new ConsultarSangreAdapter(consultarSangreInput);
        List<SangreDTO> sangreDTOS = consultarSangreAdapter.consultarSangrePorFactor("");
        assertThat(sangreDTOS,not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarSangrePorFactor_CriterioConDatos_DevuelveAlgunos() {
        when(consultarSangreInput.consultarSangrePorFactor("negativo")).thenReturn(sangresLista);
        ConsultarSangreAdapter consultarSangreAdapter = new ConsultarSangreAdapter(consultarSangreInput);
        List<SangreDTO> sangreDTOS = consultarSangreAdapter.consultarSangrePorFactor("negativo");
        assertThat(sangreDTOS, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarSangrePorFactorGrupo_CriterioConDatos_DevuelveSangre() {
        when(consultarSangreInput.consultarSangrePorGrupoFactor("A","RH+")).thenReturn(new Sangre(1,"A","RH+"));
        ConsultarSangreAdapter consultarSangreAdapter = new ConsultarSangreAdapter(consultarSangreInput);
        SangreDTO sangreDTO = consultarSangreAdapter.consultarSangrePorGrupoFactor("A","RH+");
        Assertions.assertEquals("A", sangreDTO.grupo);
        Assertions.assertEquals("RH+",sangreDTO.factor);
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
