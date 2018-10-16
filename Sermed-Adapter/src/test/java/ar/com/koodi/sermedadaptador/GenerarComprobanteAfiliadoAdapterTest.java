package ar.com.koodi.sermedadaptador;

import Adaptadores.GenerarComprobanteAfiliadoAdapter;
import Excepciones.*;
import Inputs.GenerarComprobanteAfiliadoInput;
import Mockito.MockitoExtension;
import Modelo.*;
import ModeloApi.ComprobanteAfiliadoReporteDTO;
import ModeloReporte.ComprobanteAfiliadoDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenerarComprobanteAfiliadoAdapterTest {

    @Mock
    GenerarComprobanteAfiliadoInput generarComprobanteAfiliadoInput;

    @Test
    public void generarComprobanteAfiliado_ComprobanteExiste_SeGeneraComprobante() throws ComprobanteNoExisteException, ComprobanteIncompletoException, FechaIncorrectaException, NumeroComprobanteIncorrectoException, AfiliadoDeBajaException {
        GenerarComprobanteAfiliadoAdapter generarComprobanteAfiliadoAdapter = new GenerarComprobanteAfiliadoAdapter(generarComprobanteAfiliadoInput);
        when(generarComprobanteAfiliadoInput.generarComprobanteAfiliadoReporte("123-567891")).thenReturn(factoryComprobanteReporte());
        ComprobanteAfiliadoReporteDTO comprobanteAfiliadoReporteDTO = generarComprobanteAfiliadoAdapter.generarComprobanteAfiliadoReporte("123-567891");
        Assertions.assertNotNull(comprobanteAfiliadoReporteDTO);
    }

    @Test
    public void generarComprobanteAfiliado_ComprobanteNoExiste_ComprobanteNoExisteException() throws ComprobanteNoExisteException {
        when(generarComprobanteAfiliadoInput.generarComprobanteAfiliadoReporte("1234-567891")).thenThrow(ComprobanteNoExisteException.class);
        GenerarComprobanteAfiliadoAdapter generarComprobanteAfiliadoAdapter = new GenerarComprobanteAfiliadoAdapter(generarComprobanteAfiliadoInput);
        Assertions.assertThrows(ComprobanteNoExisteException.class, () -> generarComprobanteAfiliadoAdapter.generarComprobanteAfiliadoReporte("1234-567891"));
    }

    private ComprobanteAfiliadoDTO factoryComprobanteReporte() throws ComprobanteIncompletoException, FechaIncorrectaException, NumeroComprobanteIncorrectoException, AfiliadoDeBajaException {
        Comprobante comprobante = Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 123.45, LocalDate.of(2018, 6, 15), "Efectivo", true, listaDePeriodosDePago());
        ComprobanteAfiliadoDTO comprobanteAfiliadoDTO = new ComprobanteAfiliadoDTO();
        comprobanteAfiliadoDTO.generarComprobanteDTO(comprobante);
        return comprobanteAfiliadoDTO;
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
