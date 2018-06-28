package InteractorTest;

import Excepciones.AfiliadoSinTitularException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Interactor.CrearAfiliadoUseCase;
import Mockito.MockitoExtension;
import Modelo.*;
import Repositorio.IAfiliadoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearAfiliadoUnitTest {

    @Mock
    IAfiliadoRepositorio repositorioAfiliado;

    @Test
    public void crearAfiliado_AfiliadoNoExiste_GuardaAfiliado() throws NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException {
        Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018,6,15), "190000", factoryPersonaTitular(), factoryPersonaMiembros(), true);
        when(repositorioAfiliado.findById(1)).thenReturn(null);
        when(repositorioAfiliado.persist(afiliado)).thenReturn(true);
        CrearAfiliadoUseCase crearAfiliadoUseCase = new CrearAfiliadoUseCase(repositorioAfiliado);
        boolean resultado = crearAfiliadoUseCase.crearAfiliado(afiliado);
        Assertions.assertEquals(true, resultado);

    }

    @Test
    public void crearAfiliado_AfiliadoExiste_NoGuardaAfiliado(){
        try {
            Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018,6,15), "190000", factoryPersonaTitular(),factoryPersonaMiembros(), true);
            when(repositorioAfiliado.findById(1)).thenReturn(Afiliado.instancia(1, LocalDate.of(2018,6,15), "190000", factoryPersonaTitular(),factoryPersonaMiembros(), true));
            when(repositorioAfiliado.persist(afiliado)).thenReturn(false);
            CrearAfiliadoUseCase crearAfiliadoUseCase = new CrearAfiliadoUseCase(repositorioAfiliado);
            boolean resultado = crearAfiliadoUseCase.crearAfiliado(afiliado);
            Assertions.assertEquals(false, resultado);
        }catch (AfiliadoSinTitularException ex){
            ex.printStackTrace();
        }catch (NumeroAfiliadoIncorrectoException e){
            e.printStackTrace();
        }
    }


    @Test
    public void crearAfiliado_TitularConDistintoNumero_NoGuardaAfiliado(){
        try {
            Afiliado afiliado = Afiliado.instancia(1, LocalDate.of(2018,6,15), "190002", factoryPersonaTitular(), factoryPersonaMiembros(), true);
            when(repositorioAfiliado.findById(1)).thenReturn(null);
            when(repositorioAfiliado.persist(afiliado)).thenReturn(false);
            CrearAfiliadoUseCase crearAfiliadoUseCase = new CrearAfiliadoUseCase(repositorioAfiliado);
            boolean resultado = crearAfiliadoUseCase.crearAfiliado(afiliado);
            Assertions.assertNotEquals(afiliado.getNumeroAfiliado(),afiliado.getTitular().getNumeroAfiliado().split("-")[0]);
            Assertions.assertEquals(false, resultado);
        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
        }

    }



    public Persona factoryPersonaTitular() {
        try {
            return Persona.instancia(1, "Ruitti", "Javiel", LocalDate.of(1984,1,31),"25 de mayo",new TipoDocumento(1, "DNI"),
                    "30672405",new Sangre(1,"A","RH+"),"3825674978", new ObraSocial(1,"ASDA"), "190000",factoryAntecedenteMedico(),0);
        }catch (PersonaIncompletaException e){
            e.printStackTrace();
            return null;
        }catch (NumeroAfiliadoIncorrectoException ex){
            ex.printStackTrace();
            return null;
        }

    }

    private List<Persona> factoryPersonaMiembros(){
        try {
            List<Persona> personas = new ArrayList<>();
            personas.add(Persona.instancia(1,"Torres","German Federico Nicolas", LocalDate.of(1982,9,12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "14000001", new Sangre(1,"B","RH+"), "3825672746",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(),0));
            personas.add(Persona.instancia(1,"Bazan","Rodrigo Andres", LocalDate.of(1993,5,12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "34215324", new Sangre(1,"B","RH-"), "3825532112",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(),0));
            personas.add(Persona.instancia(1,"Vega","Romina del Valle de Antinaco", LocalDate.of(1987,3,12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "33166401", new Sangre(1,"0","RH+"), "3825423547",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(),0));
            personas.add(Persona.instancia(1,"Flores","Eduardo Heriberto", LocalDate.of(1991,11,12),
                    "Sin Domicilio", new TipoDocumento(1, "DNI"), "32123457", new Sangre(1,"A","RH+"), "382584521",
                    new ObraSocial(1, "OSFATUN"), "190000", factoryAntecedenteMedico(),0));
            return personas;
        }catch (PersonaIncompletaException e){
            e.printStackTrace();
            return new ArrayList<>();
        }catch (NumeroAfiliadoIncorrectoException ex){
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    private Collection<AntecedenteMedico> factoryAntecedenteMedico(){
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1,"Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2,new Afeccion(1,"gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3,new Afeccion(1,"diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia,gonorrea,diabetes);

        return  listaAntecedentes;
    }

}
