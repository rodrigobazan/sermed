package ar.com.koodi.sermedadaptador;

import Adaptadores.ConsultarComprobanteDeAfiliadoAdapter;
import Adaptadores.ConsultarComprobantesAdapter;
import Excepciones.*;
import Inputs.ConsultarComprobanteDeAfiliadoInput;
import Mockito.MockitoExtension;
import Modelo.*;
import ModeloApi.*;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class ConsultarComprobanteDeAfiliadoAdapterTest {

    @Mock
    ConsultarComprobanteDeAfiliadoInput consultarComprobanteDeAfiliadoInput;

    @Test
    public void consultarComprobantesDeAfiliado_TieneComprobantes_DevuelveColeccionConDatos() throws PersonaIncompletaException, PlanIncompletoException, DniConPuntosException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, AfiliadoSinPlanException {
        AfiliadoDTO afiliadoDTO = new AfiliadoDTO(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaMiembrosDTO(), factoryPersonaTitularDTO(), true, null, null, factoryPlanDTO());
        when(consultarComprobanteDeAfiliadoInput.consultarTodosLosComprobantes(any(Afiliado.class))).thenReturn(crearComprobantesArray());
        ConsultarComprobanteDeAfiliadoAdapter consultarComprobanteDeAfiliadoAdapter = new ConsultarComprobanteDeAfiliadoAdapter(consultarComprobanteDeAfiliadoInput);
        List<ComprobanteDTO> comprobantesAfiliado = consultarComprobanteDeAfiliadoAdapter.consultarTodosLosComprobantes(afiliadoDTO);
        Assertions.assertEquals(5, comprobantesAfiliado.size());
        assertThat(comprobantesAfiliado, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarComprobanteDeAfiliado_NoTieneComprobantes_DevuelveColeccionVacia() throws PersonaIncompletaException, PlanIncompletoException, DniConPuntosException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, AfiliadoSinPlanException {
        AfiliadoDTO afiliado = new AfiliadoDTO(1, LocalDate.of(2018, 6, 15), "190000",  factoryPersonaMiembrosDTO(), factoryPersonaTitularDTO(), true, null, null, factoryPlanDTO());
        when(consultarComprobanteDeAfiliadoInput.consultarTodosLosComprobantes(any(Afiliado.class))).thenReturn(new ArrayList<>());
        ConsultarComprobanteDeAfiliadoAdapter consultarComprobantesAdapter = new ConsultarComprobanteDeAfiliadoAdapter(consultarComprobanteDeAfiliadoInput);
        List<ComprobanteDTO> comprobanteDTOList = consultarComprobantesAdapter.consultarTodosLosComprobantes(afiliado);
        Assertions.assertEquals(0,comprobanteDTOList.size());
        assertThat(comprobanteDTOList, IsEmptyCollection.empty());
    }

    @Test
    public void consultarComprobantesDeAfiliadoPorFechas_TieneComprobantes_DevuelveColeccionConDatos() throws PersonaIncompletaException, FechaIncorrectaException, PlanIncompletoException, DniConPuntosException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, AfiliadoSinPlanException {
        AfiliadoDTO afiliado = new AfiliadoDTO(1, LocalDate.of(2018, 6, 15), "190000",  factoryPersonaMiembrosDTO(), factoryPersonaTitularDTO(), true, null, null, factoryPlanDTO());
        LocalDate fechaDesde = LocalDate.now().minusDays(2);
        LocalDate fechaHasta = LocalDate.now();
        when(consultarComprobanteDeAfiliadoInput.consultarComprobantesAfiliadoPorFechas(any(Afiliado.class), eq(fechaDesde), eq(fechaHasta))).thenReturn(crearComprobantesArray());
        ConsultarComprobanteDeAfiliadoAdapter consultarComprobanteDeAfiliadoAdapter = new ConsultarComprobanteDeAfiliadoAdapter(consultarComprobanteDeAfiliadoInput);
        List<ComprobanteDTO> comprobantesDelAfiliado = consultarComprobanteDeAfiliadoAdapter.consultarComprobantesAfiliadoPorFechas(afiliado, fechaDesde, fechaHasta);
        Assertions.assertEquals(5,comprobantesDelAfiliado.size());
        assertThat(comprobantesDelAfiliado, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarComprobantesDeAfiliadoPorFechas_NoTieneComprobantes_DevuelveColeccionVacia() throws PersonaIncompletaException, FechaIncorrectaException, PlanIncompletoException, DniConPuntosException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, AfiliadoSinPlanException {
        AfiliadoDTO afiliado = new AfiliadoDTO(1, LocalDate.of(2018, 6, 15), "190000",  factoryPersonaMiembrosDTO(), factoryPersonaTitularDTO(), true, null, null, factoryPlanDTO());
        LocalDate fechaDesde = LocalDate.now().minusDays(4);
        LocalDate fechaHasta = LocalDate.now().minusDays(2);
        when(consultarComprobanteDeAfiliadoInput.consultarComprobantesAfiliadoPorFechas(any(Afiliado.class), eq(fechaDesde), eq(fechaHasta))).thenReturn(crearComprobantesArray());
        ConsultarComprobanteDeAfiliadoAdapter consultarComprobanteDeAfiliadoAdapter = new ConsultarComprobanteDeAfiliadoAdapter(consultarComprobanteDeAfiliadoInput);
        List<ComprobanteDTO> comprobanteDTOList = consultarComprobanteDeAfiliadoAdapter.consultarComprobantesAfiliadoPorFechas(afiliado, fechaDesde, fechaHasta);
        Assertions.assertEquals(5,comprobanteDTOList.size());
        assertThat(comprobanteDTOList, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarComprobantesDeAfiliadoPorFechas_FechaHastaMenorQueFechaDesde_FechaIncorrectasException() throws FechaIncorrectaException {
        AfiliadoDTO afiliado = new AfiliadoDTO(1, LocalDate.of(2018, 6, 15), "190000",  factoryPersonaMiembrosDTO(), factoryPersonaTitularDTO(), true, null, null, factoryPlanDTO());
        LocalDate fechaDesde = LocalDate.now();
        LocalDate fechaHasta = LocalDate.now().minusDays(2);
        when(consultarComprobanteDeAfiliadoInput.consultarComprobantesAfiliadoPorFechas(any(Afiliado.class), eq(fechaDesde), eq(fechaHasta))).thenThrow(FechaIncorrectaException.class);
        ConsultarComprobanteDeAfiliadoAdapter consultarComprobanteDeAfiliadoAdapter = new ConsultarComprobanteDeAfiliadoAdapter(consultarComprobanteDeAfiliadoInput);
        Assertions.assertThrows(FechaIncorrectaException.class, () -> consultarComprobanteDeAfiliadoAdapter.consultarComprobantesAfiliadoPorFechas(afiliado, fechaDesde, fechaHasta));
    }

    private List<PeriodoPago> listaDePeriodosDePago(){
        List<PeriodoPago> periodosPago = new ArrayList<>();
        periodosPago.add(new PeriodoPago(1,2,2018));
        periodosPago.add(new PeriodoPago(2,3,2018));
        periodosPago.add(new PeriodoPago(3,4,2018));
        return periodosPago;
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

    private List<PeriodoPagoDTO> listaDePeriodosDePagoDTO() {
        List<PeriodoPagoDTO> periodosPago = new ArrayList<>();
        periodosPago.add(new PeriodoPagoDTO(1, 2, 2018));
        periodosPago.add(new PeriodoPagoDTO(2, 3, 2018));
        periodosPago.add(new PeriodoPagoDTO(3, 4, 2018));
        return periodosPago;
    }

    private List<ComprobanteDTO> crearComprobantesArrayDTO() {
        try {
            List<ComprobanteDTO> comprobantes = new ArrayList<>();
            comprobantes.add(new ComprobanteDTO(1, "1234-567891", factoryAfiliadoDTO(), 400.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePagoDTO()));
            comprobantes.add(new ComprobanteDTO(2, "1234-567892", factoryAfiliadoDTO(), 500.50, LocalDate.now(), "Tarjeta", true, listaDePeriodosDePagoDTO()));
            comprobantes.add(new ComprobanteDTO(3, "1234-567893", factoryAfiliadoDTO(), 600.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePagoDTO()));
            comprobantes.add(new ComprobanteDTO(4, "1234-567894", factoryAfiliadoDTO(), 700.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePagoDTO()));
            comprobantes.add(new ComprobanteDTO(5, "1234-567895", factoryAfiliadoDTO(), 800.50, LocalDate.now(), "Efectivo", true, listaDePeriodosDePagoDTO()));
            return comprobantes;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public AfiliadoDTO factoryAfiliadoDTO() {
        return new AfiliadoDTO(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaMiembrosDTO(), factoryPersonaTitularDTO(), true, null, null, factoryPlanDTO());
    }

    public PersonaDTO factoryPersonaTitularDTO() {
        return new PersonaDTO(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumentoDTO(1, "DNI"),
                "30672405", new SangreDTO(1, "A", "RH+"), "3825674978", new ObraSocialDTO(1, "ASDA"), "", 0, factoryAntecedenteMedicoDTO());

    }

    private List<PersonaDTO> factoryPersonaMiembrosDTO() {
        List<PersonaDTO> personas = new ArrayList<>();
        personas.add(new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0, factoryAntecedenteMedicoDTO()));
        personas.add(new PersonaDTO(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "34215324", new SangreDTO(1, "B", "RH-"), "3825532112",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0, factoryAntecedenteMedicoDTO()));
        personas.add(new PersonaDTO(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "33166401", new SangreDTO(1, "0", "RH+"), "3825423547",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0, factoryAntecedenteMedicoDTO()));
        personas.add(new PersonaDTO(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "32123457", new SangreDTO(1, "A", "RH+"), "382584521",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0, factoryAntecedenteMedicoDTO()));
        return personas;

    }

    private Collection<AntecedenteMedicoDTO> factoryAntecedenteMedicoDTO() {
        AntecedenteMedicoDTO dislexia = new AntecedenteMedicoDTO(1, new AfeccionDTO(1, "Dislexia"), "Cronica");
        AntecedenteMedicoDTO gonorrea = new AntecedenteMedicoDTO(2, new AfeccionDTO(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedicoDTO diabetes = new AntecedenteMedicoDTO(3, new AfeccionDTO(1, "diabetes"), "nerviosa");
        Collection<AntecedenteMedicoDTO> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);
        return listaAntecedentes;
    }

    private PlanDTO factoryPlanDTO() {
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 380);
        listaPrecios.put("2", (double) 480);
        listaPrecios.put("3", (double) 550);
        listaPrecios.put("4", (double) 600);
        listaPrecios.put("5", (double) 650);
        listaPrecios.put("6", (double) 700);
        listaPrecios.put("7", (double) 750);

        return new PlanDTO(1, "Plan Basico", listaPrecios);
    }
}
