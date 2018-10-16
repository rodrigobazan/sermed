package ar.com.koodi.sermedadaptador;

import Adaptadores.ConsultarComprobantesAdapter;
import Excepciones.*;
import Inputs.ConsultarComprobantesInput;
import Mockito.MockitoExtension;
import Modelo.*;
import ModeloApi.ComprobanteDTO;
import org.hamcrest.CoreMatchers;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarComprobantesDePagoAdapterTest {

    @Mock
    ConsultarComprobantesInput consultarComprobantesInput;

    @Spy
    List<Comprobante> listaComprobantes = crearComprobantesArray();

    @Test
    public void consultarComprobantes_ExistenComprobantes_ColeccionConDatos(){
        when(consultarComprobantesInput.consultarComprobantes()).thenReturn(listaComprobantes);
        ConsultarComprobantesAdapter consultarComprobantesAdapter = new ConsultarComprobantesAdapter(consultarComprobantesInput);
        List<ComprobanteDTO> comprobantes = consultarComprobantesAdapter.consultarComprobantes();
        Assertions.assertEquals(5,comprobantes.size());
        assertThat(comprobantes, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarComprobantes_NoExistenComprobantes_ColeccionVacia(){
        when(consultarComprobantesInput.consultarComprobantes()).thenReturn(new ArrayList<>());
        ConsultarComprobantesAdapter consultarComprobantesAdapter = new ConsultarComprobantesAdapter(consultarComprobantesInput);
        List<ComprobanteDTO> comprobantes = consultarComprobantesAdapter.consultarComprobantes();
        Assertions.assertEquals(0,comprobantes.size());
        assertThat(comprobantes, IsEmptyCollection.empty());
    }

    @Test
    public void consultarComprobantePorNumero_ComprobanteNoExiste_ComprobanteNoExisteException() throws ComprobanteNoExisteException {
        when(consultarComprobantesInput.consultarComprobantePorNumero("123")).thenThrow(ComprobanteNoExisteException.class);
        ConsultarComprobantesAdapter consultarComprobantesAdapter = new ConsultarComprobantesAdapter(consultarComprobantesInput);
        Assertions.assertThrows(ComprobanteNoExisteException.class, () -> consultarComprobantesAdapter.consultarComprobantePorNumero("123"));
    }

    @Test
    public void consultarComprobantePorNumero_CriterioCadenaConDatos_DevolverAlgunos() throws ComprobanteNoExisteException, ComprobanteIncompletoException, FechaIncorrectaException, NumeroComprobanteIncorrectoException, AfiliadoDeBajaException {
        when(consultarComprobantesInput.consultarComprobantePorNumero("123-567891")).thenReturn(Comprobante.instancia(4, "1234-567891", factoryAfiliado(), 700.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago()));
        ConsultarComprobantesAdapter consultarComprobantesAdapter = new ConsultarComprobantesAdapter(consultarComprobantesInput);
        ComprobanteDTO comprobanteDTO = consultarComprobantesAdapter.consultarComprobantePorNumero("123-567891");
        Assertions.assertNotNull(comprobanteDTO);
    }

    @Test
    public void consultarComprobantesPorFecha_ExistenComprobantes_DevolverComprobantes() throws FechaIncorrectaException {
        ConsultarComprobantesAdapter consultarComprobantesAdapter = new ConsultarComprobantesAdapter(consultarComprobantesInput);
        LocalDate fechaDesde = LocalDate.of(2018,8,1);
        LocalDate fechaHasta = LocalDate.of(2018,8,6);
        when(consultarComprobantesInput.consultarComprobantesPorFechas(fechaDesde, fechaHasta)).thenReturn(crearComprobantesFiltroArray());
        List<ComprobanteDTO> comprobanteDTOS = consultarComprobantesAdapter.consultarComprobantesPorFechas(fechaDesde, fechaHasta);
        Assertions.assertEquals(2, comprobanteDTOS.size());
    }

    @Test
    public void consultarComprobantesPorFechas_NoExistenComprobantes_DevolverComprobantes() throws FechaIncorrectaException {
        ConsultarComprobantesAdapter consultarComprobantesAdapter = new ConsultarComprobantesAdapter(consultarComprobantesInput);
        LocalDate fechaDesde = LocalDate.of(2018,8,1);
        LocalDate fechaHasta = LocalDate.of(2018,8,6);
        when(consultarComprobantesInput.consultarComprobantesPorFechas(fechaDesde, fechaHasta)).thenReturn(new ArrayList<>());
        List<ComprobanteDTO> comprobantes = consultarComprobantesAdapter.consultarComprobantesPorFechas(fechaDesde, fechaHasta);
        Assertions.assertEquals(0, comprobantes.size());
        assertThat(comprobantes, IsEmptyCollection.empty());
    }

    private List<Comprobante> crearComprobantesFiltroArray() {
        try {
            List<Comprobante> comprobantes = new ArrayList<>();
            comprobantes.add(Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 400.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago()));
            comprobantes.add(Comprobante.instancia(2, "1234-567892", factoryAfiliado(), 500.50, LocalDate.now(), "Tarjeta", true, listaDePeriodosDePago()));
            return comprobantes;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private List<Comprobante> crearComprobantesArray() {
        try {
            List<Comprobante> comprobantes = new ArrayList<>();
            comprobantes.add(Comprobante.instancia(1, "1234-567891", factoryAfiliado(), 400.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago()));
            comprobantes.add(Comprobante.instancia(2, "1234-567892", factoryAfiliado(), 500.50, LocalDate.now(), "Tarjeta", true, listaDePeriodosDePago()));
            comprobantes.add(Comprobante.instancia(3, "1234-567893", factoryAfiliado(), 600.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago()));
            comprobantes.add(Comprobante.instancia(4, "1234-567894", factoryAfiliado(), 700.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago()));
            comprobantes.add(Comprobante.instancia(5, "1234-567895", factoryAfiliado(), 800.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePago()));
            return comprobantes;
        }catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
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
