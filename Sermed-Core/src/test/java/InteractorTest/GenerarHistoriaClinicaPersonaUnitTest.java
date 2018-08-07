package InteractorTest;

import Excepciones.*;
import Interactor.GenerarHistoriaClinicaPersonaUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import ModeloReporte.HistoriaClinicaPersonaDTO;
import Repositorio.IPersonaRepositorio;
import Repositorio.IVisitaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GenerarHistoriaClinicaPersonaUnitTest {

    @Mock
    IVisitaRepositorio repositorioVisita;
    @Mock
    IPersonaRepositorio repositorioPersona;


    @Test
    public void generarHistoriaClinicaPersona_PersonaExiste_GeneraHistoriaClinica() throws PersonaIncompletaException, PersonaNoExisteException {

        when(repositorioPersona.findByNumeroAfiliado("000001",0)).thenReturn(factoryPersona());

        GenerarHistoriaClinicaPersonaUseCase generarHistoriaClinicaAfiliadoUseCase = new GenerarHistoriaClinicaPersonaUseCase(repositorioPersona, repositorioVisita);
        HistoriaClinicaPersonaDTO historiaClinicaPersona = generarHistoriaClinicaAfiliadoUseCase.generarHistoriaClinicaPersona("000001",0);
        Assertions.assertNotNull(historiaClinicaPersona);

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

    private List<Visita> crearVisitasArray() {
        List<Visita> lasVisitas = new ArrayList<Visita>();
        try {
            Persona elPaciente= factoryPersona();
            Medico medico = new Medico(1, "torres", "geerman", 12015, "as212321");
            Enfermero enfermero = new Enfermero(1, "torres", "geerman", 12015, "as212321");
            Visita visita1 = Visita.instancia(1, 1452, elPaciente, LocalDateTime.of(2018, 8, 7, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita2 = Visita.instancia(2, 1453, elPaciente, LocalDateTime.of(2018, 8, 9, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita3 = Visita.instancia(3, 1454, elPaciente, LocalDateTime.of(2018, 8, 10, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
            Visita visita4 = Visita.instancia(4, 1455, elPaciente, LocalDateTime.of(2018, 8, 12, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);


            lasVisitas.add(visita1);
            lasVisitas.add(visita2);
            lasVisitas.add(visita3);
            lasVisitas.add(visita4);
            return lasVisitas;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            return lasVisitas;
        }
    }

    private Persona factoryPersona() throws PersonaIncompletaException {
        try {
            return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0);
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
            return null;
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
        }

    }

}
