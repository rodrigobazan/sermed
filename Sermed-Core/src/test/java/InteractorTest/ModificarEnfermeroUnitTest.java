package InteractorTest;

import Interactor.ModificarEnfermeroUseCase;
import Mockito.MockitoExtension;
import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ModificarEnfermeroUnitTest {

    @Mock
    IEnfermeroRepositorio repositorioEnfermero;

    @Test
    public void modificarEnfermero_DatosEnfermero_AssertTrue() {

        ModificarEnfermeroUseCase modificarEnfermeroUseCase = new ModificarEnfermeroUseCase(repositorioEnfermero);

        when(repositorioEnfermero.findById(1)).thenReturn(new Enfermero(1,"Vega", "Romina", 190106, "4813148"));
        when(repositorioEnfermero.findByMatricula(123)).thenReturn(null);
        when(repositorioEnfermero.update(any(Enfermero.class))).thenReturn(true);


        Enfermero EnfermeroAModificar = repositorioEnfermero.findById(1);
        Enfermero nuevosDatos = new Enfermero(1, "Vega", "Romina", 123, "123123");

        boolean resultado = modificarEnfermeroUseCase.modificarEnfermero(nuevosDatos);

        verify(repositorioEnfermero).update(EnfermeroAModificar);
        Assertions.assertTrue(resultado);
        Assertions.assertEquals(nuevosDatos.mostrarEnfermero(),EnfermeroAModificar.mostrarEnfermero());
        Assertions.assertEquals(1,EnfermeroAModificar.getIdEnfermero());

    }

    @Test
    void modificarEnfermero__MatriculaExiste_NoActualiza(){

        ModificarEnfermeroUseCase modificarEnfermeroUseCase = new ModificarEnfermeroUseCase(repositorioEnfermero);

        when(repositorioEnfermero.findById(1)).thenReturn(new Enfermero(1,"Vega", "Romina", 190106, "4813148"));
        when(repositorioEnfermero.findByMatricula(192256)).thenReturn(new Enfermero(3,"Ruitti", "Javier", 192256, "679414"));

        Enfermero romiNueva = new Enfermero(1,"Vega", "Romina", 192256, "4813148");

        boolean resultado = modificarEnfermeroUseCase.modificarEnfermero(romiNueva);

        Assertions.assertFalse(resultado);
        verify(repositorioEnfermero, never()).update(romiNueva);

    }

}
