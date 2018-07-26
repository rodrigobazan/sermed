package InteractorTest;

import Excepciones.AfiliadoSinTitularException;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Interactor.AfiliarPersonaUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IAfiliadoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AfiliarPersonaUnitTest {

    @Mock
    IAfiliadoRepositorio repositorioAfiliado;

    @Spy
    List<Afiliado> afiliadosList = crearAfiliadoArray();

    @Test
    public void afiliarPersona_PersonaYaAfiliada_NoSeAfilia() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException {
        Persona persona = Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001", null, 0);
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 27), "000003", factoryPersona(), factoryPersonaMiembros(), true, null, null);
        when(repositorioAfiliado.findAll()).thenReturn(afiliadosList);
        AfiliarPersonaUseCase afiliarPersonaUseCase = new AfiliarPersonaUseCase(repositorioAfiliado);
        boolean resultado = afiliarPersonaUseCase.afiliarPersona(persona, afiliado);
        Assertions.assertEquals(false, resultado);

    }


    @Test
    public void afiliarPersona_PersonaNoAfiliada_SiSeAfilia() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException {
        Persona persona = Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "37415281", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001", null, 0);
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 27), "000003", factoryPersona(), factoryPersonaMiembros(), true, null, null);
        when(repositorioAfiliado.findAll()).thenReturn(afiliadosList);
        when(repositorioAfiliado.update(afiliado)).thenReturn(true);
        AfiliarPersonaUseCase afiliarPersonaUseCase = new AfiliarPersonaUseCase(repositorioAfiliado);
        boolean resultado = afiliarPersonaUseCase.afiliarPersona(persona, afiliado);
        Assertions.assertEquals(true, resultado);

    }


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

    private List<Afiliado> crearAfiliadoArray() {
        List<Afiliado> afiliados = new ArrayList<>();
        try {

            Persona titular1 = Persona.instancia(1, "Perez", "Juan", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "10101010", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", null, 0);
            List<Persona> personasAfiliado1 = new ArrayList<>();
            personasAfiliado1.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", null, 0));
            personasAfiliado1.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "000001", null, 0));


            Persona titular2 = Persona.instancia(1, "Paez", "Martin", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "20202020", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000002", null, 0);
            List<Persona> personasAfiliado2 = new ArrayList<>();
            personasAfiliado2.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "000002", null, 0));
            personasAfiliado2.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "000002", null, 0));


            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000001", titular1, personasAfiliado1, true, null, null));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000003", titular2, personasAfiliado2, true, null, null));
            return afiliados;

        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
        } finally {
            return afiliados;
        }

    }


}
