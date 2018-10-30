package InteractorTest;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Excepciones.PersonaNoExisteException;
import Interactor.ConsultarPersonaUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IPersonaRepositorio;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConsultarPersonaUnitTest {

    @Mock
    IPersonaRepositorio repositorioPersona;

    @Spy
    List<Persona> listaPersonas = crearPersonasArray();


    @Test
    public void consultarPersonas_ExistenDatos_ColeccionConDatos() {
        when(repositorioPersona.findAll()).thenReturn(listaPersonas);

        ConsultarPersonaUseCase consultarPersonaUseCase = new ConsultarPersonaUseCase(repositorioPersona);
        List<Persona> personas = consultarPersonaUseCase.consultarPersonas();

        assertThat(personas, not(IsEmptyCollection.empty()));
        assertEquals(personas.size(), listaPersonas.size());
        verify(repositorioPersona).findAll();

    }

    @Test
    public void consultarPersonas_NoExistenDatos_ColeccionVacia() {
        when(repositorioPersona.findAll()).thenReturn(new ArrayList<>());

        ConsultarPersonaUseCase consultarPersonaUseCase = new ConsultarPersonaUseCase(repositorioPersona);
        List<Persona> personas = consultarPersonaUseCase.consultarPersonas();

        assertThat(personas, IsEmptyCollection.empty());
    }

    @Test
    public void consultarPersonasPorApellido_CriterioCadenaVacia_DevolverTodos(){
        when(repositorioPersona.findByApellido("")).thenReturn(listaPersonas);

        ConsultarPersonaUseCase consultarPersonaUseCase = new ConsultarPersonaUseCase(repositorioPersona);

        List<Persona> personas = consultarPersonaUseCase.consultarPersonasPorApellido("");

        assertEquals(listaPersonas, personas);
        assertThat(personas, not(IsEmptyCollection.empty()));
    }

    @Test void consultarPersonasPorApellido_CriterioCadenaConDatos_DevolverAlgunos(){
        when(repositorioPersona.findByApellido("torr")).thenReturn(crearPersonaFiltroApellidoArray());

        ConsultarPersonaUseCase consultarPersonaUseCase = new ConsultarPersonaUseCase(repositorioPersona);

        List<Persona> personas = consultarPersonaUseCase.consultarPersonasPorApellido("torr");

        assertThat(personas, hasSize(2));

    }



    @Test
    void consultarPersonaNumeroAfiliado_NumeroExiste_RetornaPersona() throws PersonaIncompletaException, PersonaNoExisteException {
        when(repositorioPersona.findByNumeroAfiliado("000001",0)).thenReturn(factoryPersona());

        ConsultarPersonaUseCase consultarPersonaUseCase=new ConsultarPersonaUseCase(repositorioPersona);
        Persona personaBuscada=consultarPersonaUseCase.consultarPersonaPorNumeroAfiliado("000001",0);

        Assertions.assertEquals(1,personaBuscada.getIdPersona().intValue());

    }

    @Test
    void consultarPersonaNumeroAfiliado_NumeroNoExiste_PersonaNoExisteException()
    {
        ConsultarPersonaUseCase consultarPersonaUseCase=new ConsultarPersonaUseCase(repositorioPersona);
        Assertions.assertThrows(PersonaNoExisteException.class,()->consultarPersonaUseCase.consultarPersonaPorNumeroAfiliado("999999",0));
    }


    private List<Persona> crearPersonasArray(){
        List<Persona> lasPersonas=new ArrayList<Persona>();
        try {
            lasPersonas.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            lasPersonas.add(Persona.instancia(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1, "B", "RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            lasPersonas.add(Persona.instancia(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1, "0", "RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            lasPersonas.add(Persona.instancia(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            return lasPersonas;
        }
        catch (Exception ex) {
        }
        finally {
            return lasPersonas;
        }
    }

    private Collection<Persona> crearPersonaFiltroApellidoArray() {
        List<Persona> lasPersonas=new ArrayList<Persona>();
        try {
            lasPersonas.add(Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            lasPersonas.add(Persona.instancia(1, "Torresson", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1, "A", "RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(), 0));
            return lasPersonas;
        }
        catch (Exception ex) {
        }
        finally {
            return lasPersonas;
        }
    }


    private Collection<AntecedenteMedico> factoryAntecedenteMedico() {
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1, "Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2, new Afeccion(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3, new Afeccion(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
    }

    private Persona factoryPersona() throws PersonaIncompletaException {
        try {
            return Persona.instancia(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1, "B", "RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "000001", factoryAntecedenteMedico(), 0);
        } catch (DniConPuntosException e) {
            e.printStackTrace();
            return null;
        }

    }

}
