package ar.com.koodi.sermedadaptador;

import Adaptadores.ConsultarMedicoAdapter;
import Excepciones.MedicoNoExisteException;
import Inputs.ConsultarMedicoInput;
import Mockito.MockitoExtension;
import Modelo.Medico;
import ModeloApi.MedicoDTO;
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
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarMedicoAdapterTest {

    @Mock
    ConsultarMedicoInput consultarMedicoInput;

    @Spy
    List<Medico> list = crearMedicosArray();

    @Test
    public void consultarMedicos_ExistenDatos_DevuelveColeccionConDatos(){
        when(consultarMedicoInput.consultarMedicos()).thenReturn(list);
        ConsultarMedicoAdapter consultarMedicoAdapter = new ConsultarMedicoAdapter(consultarMedicoInput);
        List<MedicoDTO> medicoDTOList = consultarMedicoAdapter.consultarMedicos();
        assertThat(medicoDTOList, not(IsEmptyCollection.empty()));
        assertEquals(medicoDTOList.size(), list.size());
    }

    @Test
    public void consultarMedicos_NoExistenDatos_ColeccionVacia() {
        when(consultarMedicoInput.consultarMedicos()).thenReturn(new ArrayList<>());
        ConsultarMedicoAdapter consultarMedicoAdapter = new ConsultarMedicoAdapter(consultarMedicoInput);
        List<MedicoDTO> medicoDTOList = consultarMedicoAdapter.consultarMedicos();
        assertThat(medicoDTOList, IsEmptyCollection.empty());
    }

    @Test
    public void consultarMedicosPorApellido_CriterioCadenaVacia_DevolverTodos(){
        when(consultarMedicoInput.consultarMedicosPorApellido("")).thenReturn(list);
        ConsultarMedicoAdapter consultarMedicoAdapter = new ConsultarMedicoAdapter(consultarMedicoInput);
        List<MedicoDTO> medicosList = consultarMedicoAdapter.consultarMedicosPorApellido("");
        assertEquals(list.size(), medicosList.size());
        assertThat(medicosList, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarMedicosPorApellido_CriterioCadenaConDatos_DevolverAlgunos(){
        when(consultarMedicoInput.consultarMedicosPorApellido("torr")).thenReturn(crearMedicoFiltroApellidoArray());
        ConsultarMedicoAdapter consultarMedicoAdapter = new ConsultarMedicoAdapter(consultarMedicoInput);
        List<MedicoDTO> medicos = consultarMedicoAdapter.consultarMedicosPorApellido("torr");
        assertThat(medicos, hasSize(2));
    }

    @Test
    public void consultarMedicoMatricula_MatriculaExiste_RetornaMedico() throws MedicoNoExisteException {
        when(consultarMedicoInput.consultarMedicoPorMatricula(190252)).thenReturn(new Medico(2,"Torres", "German", 190252, "674678"));
        ConsultarMedicoAdapter consultarMedicoAdapter = new ConsultarMedicoAdapter(consultarMedicoInput);
        MedicoDTO medicoDTO = consultarMedicoAdapter.consultarMedicoPorMatricula(190252);
        Assertions.assertEquals(2, medicoDTO.idMedico.intValue());
    }

    @Test
    public void consultarMedicoMatricula_MatriculaNoExiste_MedicoNoExisteException() throws MedicoNoExisteException {
        when(consultarMedicoInput.consultarMedicoPorMatricula(190123)).thenThrow(MedicoNoExisteException.class);
        ConsultarMedicoAdapter consultarMedicoAdapter = new ConsultarMedicoAdapter(consultarMedicoInput);
        Assertions.assertThrows(MedicoNoExisteException.class, () -> consultarMedicoAdapter.consultarMedicoPorMatricula(190123));

    }

    private List<Medico> crearMedicosArray() {
        List<Medico> losMedicos=new ArrayList<Medico>();
        losMedicos.add(new Medico(1,"Vega", "Romina", 1044, "4813148"));
        losMedicos.add(new Medico(2,"Torres", "German", 190252, "674678"));
        losMedicos.add(new Medico(4,"Torreson", "Edgar", 123465, "674678"));
        losMedicos.add(new Medico(3,"Ruitti", "Javier", 192256, "679414"));
        return losMedicos;
    }

    private List<Medico> crearMedicoFiltroApellidoArray(){
        List<Medico> losMedicos=new ArrayList<Medico>();
        losMedicos.add(new Medico(2,"Torres", "German", 190252, "674678"));
        losMedicos.add(new Medico(4,"Torreson", "Edgar", 123465, "674678"));
        return losMedicos;
    }

}
