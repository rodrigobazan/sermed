package ModelTest;

import Excepciones.*;
import Modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.*;


public class ComprobanteUnitTest {


    @Test
    void instanciarComprobante_NumeroComprobanteCorrecto_InstanciaComprobante() throws ComprobanteIncompletoException, AfiliadoDeBajaException, FechaIncorrectaException, NumeroComprobanteIncorrectoException {
        Comprobante unComprobante = Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 500.56, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago());
        Assertions.assertNotNull(unComprobante);
    }

    @Test
    void instanciarComprobante_NumeroComprobanteIncorrecto_NumeroComprobanteIncorrectoException() throws ComprobanteIncompletoException, AfiliadoDeBajaException, FechaIncorrectaException {
        Assertions.assertThrows(NumeroComprobanteIncorrectoException.class, () -> Comprobante.instancia(1, "1234567891", factoryAfiliado(), 500.56, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago()));

    }

    @Test
    void instanciarComprobante_ComprobanteConDatosObligatorios_InstanciaComprobante() throws ComprobanteIncompletoException, AfiliadoDeBajaException, FechaIncorrectaException, NumeroComprobanteIncorrectoException {
        Comprobante unComprobante = Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 500.56, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago());
        Assertions.assertNotNull(unComprobante);
    }

    @Test
    void instanciarComprobante_ComprobanteSinDatosObligatorios_ComprobanteIncompletoException() {
        Assertions.assertThrows(ComprobanteIncompletoException.class, ()-> Comprobante.instancia(1, "", null, 0, null, "", true, new ArrayList<>()));
    }

    @Test
    void instanciaComprobante_AfiliadoDeBaja_AfiliadoDeBajaException() {
        Assertions.assertThrows(AfiliadoDeBajaException.class, ()-> Comprobante.instancia(1, "1234-567891", factoryAfiliadoInactivo(), 500.56, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago()));
    }

    @Test
    void instanciarComprobante_AfiliadoActivo_InstanciaComprobante() throws ComprobanteIncompletoException, AfiliadoDeBajaException, FechaIncorrectaException, NumeroComprobanteIncorrectoException {
        Comprobante unComprobante = Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 500.56, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago());
        Assertions.assertNotNull(unComprobante);
    }

    @Test
    void instanciaComprobante_FechaMayorActual_FechaIncorrectaException(){
        LocalDate fecha = LocalDate.now().plusDays(2);
        Assertions.assertThrows(FechaIncorrectaException.class, () -> Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 500.56, fecha,"Efectivo", true, listaDePeriodosDePago()));
    }

    @Test
    void instanciaComprobante_FechaMenorActual_InstanciaComprobante() throws FechaIncorrectaException, ComprobanteIncompletoException, AfiliadoDeBajaException, NumeroComprobanteIncorrectoException {
        LocalDate fecha = LocalDate.of(2018,1,1);
        Comprobante comprobante = Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 500.56, fecha,"Efectivo", true, listaDePeriodosDePago());
        Assertions.assertNotNull(comprobante);
    }

    private List<PeriodoPago> listaDePeriodosDePago(){
        List<PeriodoPago> periodosPago = new ArrayList<>();
        periodosPago.add(new PeriodoPago(1,2,2018));
        periodosPago.add(new PeriodoPago(2,3,2018));
        periodosPago.add(new PeriodoPago(3,4,2018));
        return periodosPago;
    }

    private Afiliado factoryAfiliadoInactivo() {
        try {
            return Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), false, null, null, factoryPlan());
        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } catch (AfiliadoSinPlanException e) {
            e.printStackTrace();
        } catch (PlanIncompletoException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Afiliado factoryAfiliado() {
        try {
            return Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, null, factoryPlan());
        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } catch (AfiliadoSinPlanException e) {
            e.printStackTrace();
        } catch (PlanIncompletoException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Persona factoryPersonaTitular() {
        try {
            return Persona.instancia(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumento(1, "DNI"),
                    "30672405", new Sangre(1, "A", "RH+"), "3825674978", new ObraSocial(1, "ASDA"), "", factoryAntecedenteMedico(), 0);
        } catch (PersonaIncompletaException e) {
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
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            personas.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            personas.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            personas.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            return personas;
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
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

    private Plan factoryPlan() throws PlanIncompletoException {
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 380);
        listaPrecios.put("2", (double) 480);
        listaPrecios.put("3", (double) 550);
        listaPrecios.put("4", (double) 600);
        listaPrecios.put("5", (double) 650);
        listaPrecios.put("6", (double) 700);
        listaPrecios.put("7", (double) 750);

        return Plan.instancia(1, "Plan Basico", listaPrecios);
    }
}
