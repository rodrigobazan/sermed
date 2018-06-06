package InteractorTest;

import Interactor.ModificarMedicoUseCase;
import Mockito.MockitoExtension;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModificarMedicoUnitTest {

    @Mock
    IMedicoRepositorio repositorioMedico;

    @Test
    public void modificarMedico_DatosMedico_AssertTrue() {

        ModificarMedicoUseCase modificarMedicoUseCase = new ModificarMedicoUseCase(repositorioMedico);

        when(repositorioMedico.findById(1)).thenReturn(new Medico(1,"Vega", "Romina", 190106, "4813148"));
        when(repositorioMedico.findByMatricula(190106)).thenReturn(null);
        when(repositorioMedico.update(any(Medico.class))).thenReturn(true);


        Medico medicoAModificar = repositorioMedico.findById(1);
        Medico nuevosDatos = new Medico(1, "Vega", "Romina", 123, "123123");

        boolean resultado = modificarMedicoUseCase.modificarMedico(nuevosDatos);

        verify(repositorioMedico).update(medicoAModificar);
        Assertions.assertTrue(resultado);
        Assertions.assertEquals(nuevosDatos.mostrarMedico(),medicoAModificar.mostrarMedico());
        Assertions.assertEquals(1,medicoAModificar.getIdMedico());

    }

    @Test
    void modificarMedico__MatriculaExiste_NoActualiza(){

        ModificarMedicoUseCase modificarMedicoUseCase = new ModificarMedicoUseCase(repositorioMedico);

        when(repositorioMedico.findById(1)).thenReturn(new Medico(1,"Vega", "Romina", 190106, "4813148"));
        when(repositorioMedico.findByMatricula(192256)).thenReturn(new Medico(3,"Ruitti", "Javier", 192256, "679414"));

        Medico romiNueva = new Medico(1,"Vega", "Romina", 192256, "4813148");

        boolean resultado = modificarMedicoUseCase.modificarMedico(romiNueva);

        Assertions.assertFalse(resultado);
        verify(repositorioMedico, never()).update(romiNueva);

    }

}
