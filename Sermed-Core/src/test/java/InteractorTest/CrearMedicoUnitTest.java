package InteractorTest;

import Interactor.CrearMedicoUseCase;
import Mockito.MockitoExtension;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearMedicoUnitTest {

    @Mock
    IMedicoRepositorio repositorioMedico;

    @Test
    public void crearMedico_MedicoNoExiste_GuardarMedico() {
        Medico medico = new Medico(1,"torres","geerman",12015,"as212321");
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        when(repositorioMedico.persist(any(Medico.class))).thenReturn(true);

        boolean resultado = crearMedicoUseCase.crearMedico(medico);

        Assertions.assertEquals(true, resultado);
    }

    @Test
    public void crearMedico_MedicoSiExiste_NoGuardaMedico() {
        when(repositorioMedico.findById(1)).thenReturn(new Medico(1,"torres","geerman",12015,"as212321"));
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        Medico medico = new Medico(1, "vega", "romina", 190202, "674678");

        boolean resultado = crearMedicoUseCase.crearMedico(medico);

        Assertions.assertEquals(false, resultado);
        verify(repositorioMedico, never()).persist(any(Medico.class));
    }

    @Test
    public void crearMedico_MatriculaSiExiste_NoGuardaMedico() {
        //arrange

        when(repositorioMedico.findByMatricula(190202)).thenReturn(new Medico(1, "vega", "romina", 190202, "674678"));
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        Medico medico = new Medico(45, "Torres", "German", 190202, "674678");

        //Act
        boolean resultado = crearMedicoUseCase.crearMedico(medico);

        //Assert
        Assertions.assertEquals(false, resultado);
        verify(repositorioMedico,never()).persist(any(Medico.class));
    }

    @Test
    void validarMedicoExiste_MedicoExisteID_ReturnTrue() {

        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        when(repositorioMedico.findById(1)).thenReturn(new Medico(1, "vega", "romina", 190202, "674678"));
        Medico medico = new Medico(1, "Torres", "German", 190202, "674678");

        boolean respuestaValidar = crearMedicoUseCase.validarMedicoExiste(medico);

        Assertions.assertEquals(true, respuestaValidar);
    }

    @Test
    void validarMedicoExiste_MedicoExisteMatricula_ReturnTrue() {
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        when(repositorioMedico.findByMatricula(190202)).thenReturn(new Medico(1, "vega", "romina", 190202, "674678"));
        Medico medico = new Medico(1, "Torres", "German", 190202, "674678");

        boolean respuestaValidar = crearMedicoUseCase.validarMedicoExiste(medico);

        Assertions.assertEquals(true, respuestaValidar);
    }

    @Test
    void validarMedicoExiste_MedicoNoExiste_ReturnFalse() {
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        when(repositorioMedico.findByMatricula(190202)).thenReturn(null);
        when(repositorioMedico.findById(1)).thenReturn(null);
        Medico medico = new Medico(1, "Torres", "German", 190202, "674678");

        boolean respuestaValidar = crearMedicoUseCase.validarMedicoExiste(medico);

        Assertions.assertEquals(false, respuestaValidar);
    }


}




