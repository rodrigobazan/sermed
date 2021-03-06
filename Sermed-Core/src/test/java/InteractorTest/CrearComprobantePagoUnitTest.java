package InteractorTest;

import Excepciones.*;
import Interactor.CrearComprobantePagoUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IComprobanteRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearComprobantePagoUnitTest {

    @Mock
    IComprobanteRepositorio repositorioComprobante;

    @Test
    public void crearComprobante_ComprobanteExiste_NoSeCreaComprobante() throws ComprobanteExisteException, ComprobanteIncompletoException, AfiliadoDeBajaException, FechaIncorrectaException, NumeroComprobanteIncorrectoException {
        Comprobante comprobante = Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 123.45, LocalDate.of(2018, 6, 15), "Efectivo", true, listaDePeriodosDePago());
        CrearComprobantePagoUseCase crearComprobantePagoUseCase = new CrearComprobantePagoUseCase(repositorioComprobante);
        when(repositorioComprobante.findByNumero("1234-567891")).thenReturn(Comprobante.instancia(2, "1234-567891", factoryAfiliado(), 500.45, LocalDate.of(2018, 3, 25), "Efectivo", true, listaDePeriodosDePago()));

        Assertions.assertThrows(ComprobanteExisteException.class, () -> crearComprobantePagoUseCase.crearComprobante(comprobante));
    }

    @Test
    public void crearComprobante_ComprobanteNoExiste_SeCreaComprobante() throws ComprobanteExisteException, ComprobanteIncompletoException, AfiliadoDeBajaException, FechaIncorrectaException, NumeroComprobanteIncorrectoException {
        Comprobante comprobante = Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 123.45, LocalDate.of(2018, 6, 15), "Efectivo", true, listaDePeriodosDePago());
        CrearComprobantePagoUseCase crearComprobantePagoUseCase = new CrearComprobantePagoUseCase(repositorioComprobante);
        when(repositorioComprobante.findByNumero("1234-567891")).thenReturn(null);
        when(repositorioComprobante.persist(comprobante)).thenReturn(true);
        boolean resultado = crearComprobantePagoUseCase.crearComprobante(comprobante);
        Assertions.assertEquals(true, resultado);
    }

    private List<PeriodoPago> listaDePeriodosDePago(){
        List<PeriodoPago> periodosPago = new ArrayList<>();
        periodosPago.add(new PeriodoPago(1,2,2018));
        periodosPago.add(new PeriodoPago(2,3,2018));
        periodosPago.add(new PeriodoPago(3,4,2018));
        return periodosPago;
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
