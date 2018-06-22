package ModelTest;

import Excepciones.AfiliadoSinPersonaException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Modelo.Afiliado;
import Modelo.Persona;
import Modelo.Sangre;
import Modelo.TipoDocumento;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AfiliadoUnitTest {

    @Test
    void instanciaAfiliado_personaNoNula_afiliadoInstanciado() throws PersonaIncompletaException, AfiliadoSinPersonaException, NumeroAfiliadoIncorrectoException {
        Persona persona = Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),null,new TipoDocumento(1, "DNI"),
                "30672405",new Sangre(1,"A","RH+"),null, null, "190000-01",null);

        Afiliado unAfiliado = Afiliado.instancia(1, LocalDate.of(2018,6,15), "190000-02", persona, true);

        Assertions.assertNotNull(unAfiliado);
    }

    @Test
    void instanciaAfiliado_personaNula_afiliadoSinPersonaException(){
        Assertions.assertThrows(AfiliadoSinPersonaException.class, () -> Afiliado.instancia(1, LocalDate.of(2018,6,15), "190000-02", null, true));
    }

    @Test
    void instanciaAfiliado_numeroAfiliadoCorrecto_afiliadoInstanciado() throws PersonaIncompletaException, AfiliadoSinPersonaException, NumeroAfiliadoIncorrectoException {
        Persona persona = Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),null,new TipoDocumento(1, "DNI"),
                "30672405",new Sangre(1,"A","RH+"),null, null, "190000-01",null);

        Afiliado unAfiliado = Afiliado.instancia(1, LocalDate.of(2018,6,15), "190000-02", persona, true);

        Assertions.assertNotNull(unAfiliado);

    }


    @Test
    void instanciaAfiliado_numeroAfiliadoIncorrecto_() throws PersonaIncompletaException, AfiliadoSinPersonaException, NumeroAfiliadoIncorrectoException {
        Persona persona = Persona.instancia(null, "Ruitti", "Javiel", LocalDate.of(1984,1,31),null,new TipoDocumento(1, "DNI"),
                "30672405",new Sangre(1,"A","RH+"),null, null, "190000-01",null);

        Assertions.assertThrows(NumeroAfiliadoIncorrectoException.class, () -> Afiliado.instancia(1, LocalDate.of(2018,6,15), "190000-02", persona, true));

    }

}
