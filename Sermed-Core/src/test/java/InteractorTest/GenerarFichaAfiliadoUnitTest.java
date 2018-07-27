package InteractorTest;

import Excepciones.AfiliadoSinTitularException;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Interactor.GenerarFichaAfiliadoUseCase;
import Modelo.*;
import Repositorio.IAfiliadoRepositorio;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenerarFichaAfiliadoUnitTest {

    @Mock
    IAfiliadoRepositorio repositorioAfiliado;

    /*@Test
    public void generarFinaAfiliado_AfiliadoExiste_SeGeneraFicha() throws PersonaIncompletaException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException {
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 27), "000003", factoryPersona(), factoryPersonaMiembros(), true, null, null);

        GenerarFichaAfiliadoUseCase generarFichaAfiliadoUseCase = new GenerarFichaAfiliadoUseCase(repositorioAfiliado);

        generarFichaAfiliadoUseCase.generarFichaAfiliadoParaReporte(afiliado);

    }*/


    private Persona factoryPersona() throws PersonaIncompletaException {
        try {
            return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", null, 0);
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
            return null;
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
        }

    }

    private List<Persona> factoryPersonaMiembros() {
        try {
            List<Persona> personas = new ArrayList<>();
            personas.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
            personas.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
            personas.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
            personas.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "190000", null, 0));
            return personas;
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (NumeroAfiliadoIncorrectoException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }
}
