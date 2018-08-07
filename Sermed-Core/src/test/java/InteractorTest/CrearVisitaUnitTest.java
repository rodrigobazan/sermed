package InteractorTest;

import Excepciones.*;
import Interactor.CrearVisitaUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IVisitaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.security.PublicKey;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class CrearVisitaUnitTest {

    @Mock
    IVisitaRepositorio repositorioVisita;

    @Test
    public void CrearVisita_visitaNoExiste_GuardarVisita() throws PlanIncompletoException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException, VisitaIncompletaException {
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), true, null, null, factoryPlan());
        Medico medico = new Medico(1,"torres","geerman",12015,"as212321");
        Enfermero enfermero = new Enfermero(1,"torres","geerman",12015,"as212321");
        Visita visita = Visita.instancia(1, 1452, afiliado, LocalDateTime.of(2018,8,7,10,1),"Dolores columna", "Espina bifida", "110/80",36.5f,45,82,"Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero  );

        when(repositorioVisita.persist(visita)).thenReturn(true);

        CrearVisitaUseCase crearVisitaUseCase =new CrearVisitaUseCase(repositorioVisita);
        boolean resultado= crearVisitaUseCase.crearVisita(visita);


        Assertions.assertTrue(resultado);
    }

    @Test
    public void InstanciarVisita_visitaIncompleta_visitaIncompletaExceptions() throws VisitaIncompletaException, PlanIncompletoException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
        Medico medico = new Medico(1,"torres","geerman",12015,"as212321");

        Assertions.assertThrows(VisitaIncompletaException.class,()-> Visita.instancia(1, 1452, null , null ,"Dolores columna", "Espina bifida", "110/80",36.5f,45,82,"Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, null));
    }
    @Test
    public void CrearVisita_afiliadoDeBaja_NoCreaVisita() throws VisitaIncompletaException, PlanIncompletoException, AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException, AfiliadoSinPlanException {
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018, 6, 15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), false, LocalDate.of(2018,5,15), null, factoryPlan());
        Medico medico = new Medico(1,"torres","geerman",12015,"as212321");
        Enfermero enfermero = new Enfermero(1,"torres","geerman",12015,"as212321");
        Visita visita = Visita.instancia(1, 1452, afiliado, LocalDateTime.of(2018,8,7,10,1),"Dolores columna", "Espina bifida", "110/80",36.5f,45,82,"Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero  );

        CrearVisitaUseCase crearVisitaUseCase = new CrearVisitaUseCase(repositorioVisita);
        boolean resultado = crearVisitaUseCase.crearVisita(visita);

        Assertions.assertFalse(resultado);
    }

    @Test





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