package ar.com.koodi.sermedadaptador;

import Adaptadores.AfiliadPersonaAdapter;
import Excepciones.*;
import Inputs.AfiliarPersonaInput;
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

@ExtendWith(MockitoExtension.class)
public class AfiliarPersonaAdapterTest {

    @Mock
    AfiliarPersonaInput afiliarPersonaInput;

    @Test
    public void afiliarPersona_PersonaYaAfiliada_PersonaAfiliadaException() throws PlanIncompletoException, PersonaIncompletaException {
        PersonaDTO persona = new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001", 0, null);
        AfiliadoDTO afiliado = new AfiliadoDTO(1, LocalDate.of(2018, 6, 27), "000003", factoryPersonaMiembros(), factoryPersona(), true, null, null, factoryPlan());
        AfiliadPersonaAdapter afiliadPersonaAdapter = new AfiliadPersonaAdapter(afiliarPersonaInput);
        Assertions.assertThrows(PersonaAfiliadaException.class, () -> afiliadPersonaAdapter.afiliadPersona(persona, afiliado));
    }


    private PersonaDTO factoryPersona() throws PersonaIncompletaException {
        return new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "000001", 0, null);

    }

    private List<PersonaDTO> factoryPersonaMiembros() {

        List<PersonaDTO> personasDTO = new ArrayList<>();
        personasDTO.add(new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0,null));
        personasDTO.add(new PersonaDTO(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "34215324", new SangreDTO(1, "B", "RH-"), "3825532112",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0,null));
        personasDTO.add(new PersonaDTO(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "33166401", new SangreDTO(1, "0", "RH+"), "3825423547",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0,null));
        personasDTO.add(new PersonaDTO(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "32123457", new SangreDTO(1, "A", "RH+"), "382584521",
                new ObraSocialDTO(1, "OSFATUN"), "190000", 0, null));
        return personasDTO;
    }

    private PlanDTO factoryPlan() throws PlanIncompletoException {
        HashMap<String, Double> listaPrecios = new HashMap<>();
        listaPrecios.put("1", (double) 380);
        listaPrecios.put("2", (double) 480);
        listaPrecios.put("3", (double) 550);
        listaPrecios.put("4", (double) 600);
        listaPrecios.put("5", (double) 650);
        listaPrecios.put("6", (double) 700);
        listaPrecios.put("7", (double) 750);

        return new PlanDTO(1,"Plan Basico",listaPrecios);
    }
}
