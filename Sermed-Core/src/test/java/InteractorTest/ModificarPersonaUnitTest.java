package InteractorTest;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Interactor.ModificarPersonaUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IPersonaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ModificarPersonaUnitTest {

    @Mock
    IPersonaRepositorio repositorioPersona;

    @Test
    public void modificarPersona_DatosPersona_DevuelveTrue() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException {
        Persona personaDatosNuevos = Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000002", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0);


        when(repositorioPersona.findById(1)).thenReturn(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0));
        when(repositorioPersona.findByDocumentoAndTipoDocumento("14000002", "DNI")).thenReturn(null);
        when(repositorioPersona.update(any(Persona.class))).thenReturn(true);

        ModificarPersonaUseCase modificarPersonaUseCase = new ModificarPersonaUseCase(repositorioPersona);
        boolean resultado = modificarPersonaUseCase.modificarPersona(personaDatosNuevos);
        Assertions.assertEquals(true, resultado);
    }

    @Test
    public void modificarPersona_PersonaExiste_NoActualiza() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException {
        Persona personaDatosNuevos = Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0);


        when(repositorioPersona.findById(1)).thenReturn(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0));
        when(repositorioPersona.findByDocumentoAndTipoDocumento("14000002", "DNI")).thenReturn(Persona.instancia(2, "Torreson", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0));
        when(repositorioPersona.update(any(Persona.class))).thenReturn(false);

        ModificarPersonaUseCase modificarPersonaUseCase = new ModificarPersonaUseCase(repositorioPersona);
        boolean resultado = modificarPersonaUseCase.modificarPersona(personaDatosNuevos);
        Assertions.assertEquals(false, resultado);


    }


    private Collection<AntecedenteMedico> factoryAntecedenteMedico() {
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1, "Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2, new Afeccion(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3, new Afeccion(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }
}
