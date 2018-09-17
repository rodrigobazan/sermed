package InteractorTest;

import Excepciones.MatriculasIgualesException;
import Excepciones.NombreObraSocialExisteException;
import Excepciones.UpdateObraSocialException;
import Interactor.ModificarObraSocialUseCase;
import Mockito.MockitoExtension;
import Modelo.ObraSocial;
import Repositorio.IObraSocialRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModificarObraSocialUnitTest {

    @Mock
    IObraSocialRepositorio repositorioObraSocial;

    @Test
    public void modificarObraSocial_DatosObraSocial_AssertTrue() throws NombreObraSocialExisteException, UpdateObraSocialException {

        ModificarObraSocialUseCase modificarObraSocialUseCase = new ModificarObraSocialUseCase(repositorioObraSocial);
        ObraSocial nuevosDatos = new ObraSocial(1, "OSFATURA");

        when(repositorioObraSocial.findById(1)).thenReturn(new ObraSocial(1,"OSFATUN"));
        when(repositorioObraSocial.update(any(ObraSocial.class))).thenReturn(true);

        ObraSocial obraSocialModificada = modificarObraSocialUseCase.modificarObraSocial(nuevosDatos);

        Assertions.assertEquals(nuevosDatos.getNombre(),obraSocialModificada.getNombre());
        Assertions.assertEquals(1,obraSocialModificada.getIdObraSocial().intValue());
    }

    @Test
    public void modificarObraSocial_NombreExiste_NoActualiza(){
        ModificarObraSocialUseCase modificarObraSocialUseCase = new ModificarObraSocialUseCase(repositorioObraSocial);
        when(repositorioObraSocial.findById(1)).thenReturn(new ObraSocial(1,"OSFATUN"));
        when(repositorioObraSocial.findByNombreUnico("OSDE")).thenReturn(new ObraSocial(2,"OSDE"));
        
        ObraSocial obraSocialNueva=new ObraSocial(1,"OSDE");

        Assertions.assertThrows(NombreObraSocialExisteException.class, () -> modificarObraSocialUseCase.modificarObraSocial(obraSocialNueva));
    }
}
