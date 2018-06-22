package InteractorTest;

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
import java.util.Arrays;
import java.util.Collection;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearAfiliadoUnitTest {

    @Mock
    IAfiliadoRepositorio repositorioAfiliado;

    @Test
    public void crearAfiliado_AfiliadoNoExiste_GuardaAfiliado() throws PersonaIncompletaException {
        Afiliado afiliado = new Afiliado(1, LocalDate.of(2018,6,15), "190000-02", factoryPersona(), true);
        when(repositorioAfiliado.findById(1)).thenReturn(null);
        when(repositorioAfiliado.persist(afiliado)).thenReturn(true);
        CrearAfiliadoUseCase crearAfiliadoUseCase = new CrearAfiliadoUseCase(repositorioAfiliado);
        boolean resultado = crearAfiliadoUseCase.crearAfiliado(afiliado);
        Assertions.assertEquals(true, resultado);

    }

    @Test
    public void crearAfiliado_AfiliadoExiste_NoGuardaAfiliado() throws PersonaIncompletaException {
        Afiliado afiliado = new Afiliado(1, LocalDate.of(2018,6,15), "190000-02", factoryPersona(), true);
        when(repositorioAfiliado.findById(1)).thenReturn(new Afiliado(1, LocalDate.of(2018,6,15), "190000-02", factoryPersona(), true));
        when(repositorioAfiliado.persist(afiliado)).thenReturn(false);
        CrearAfiliadoUseCase crearAfiliadoUseCase = new CrearAfiliadoUseCase(repositorioAfiliado);
        boolean resultado = crearAfiliadoUseCase.crearAfiliado(afiliado);
        Assertions.assertEquals(false, resultado);

    }



    public Persona factoryPersona() throws PersonaIncompletaException {
        return Persona.instancia(1, "Ruitti", "Javiel", LocalDate.of(1984,1,31),"25 de mayo",new TipoDocumento(1, "DNI"),
                "30672405",new Sangre(1,"A","RH+"),"3825674978", new ObraSocial(1,"ASDA"), "190000-01",factoryAntecedenteMedico());
    }

    private Collection<AntecedenteMedico> factoryAntecedenteMedico(){
        AntecedenteMedico dislexia = new AntecedenteMedico(1, new Afeccion(1,"Dislexia"), "Cronica");
        AntecedenteMedico gonorrea = new AntecedenteMedico(2,new Afeccion(1,"gonorrea"), "Cronica Tambien");
        AntecedenteMedico diabetes = new AntecedenteMedico(3,new Afeccion(1,"diabetes"), "nerviosa");

        Collection<AntecedenteMedico> listaAntecedentes = Arrays.asList(dislexia,gonorrea,diabetes);

        return  listaAntecedentes;
    }

}
