package ar.com.koodi.sermedadaptador;

import Adaptadores.CambiarPlanAfiliadoAdapter;
import Excepciones.*;
import Inputs.CambiarPlanAfiliadoInput;
import Mockito.MockitoExtension;
import Modelo.*;
import ModeloApi.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CambiarPlanAfiliadoAdapterTest {

    @Mock
    CambiarPlanAfiliadoInput cambiarPlanAfiliadoInput;

    @Test
    public void cambiarPlan_PlanDistinto_CambiarPlan() throws PlanIncompletoException, PersonaIncompletaException, PlanesIgualesException, AfiliadoSinPlanException, UpdateAfiliadoException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException {
        PlanDTO planNuevo = new PlanDTO(2, "Plan Nuevo", listaPrecios());
        AfiliadoDTO afiliadoAModificar = new AfiliadoDTO(1, LocalDate.of(2018, 6, 27), "000003", factoryPersonaMiembrosDTO(), factoryPersonaDTO(), true, null, null, factoryPlanDTO());
        when(cambiarPlanAfiliadoInput.modificarPlanAfiliado(any(Afiliado.class), any(Plan.class))).thenReturn(Afiliado.instancia(1, LocalDate.of(2018, 6, 27), "000003", factoryPersona(), factoryPersonaMiembros(), true, null, null, factoryPlan()));
        CambiarPlanAfiliadoAdapter cambiarPlanAfiliadoAdapter = new CambiarPlanAfiliadoAdapter(cambiarPlanAfiliadoInput);
        AfiliadoDTO afiliadoModificado = cambiarPlanAfiliadoAdapter.modificarPlanAfiliado(afiliadoAModificar, planNuevo);
        Assertions.assertEquals(afiliadoModificado.mostrarPlanAfiliado(), planNuevo.mostrarPlan());
    }

    @Test
    public void cambiarPlan_PlanIgual_PlanesIgualesException() throws PlanesIgualesException, UpdateAfiliadoException {
        PlanDTO planNuevo = new PlanDTO(2, "Plan Basico", listaPrecios());
        AfiliadoDTO afiliadoAModificar = new AfiliadoDTO(1, LocalDate.of(2018, 6, 27), "000003", factoryPersonaMiembrosDTO(), factoryPersonaDTO(), true, null, null, factoryPlanDTO());
        when(cambiarPlanAfiliadoInput.modificarPlanAfiliado(any(Afiliado.class), any(Plan.class))).thenThrow(PlanesIgualesException.class);
        CambiarPlanAfiliadoAdapter cambiarPlanAfiliadoAdapter = new CambiarPlanAfiliadoAdapter(cambiarPlanAfiliadoInput);
        Assertions.assertThrows(PlanesIgualesException.class, () -> cambiarPlanAfiliadoAdapter.modificarPlanAfiliado(afiliadoAModificar, planNuevo));

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

        return Plan.instancia(1,"Plan Nuevo",listaPrecios);
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
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Persona factoryPersona() throws PersonaIncompletaException {
        try {
            return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", null, 0);
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
        }

    }

    private PersonaDTO factoryPersonaDTO() {
        return new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001", 0, null);
    }

    private PlanDTO factoryPlanDTO() {
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 380);
        listaPrecios.put("2", (double) 480);
        listaPrecios.put("3", (double) 550);
        listaPrecios.put("4", (double) 600);
        listaPrecios.put("5", (double) 650);
        listaPrecios.put("6", (double) 700);

        return new PlanDTO(1, "Plan Basico", listaPrecios);
    }

    private HashMap<String, Double> listaPrecios() {
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 480);
        listaPrecios.put("2", (double) 580);
        listaPrecios.put("3", (double) 650);
        listaPrecios.put("4", (double) 700);
        listaPrecios.put("5", (double) 850);
        listaPrecios.put("6", (double) 900);

        return listaPrecios;
    }


    private List<PersonaDTO> factoryPersonaMiembrosDTO() {
        List<PersonaDTO> personas = new ArrayList<>();
        personas.add(new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0, null));
        personas.add(new PersonaDTO(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "34215324", new SangreDTO(1, "B", "RH-"), "3825532112",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0, null));
        personas.add(new PersonaDTO(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "33166401", new SangreDTO(1, "0", "RH+"), "3825423547",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0, null));
        personas.add(new PersonaDTO(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "32123457", new SangreDTO(1, "A", "RH+"), "382584521",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0, null));
        return personas;
    }
}
