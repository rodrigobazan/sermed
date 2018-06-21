package InteractorTest;

import Excepciones.PersonaIncompletaException;
import Interactor.CrearPersonaUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IPersonaRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import sun.rmi.runtime.NewThreadAction;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CrearPersonaUnitTest {

    @Mock
    IPersonaRepositorio repositorioPersona;

    @Test
    public void crearPersona_PersonaNoExiste_GuardarPersona() throws PersonaIncompletaException {
        Persona persona = Persona.instancia(1,"Torres","German Federico Nicolas", LocalDate.of(1982,9,12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1,"B","RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001-00", factoryAntecedenteMedico());

        when(repositorioPersona.findById(1)).thenReturn(null);
        when(repositorioPersona.findByDocumentoAndTipoDocumento("14000001","DNI")).thenReturn(null);
        when(repositorioPersona.persist(any(Persona.class))).thenReturn(true);
        CrearPersonaUseCase crearPersonaUseCase = new CrearPersonaUseCase(repositorioPersona);

        boolean resultado = crearPersonaUseCase.crearPersona(persona);

        Assertions.assertEquals(true, resultado);
        verify(repositorioPersona).persist(any(Persona.class));

    }

    @Test
    public void crearPersona_PersonaExiste_NoGuardarPersona() throws PersonaIncompletaException {
        Persona persona = Persona.instancia(1,"Torres","German Federico Nicolas", LocalDate.of(1982,9,12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1,"B","RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001-00", factoryAntecedenteMedico());

        when(repositorioPersona.findById(1)).thenReturn(factoryPersona());
        when(repositorioPersona.findByDocumentoAndTipoDocumento("14000001","DNI")).thenReturn(factoryPersona());

        CrearPersonaUseCase crearPersonaUseCase = new CrearPersonaUseCase(repositorioPersona);
        boolean resultado = crearPersonaUseCase.crearPersona(persona);

        verify(repositorioPersona, never()).persist(persona);
        Assertions.assertEquals(false, resultado);
    }


    @Test
    public void validarPersona_existePorIDyDocumento_true() throws PersonaIncompletaException {

        Persona persona = Persona.instancia(1,"Torres","German Federico Nicolas", LocalDate.of(1982,9,12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1,"B","RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001-00", factoryAntecedenteMedico());

        when(repositorioPersona.findById(1)).thenReturn(factoryPersona());
        when(repositorioPersona.findByDocumentoAndTipoDocumento("14000001","DNI")).thenReturn(factoryPersona());

        CrearPersonaUseCase crearPersonaUseCase = new CrearPersonaUseCase(repositorioPersona);

        try{
            Method privateMethod = CrearPersonaUseCase.class.getDeclaredMethod("validarPersonaExiste", Persona.class);
            privateMethod.setAccessible(true);

            boolean resultado = (boolean) privateMethod.invoke(crearPersonaUseCase, persona);

            Assertions.assertEquals(true, resultado);
        }catch(Exception e){
            System.out.println("error");
            e.printStackTrace();
        }
    }

    private Collection<AntecedenteMedico> factoryAntecedenteMedico(){
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1,"Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2,new Afeccion(1,"gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3,new Afeccion(1,"diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia,gonorrea,diabetes);

        return  listaAntecedentes;
    }

    private Persona factoryPersona() throws PersonaIncompletaException {
        return Persona.instancia(1,"Torres","German Federico Nicolas", LocalDate.of(1982,9,12),
                "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1,"B","RH+"), "3825672746",
                new ObraSocial(1, "OSFATUN"), "000001-00", factoryAntecedenteMedico());
    }



}
