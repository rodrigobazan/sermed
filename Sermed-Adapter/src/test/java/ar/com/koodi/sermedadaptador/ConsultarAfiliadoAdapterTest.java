package ar.com.koodi.sermedadaptador;

import Adaptadores.ConsultarAfiliadoAdapter;
import Adaptadores.ConsultarMedicoAdapter;
import Excepciones.*;
import Inputs.ConsultarAfiliadoInput;
import Mockito.MockitoExtension;
import Modelo.*;
import ModeloApi.AfiliadoDTO;
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
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarAfiliadoAdapterTest {

    @Mock
    ConsultarAfiliadoInput consultarAfiliadoInput;

    @Spy
    List<Afiliado> listaAfiliados = crearAfiliadoArray();

    @Test
    public void consultarAfiliados_ExistenDatos_ColeccionConDatos(){
        ConsultarAfiliadoAdapter consultarAfiliadoAdapter = new ConsultarAfiliadoAdapter(consultarAfiliadoInput);
        when(consultarAfiliadoInput.consultarAfiliados()).thenReturn(listaAfiliados);
        List<AfiliadoDTO> afiliadoDTOS = consultarAfiliadoAdapter.consultarAfiliados();
        Assertions.assertEquals(afiliadoDTOS.size(), listaAfiliados.size());
        assertThat(afiliadoDTOS, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarAfiliados_NoExistenDatos_ColeccionVacia(){
        ConsultarAfiliadoAdapter consultarAfiliadoAdapter = new ConsultarAfiliadoAdapter(consultarAfiliadoInput);
        when(consultarAfiliadoInput.consultarAfiliados()).thenReturn(new ArrayList<>());
        List<AfiliadoDTO> afiliados = consultarAfiliadoAdapter.consultarAfiliados();
        assertThat(afiliados, IsEmptyCollection.empty());
    }

    @Test
    public void consultarAfiliadosPorNumero_CriterioCadenaVacia_DevolverTodos(){
        ConsultarAfiliadoAdapter consultarAfiliadoAdapter = new ConsultarAfiliadoAdapter(consultarAfiliadoInput);
        when(consultarAfiliadoInput.consultarAfiliadosPorNumero("")).thenReturn(listaAfiliados);
        List<AfiliadoDTO> afiliadoDTOS = consultarAfiliadoAdapter.consultarAfiliadosPorNumero("");
        assertEquals(afiliadoDTOS.size(), listaAfiliados.size());
        assertThat(afiliadoDTOS, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarAfiliadosPorNumero_CriterioCadenaConDatos_DevolverAlgunos(){
        ConsultarAfiliadoAdapter consultarAfiliadoAdapter = new ConsultarAfiliadoAdapter(consultarAfiliadoInput);
        when(consultarAfiliadoInput.consultarAfiliadosPorNumero("01")).thenReturn(crearAfiliadosFiltroNumero());
        List<AfiliadoDTO> afiliadoDTOS = consultarAfiliadoAdapter.consultarAfiliadosPorNumero("01");
        assertThat(afiliadoDTOS, hasSize(2));
        assertThat(afiliadoDTOS, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarAfiliadoNumero_NumeroExiste_RetornaAfiliado() throws AfiliadoNoExisteException, PlanIncompletoException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
        when(consultarAfiliadoInput.consultarAfiliadoPorNumero("000001")).thenReturn(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000001", factoryPersona().get(0), factoryPersona(), true, null, null, factoryPlan()));
        ConsultarAfiliadoAdapter consultarAfiliadoAdapter = new ConsultarAfiliadoAdapter(consultarAfiliadoInput);
        AfiliadoDTO afiliado = consultarAfiliadoAdapter.consultarAfiliadoPorNumero("000001");
        Assertions.assertEquals(1, afiliado.idAfiliado.intValue());
    }

    @Test
    public void consultarAfiliadoNumero_NumeroNoExiste_RetornaAfiliadoNoExisteException() throws AfiliadoNoExisteException {
        when(consultarAfiliadoInput.consultarAfiliadoPorNumero("000001")).thenThrow(AfiliadoNoExisteException.class);
        ConsultarAfiliadoAdapter consultarAfiliadoAdapter = new ConsultarAfiliadoAdapter(consultarAfiliadoInput);
        Assertions.assertThrows(AfiliadoNoExisteException.class, () -> consultarAfiliadoAdapter.consultarAfiliadoPorNumero("000001"));
    }


    private List<Afiliado> crearAfiliadoArray() {
        List<Afiliado> afiliados = new ArrayList<>();
        try {
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000001", factoryPersona().get(0), factoryPersona(), true, null, null, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000003", factoryPersona().get(1), factoryPersona(), true, null, null, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000004", factoryPersona().get(2), factoryPersona(), true, null, null, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000005", factoryPersona().get(3), factoryPersona(), true, null, null, factoryPlan()));
            return afiliados;
        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } finally {
            return afiliados;
        }
    }

    private List<Persona> factoryPersona() {
        List<Persona> personas = new ArrayList<>();
        try {
            personas.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0));
            personas.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "000002", factoryAntecedenteMedico(), 0));
            personas.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "000003", factoryAntecedenteMedico(), 0));
            personas.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "000004", factoryAntecedenteMedico(), 0));
            return personas;
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
            return null;
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
            return null;
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Collection<AntecedenteMedico> factoryAntecedenteMedico() {
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1, "Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2, new Afeccion(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3, new Afeccion(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }

    private List<Afiliado> crearAfiliadosFiltroNumero() {
        List<Afiliado> afiliados = new ArrayList<>();
        try {
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000001", factoryPersona().get(0), factoryPersona(), true, null, null, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 06, 20), "000100", factoryPersona().get(1), factoryPersona(), true, null, null, factoryPlan()));
            return afiliados;
        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        } finally {
            return afiliados;
        }
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

        return Plan.instancia(1,"Plan Basico",listaPrecios);
    }
}
