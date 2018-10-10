package ar.com.koodi.sermedadaptador;

import Adaptadores.ConsultarEnfermeroAdapter;
import Excepciones.EnfermeroNoExisteException;
import Inputs.ConsultarEnfermeroInput;
import Mockito.MockitoExtension;
import Modelo.Enfermero;
import ModeloApi.EnfermeroDTO;
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
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarEnfermeroAdapterTest {

    @Mock
    ConsultarEnfermeroInput consultarEnfermeroInput;

    @Spy
    List<Enfermero> list = crearEnfermerosArray();

    @Test
    public void consultarEnfermeros_ExistenDatos_DevuelveColeccionConDatos() {
        when(consultarEnfermeroInput.consultarEnfermeros()).thenReturn(crearEnfermerosArray());
        ConsultarEnfermeroAdapter consultarEnfermeroAdapter = new ConsultarEnfermeroAdapter(consultarEnfermeroInput);
        List<EnfermeroDTO> enfermeroDTOList = consultarEnfermeroAdapter.consultarEnfermeros();
        assertThat(enfermeroDTOList, not(IsEmptyCollection.empty()));
        Assertions.assertEquals(enfermeroDTOList.size(), list.size());
    }

    @Test
    public void consultarEnfermeros_NoExistenDatos_DevuelveColeccionVacia() {
        when(consultarEnfermeroInput.consultarEnfermeros()).thenReturn(new ArrayList<>());
        ConsultarEnfermeroAdapter consultarEnfermeroAdapter = new ConsultarEnfermeroAdapter(consultarEnfermeroInput);
        List<EnfermeroDTO> enfermeroDTOList = consultarEnfermeroAdapter.consultarEnfermeros();
        assertThat(enfermeroDTOList, IsEmptyCollection.empty());
    }

    @Test
    public void consultarEnfermerosPorApellido_CriterioCadenaVacia_DevolverTodos() {
        when(consultarEnfermeroInput.consultarEnfermerosPorApellido("")).thenReturn(list);
        ConsultarEnfermeroAdapter consultarEnfermeroAdapter = new ConsultarEnfermeroAdapter(consultarEnfermeroInput);
        List<EnfermeroDTO> Enfermeros = consultarEnfermeroAdapter.consultarEnfermerosPorApellido("");
        Assertions.assertEquals(list.size(), Enfermeros.size());
        assertThat(Enfermeros, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarEnfermerosPorApellido_CriterioCadenaConDatos_DevolverAlgunos() {
        when(consultarEnfermeroInput.consultarEnfermerosPorApellido("torr")).thenReturn(crearEnfermeroFiltroApellidoArray());
        ConsultarEnfermeroAdapter consultarEnfermeroAdapter = new ConsultarEnfermeroAdapter(consultarEnfermeroInput);
        List<EnfermeroDTO> Enfermeros = consultarEnfermeroAdapter.consultarEnfermerosPorApellido("torr");
        assertThat(Enfermeros, hasSize(2));
    }

    @Test
    public void consultarEnfermeroMatricula_MatriculaExiste_RetornaEnfermero() throws EnfermeroNoExisteException {
        when(consultarEnfermeroInput.consultarEnfermeroPorMatricula(190252)).thenReturn(new Enfermero(1, "Torres", "German", 190200, "12345678"));
        ConsultarEnfermeroAdapter consultarEnfermeroAdapter = new ConsultarEnfermeroAdapter(consultarEnfermeroInput);
        EnfermeroDTO EnfermeroBuscado = consultarEnfermeroAdapter.consultarEnfermeroPorMatricula(190252);
        Assertions.assertEquals(1, EnfermeroBuscado.idEnfermero.intValue());
        Assertions.assertEquals("Torres", EnfermeroBuscado.apellido);
    }

    @Test
    void consultarEnfermeroMatricula_MatriculaNoExiste_EnfermeroNoExisteException() throws EnfermeroNoExisteException {
        when(consultarEnfermeroInput.consultarEnfermeroPorMatricula(190123)).thenThrow(EnfermeroNoExisteException.class);
        ConsultarEnfermeroAdapter consultarEnfermeroAdapter=new ConsultarEnfermeroAdapter(consultarEnfermeroInput);
        Assertions.assertThrows(EnfermeroNoExisteException.class, () -> consultarEnfermeroAdapter.consultarEnfermeroPorMatricula(190123));
    }


    private List<Enfermero> crearEnfermeroFiltroApellidoArray() {
        List<Enfermero> losEnfermeros = new ArrayList<Enfermero>();
        losEnfermeros.add(new Enfermero(2, "Torres", "German", 190252, "674678"));
        losEnfermeros.add(new Enfermero(4, "Torrielli", "Edgar", 123465, "674678"));
        return losEnfermeros;
    }

    private List<Enfermero> crearEnfermerosArray() {
        List<Enfermero> enfermeros = new ArrayList<>();
        enfermeros.add(new Enfermero(1, "Torres", "German", 190200, "12345678"));
        enfermeros.add(new Enfermero(2, "Vega", "Romina", 190201, "23456789"));
        enfermeros.add(new Enfermero(3, "Paez Yañez", "Martil", 190202, "87654321"));
        enfermeros.add(new Enfermero(4, "Ruitti", "Javier", 190203, "98765432"));
        return enfermeros;
    }


}
