package ModelTest;

import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Mockito.MockitoExtension;
import Modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class PersonaUnitTest {

    @Test
    void instanciarPersona_atributosObligatorios_personaInstanciada(){
        try {
            Persona unaPersona=Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),null,new TipoDocumento(1, "DNI"),
                    "30672405",new Sangre(1,"A","RH+"),null, null, "190000",null,0);
            Assertions.assertNotNull(unaPersona);
        }catch (NumeroAfiliadoIncorrectoException ex){
            ex.printStackTrace();
        }catch (PersonaIncompletaException e){
            e.printStackTrace();
        }
    }

    @Test
    void instanciaPersona_sinAtributosObligatorios_personaIncompletaException() {

        Assertions.assertThrows(PersonaIncompletaException.class, () -> Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),null,new TipoDocumento(1, "DNI"),
                "30672405",null,null, null, "190000",null,0));
    }
}
