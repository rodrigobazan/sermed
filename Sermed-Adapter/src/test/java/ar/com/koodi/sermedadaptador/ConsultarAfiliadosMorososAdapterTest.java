package ar.com.koodi.sermedadaptador;

import Adaptadores.ConsultarAfiliadosMorososAdapter;
import Excepciones.*;
import Inputs.ConsultarAfiliadosMorososInput;
import Mockito.MockitoExtension;
import Modelo.*;
import ModeloApi.AfiliadoDTO;
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
public class ConsultarAfiliadosMorososAdapterTest {

    @Mock
    ConsultarAfiliadosMorososInput consultarAfiliadosMorososInput;

    @Spy
    List<Afiliado> listaAfiliados = crearAfiliadoArray();

    @Test
    public void mostrarAfiliadosMorosos_ExistenMorosos_DevuelveListaAfiliados() {
        LocalDate fecha = LocalDate.of(2018, 7, 14);
        when(consultarAfiliadosMorososInput.consultarAfiliadosMorosos(fecha)).thenReturn(listaAfiliados);
        ConsultarAfiliadosMorososAdapter consultarAfiliadosMorososAdapter = new ConsultarAfiliadosMorososAdapter(consultarAfiliadosMorososInput);
        List<AfiliadoDTO> afiliadosMorosos = consultarAfiliadosMorososAdapter.consultarAfiliadosMorosos(fecha);
        Assertions.assertEquals(2, afiliadosMorosos.size());
        assertThat(afiliadosMorosos, not(IsEmptyCollection.empty()));
    }

    @Test
    public void mostrarAfiliadosMorosos_NoExistenMorosos_DevuelveListaVacia(){
        LocalDate fecha = LocalDate.of(2018, 7, 5);
        when(consultarAfiliadosMorososInput.consultarAfiliadosMorosos(fecha)).thenReturn(new ArrayList<>());
        ConsultarAfiliadosMorososAdapter consultarAfiliadosMorososAdapter = new ConsultarAfiliadosMorososAdapter(consultarAfiliadosMorososInput);
        List<AfiliadoDTO> afiliadosMorosos = consultarAfiliadosMorososAdapter.consultarAfiliadosMorosos(fecha);
        Assertions.assertEquals(0, afiliadosMorosos.size());
        assertThat(afiliadosMorosos, IsEmptyCollection.empty());
    }

    private List<Afiliado> crearAfiliadoArray() {
        List<Afiliado> afiliados = new ArrayList<>();
        try {
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 6, 20), "000001", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, 1, factoryPlan()));
            afiliados.add(Afiliado.instancia(1, LocalDate.of(2018, 6, 20), "000003", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, 5, factoryPlan()));
            return afiliados;
        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (AfiliadoSinPlanException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (PersonaIncompletaException e) {
            e.printStackTrace();
            return new ArrayList<>();
        } catch (PlanIncompletoException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }

    }

    private List<Persona> factoryPersonaMiembros() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException {
        List<Modelo.Persona> personas = new ArrayList<>();
        personas.add(Modelo.Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
        personas.add(Modelo.Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
        personas.add(Modelo.Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
        personas.add(Modelo.Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
        return personas;

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

    public Persona factoryPersonaTitular() throws DniConPuntosException, PersonaIncompletaException, NumeroAfiliadoIncorrectoException {
        return Persona.instancia(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumento(1, "DNI"),
                "30672405", new Sangre(1, "A", "RH+"), "3825674978", new ObraSocial(1, "ASDA"), "", factoryAntecedenteMedico(), 0);

    }

}
