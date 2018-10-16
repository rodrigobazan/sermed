package ar.com.koodi.sermedadaptador;

import Adaptadores.DarBajaAfiliadoAdapter;
import Excepciones.*;
import Factorys.AfiliadoFactory;
import Inputs.DarBajaAfiliadoInput;
import Mockito.MockitoExtension;
import Modelo.Afiliado;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DarBajaAfiliadoAdapterTest {

    @Mock
    private DarBajaAfiliadoInput darBajaAfiliadoInput;

    @Test
    public void darBajaAfiliado_AfiliadoYaTieneLaBaja_AfiliadoDeBajaException() throws AfiliadoDeBajaException, PersonaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException {
        AfiliadoDTO afiliado = new AfiliadoDTO(1, LocalDate.of(2018, 6, 27), "000003", factoryPersonaMiembros(), factoryPersona(), false, null, null, factoryPlan());
        when(darBajaAfiliadoInput.darBajaAfiliado(any(Afiliado.class), eq(LocalDate.of(2018, 6, 27)))).thenThrow(AfiliadoDeBajaException.class);
        DarBajaAfiliadoAdapter darBajaAfiliadoAdapter = new DarBajaAfiliadoAdapter(darBajaAfiliadoInput);
        Assertions.assertThrows(AfiliadoDeBajaException.class, ()->darBajaAfiliadoAdapter.darBajaAfiliado(afiliado, LocalDate.of(2018, 6, 27)));
    }

    @Test
    public void darBajaAfiliado_AfiliadoEstaActivo_SeDaDeBaja() throws PersonaIncompletaException, PlanIncompletoException, AfiliadoDeBajaException, DniConPuntosException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, AfiliadoSinPlanException {
        AfiliadoDTO afiliado = new AfiliadoDTO(1, LocalDate.of(2018, 6, 27), "000003", factoryPersonaMiembros(), factoryPersona(), false, null, null, factoryPlan());
        when(darBajaAfiliadoInput.darBajaAfiliado(any(Afiliado.class), eq(LocalDate.of(2018, 6, 27)))).thenReturn(true);
        DarBajaAfiliadoAdapter darBajaAfiliadoAdapter = new DarBajaAfiliadoAdapter(darBajaAfiliadoInput);
        boolean resultado = darBajaAfiliadoAdapter.darBajaAfiliado(afiliado, LocalDate.of(2018, 6, 27));
        Assertions.assertTrue(resultado);
    }

    private PersonaDTO factoryPersona() {
        return new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001", 0, null);
    }

    private List<PersonaDTO> factoryPersonaMiembros() {
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

    private PlanDTO factoryPlan(){
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 380);
        listaPrecios.put("2", (double) 480);
        listaPrecios.put("3", (double) 550);
        listaPrecios.put("4", (double) 600);
        listaPrecios.put("5", (double) 650);
        listaPrecios.put("6", (double) 700);
        listaPrecios.put("7", (double) 750);

        return new PlanDTO(1, "Plan Basico", listaPrecios);
    }


}
