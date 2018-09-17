package InteractorTest;

import Excepciones.ObraSocialExisteException;
import Interactor.CrearObraSocialUseCase;
import Mockito.MockitoExtension;
import Modelo.ObraSocial;
import Repositorio.IObraSocialRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearObraSocialUnitTest {

    @Mock
    IObraSocialRepositorio repositorioObraSocial;

    @Test
    public void crearObraSocial_NombreNoExiste_GuardaCorrectamente() throws ObraSocialExisteException {
        ObraSocial obraSocial = new ObraSocial(1, "OSFATUN");
        CrearObraSocialUseCase crearObraSocialUseCase = new CrearObraSocialUseCase(repositorioObraSocial);
        boolean resultado=crearObraSocialUseCase.crearObraSocial(obraSocial);
        when(repositorioObraSocial.findByNombreUnico("OSFATUN")).thenReturn(null);
        Assertions.assertTrue(resultado);
    }

    @Test
    public void crearObraSocial_NombreExiste_ObraSocialExisteException(){
        when(repositorioObraSocial.findByNombreUnico("OSFATUN")).thenReturn(new ObraSocial(1,"OSFATUN"));
        ObraSocial obraSocial = new ObraSocial(1, "OSFATUN");
        CrearObraSocialUseCase crearObraSocialUseCase = new CrearObraSocialUseCase(repositorioObraSocial);
        Assertions.assertThrows(ObraSocialExisteException.class,()->crearObraSocialUseCase.crearObraSocial(obraSocial));
    }

}
