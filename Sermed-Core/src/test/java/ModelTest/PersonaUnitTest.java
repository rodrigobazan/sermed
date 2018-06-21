package ModelTest;

import Excepciones.PersonaIncompletaException;
import Mockito.MockitoExtension;
import Modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.Collection;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonaUnitTest {

    @Mock
    Persona laPersona;

    @Test
    void instanciarPersona_atributosObligatorios_personaInstanciada() throws PersonaIncompletaException {

        Persona unaPersona=Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),null,new TipoDocumento(1, "DNI"),
                "30672405",new Sangre(1,"A","RH+"),null, null, "190000-01",null);

        Assertions.assertNotNull(unaPersona);

    }

    @Test
    void instanciaPersona_sinAtributosObligatorios_PersonaIncompletaException() throws PersonaIncompletaException {

        Assertions.assertThrows(PersonaIncompletaException.class, () -> Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),null,new TipoDocumento(1, "DNI"),
                "30672405",null,null, null, "190000-01",null));
    }
}
