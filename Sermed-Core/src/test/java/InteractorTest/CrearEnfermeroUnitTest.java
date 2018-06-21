package InteractorTest;

import Interactor.CrearEnfermeroUseCase;
import Mockito.MockitoExtension;

import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearEnfermeroUnitTest {

    @Mock
    IEnfermeroRepositorio repositorioEnfermero;

    @Test
    public void crearEnfermero_EnfermeroNoExiste_GuardarEnfermero() {
        Enfermero Enfermero = new Enfermero(1,"torres","geerman",12015,"as212321");
        CrearEnfermeroUseCase crearEnfermeroUseCase = new CrearEnfermeroUseCase(repositorioEnfermero);
        when(repositorioEnfermero.findByMatricula(12015)).thenReturn(null);
        when(repositorioEnfermero.findById(1)).thenReturn(null);
        when(repositorioEnfermero.persist(any(Enfermero.class))).thenReturn(true);

        boolean resultado = crearEnfermeroUseCase.crearEnfermero(Enfermero);

        Assertions.assertEquals(true, resultado);
    }

    @Test
    public void crearEnfermero_EnfermeroSiExiste_NoGuardaEnfermero() {
        when(repositorioEnfermero.findById(1)).thenReturn(new Enfermero(1,"torres","geerman",12015,"as212321"));
        CrearEnfermeroUseCase crearEnfermeroUseCase = new CrearEnfermeroUseCase(repositorioEnfermero);
        Enfermero Enfermero = new Enfermero(1, "vega", "romina", 190202, "674678");

        boolean resultado = crearEnfermeroUseCase.crearEnfermero(Enfermero);

        Assertions.assertEquals(false, resultado);
        verify(repositorioEnfermero, never()).persist(any(Enfermero.class));
    }

    @Test
    public void crearEnfermero_MatriculaSiExiste_NoGuardaEnfermero() {
        //arrange

        when(repositorioEnfermero.findByMatricula(190202)).thenReturn(new Enfermero(1, "vega", "romina", 190202, "674678"));
        CrearEnfermeroUseCase crearEnfermeroUseCase = new CrearEnfermeroUseCase(repositorioEnfermero);
        Enfermero Enfermero = new Enfermero(45, "Torres", "German", 190202, "674678");

        //Act
        boolean resultado = crearEnfermeroUseCase.crearEnfermero(Enfermero);

        //Assert
        Assertions.assertEquals(false, resultado);
        verify(repositorioEnfermero,never()).persist(any(Enfermero.class));
    }

    @Test
    void validarEnfermeroExiste_EnfermeroExisteID_ReturnTrue() {

        CrearEnfermeroUseCase crearEnfermeroUseCase = new CrearEnfermeroUseCase(repositorioEnfermero);
        when(repositorioEnfermero.findById(1)).thenReturn(new Enfermero(1, "vega", "romina", 190202, "674678"));
        Enfermero Enfermero = new Enfermero(1, "Torres", "German", 190202, "674678");

        boolean respuestaValidar = crearEnfermeroUseCase.validarEnfermeroExiste(Enfermero);

        Assertions.assertEquals(true, respuestaValidar);
    }

    @Test
    void validarEnfermeroExiste_EnfermeroExisteMatricula_ReturnTrue() {
        CrearEnfermeroUseCase crearEnfermeroUseCase = new CrearEnfermeroUseCase(repositorioEnfermero);
        when(repositorioEnfermero.findByMatricula(190202)).thenReturn(new Enfermero(1, "vega", "romina", 190202, "674678"));
        Enfermero Enfermero = new Enfermero(1, "Torres", "German", 190202, "674678");

        boolean respuestaValidar = crearEnfermeroUseCase.validarEnfermeroExiste(Enfermero);

        Assertions.assertEquals(true, respuestaValidar);
    }

    @Test
    void validarEnfermeroExiste_EnfermeroNoExiste_ReturnFalse() {
        CrearEnfermeroUseCase crearEnfermeroUseCase = new CrearEnfermeroUseCase(repositorioEnfermero);
        when(repositorioEnfermero.findByMatricula(190202)).thenReturn(null);
        when(repositorioEnfermero.findById(1)).thenReturn(null);
        Enfermero Enfermero = new Enfermero(1, "Torres", "German", 190202, "674678");

        boolean respuestaValidar = crearEnfermeroUseCase.validarEnfermeroExiste(Enfermero);

        Assertions.assertEquals(false, respuestaValidar);
    }


}




