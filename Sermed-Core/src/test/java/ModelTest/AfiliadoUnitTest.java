package ModelTest;

import Excepciones.AfiliadoSinTitularException;
import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AfiliadoUnitTest {

    @Test
    void mostrarAfiliado_AfiliadoCompleto_MuestraFormateado() {
        try {
            Afiliado unAfiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, null);
            String mostrarAfiliado = unAfiliado.mostrarAfiliado();
            Assertions.assertEquals("190000. Titular: Ruitti, Javiel (190000-00).", mostrarAfiliado);

        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        }
    }

    @Test
    void instanciaAfiliado_PersonaNoNula_afiliadoInstanciado() {
        try {
            Afiliado unAfiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, null);
            Assertions.assertNotNull(unAfiliado);
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } catch (AfiliadoSinTitularException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    void instanciaAfiliado_PersonaNula_afiliadoSinPersonaException() {
        Assertions.assertThrows(AfiliadoSinTitularException.class, () -> Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", null, factoryPersonaMiembros(), true, null, null));
    }

    @Test
    void instanciaAfiliado_NumeroAfiliadoCorrecto_afiliadoInstanciado() {
        try {
            Afiliado unAfiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, null);
            Assertions.assertNotNull(unAfiliado);
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } catch (AfiliadoSinTitularException ex) {
            ex.printStackTrace();
        }
    }


    @Test
    void instanciaAfiliado_NumeroAfiliadoIncorrecto_NumeroAfiliadoIncorrectoException() {
        Assertions.assertThrows(NumeroAfiliadoIncorrectoException.class, () -> Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "19000-02", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, null));

    }

    @Test
    void agregarPersona_NumeroAfiliadoCorrecto_DevuelveTrue() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException {
        Persona persona = Persona.instancia(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumento(1, "DNI"),
                "30672405", new Sangre(1, "A", "RH+"), "3825674978", new ObraSocial(1, "ASDA"), "", factoryAntecedenteMedico(), 0);
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, null);
        afiliado.agregarPersona(persona);
        Assertions.assertEquals(5, afiliado.getMiembros().size());
        Assertions.assertEquals("190000-05", afiliado.getMiembros().get(afiliado.getMiembros().size() - 1).getNumeroAfiliado());


    }

    @Test
    void agregarTitular_NumeroAfiliadoCorrecto_DevuelveTrue() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException {
        Persona persona = Persona.instancia(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumento(1, "DNI"),
                "30672405", new Sangre(1, "A", "RH+"), "3825674978", new ObraSocial(1, "ASDA"), "", factoryAntecedenteMedico(), 0);
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, null);
        afiliado.agregarPersona(persona);
        Assertions.assertEquals("190000-00", afiliado.getTitular().getNumeroAfiliado());
    }


    public Persona factoryPersonaTitular() {
        try {
            return Persona.instancia(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumento(1, "DNI"),
                    "30672405", new Sangre(1, "A", "RH+"), "3825674978", new ObraSocial(1, "ASDA"), "190000", factoryAntecedenteMedico(), 0);
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
            return null;
        } catch (NumeroAfiliadoIncorrectoException ex) {
            ex.printStackTrace();
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
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 1));
            personas.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 2));
            personas.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 3));
            personas.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 4));
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

    private Collection<AntecedenteMedico> factoryAntecedenteMedico() {
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1, "Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2, new Afeccion(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3, new Afeccion(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }

}
