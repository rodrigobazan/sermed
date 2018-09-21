package InteractorTest;

import Excepciones.*;
import Interactor.ConsultarVisitasDePersonaUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IVisitaRepositorio;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarVisitaPersonaUnitTest {

    @Mock
    IVisitaRepositorio repositorioVisita;

    @Test
    public void consultarVisitasDePersona_TieneVisitas_DevuelveColeccionConDatos() throws PlanIncompletoException {
        Persona persona = null;
        persona = factoryPersonaTitular();
        when(repositorioVisita.findByPersona(persona)).thenReturn(crearVisitasArray());
        ConsultarVisitasDePersonaUseCase consultarVisitaDePersonaUseCase = new ConsultarVisitasDePersonaUseCase(repositorioVisita);
        List<Visita> VisitasDelPersona = consultarVisitaDePersonaUseCase.consultarVisitasPersona(persona);
        Assertions.assertEquals(4,VisitasDelPersona.size());
        assertThat(VisitasDelPersona, not(IsEmptyCollection.empty()));

    }

    @Test
    public void consultarVisitasDePersona_NoTieneVisitas_DevuelveColeccionVacia(){
        Persona persona = factoryPersonaTitular();
        when(repositorioVisita.findByPersona(persona)).thenReturn(new ArrayList<>());
        ConsultarVisitasDePersonaUseCase consultarVisitaDePersonaUseCase = new ConsultarVisitasDePersonaUseCase(repositorioVisita);
        List<Visita> VisitasDelPersona = consultarVisitaDePersonaUseCase.consultarVisitasPersona(persona);
        Assertions.assertEquals(0,VisitasDelPersona.size());
        assertThat(VisitasDelPersona, IsEmptyCollection.empty());

    }

    @Test
    public void consultarVisitasDePersonaPorFechas_TieneVisitas_DevuelveColeccionConDatos(){
        Persona persona = factoryPersonaTitular();
        when(repositorioVisita.findByPersona(persona)).thenReturn(crearVisitasArray());
        LocalDate fechaDesde = LocalDate.of(2018, 8, 9);
        LocalDate fechaHasta = LocalDate.of(2018, 8, 12);
        ConsultarVisitasDePersonaUseCase consultarVisitaDePersonaUseCase = new ConsultarVisitasDePersonaUseCase(repositorioVisita);
        List<Visita> visitasDelPersona = consultarVisitaDePersonaUseCase.consultarVisitasPersonaPorFechas(persona, fechaDesde, fechaHasta);
        Assertions.assertEquals(3,visitasDelPersona.size());
        assertThat(visitasDelPersona, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarVisitasDePersonaPorFechas_NoTieneVisitas_DevuelveColeccionVacia(){
        Persona persona = factoryPersonaTitular();
        when(repositorioVisita.findByPersona(persona)).thenReturn(crearVisitasArray());
        LocalDate fechaDesde = LocalDate.of(2018, 8, 20);
        LocalDate fechaHasta = LocalDate.of(2018, 8, 25);
        ConsultarVisitasDePersonaUseCase consultarVisitaDePersonaUseCase = new ConsultarVisitasDePersonaUseCase(repositorioVisita);
        List<Visita> VisitasDelPersona = consultarVisitaDePersonaUseCase.consultarVisitasPersonaPorFechas(persona, fechaDesde, fechaHasta);
        Assertions.assertEquals(0,VisitasDelPersona.size());
        assertThat(VisitasDelPersona, IsEmptyCollection.empty());
    }

    private List<Visita> crearVisitasArray() {
        List<Visita> lasVisitas = new ArrayList<Visita>();
        try {
            Persona elPaciente= factoryPersonaTitular();
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
    private Visita factoryVisita() {
        try{
            Persona elPaciente= factoryPersonaTitular();
            Medico medico = new Medico(1, "torres", "geerman", 12015, "as212321");
            Enfermero enfermero = new Enfermero(1, "torres", "geerman", 12015, "as212321");
            return Visita.instancia(1, 1452, elPaciente, LocalDateTime.of(2018, 8, 7, 10, 1), "Dolores columna", "Espina bifida", "110/80", 36.5f, 45, 82, "Analgesicos", "Reposo", "Estres", "Fisioterapia", "ninguna", medico, enfermero);
        }catch (Exception s){
            return null;
        }

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
        } catch (NumeroAfiliadoIncorrectoException e) {
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


}
