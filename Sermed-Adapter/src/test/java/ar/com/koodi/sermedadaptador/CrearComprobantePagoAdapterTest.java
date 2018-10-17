package ar.com.koodi.sermedadaptador;

import Adaptadores.CrearComprobantePagoAdapter;
import Excepciones.*;
import Inputs.CrearComprobanteDePagoInput;
import Mockito.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import Modelo.*;
import ModeloApi.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.*;

@ExtendWith(MockitoExtension.class)
public class CrearComprobantePagoAdapterTest {

 @Mock
    private CrearComprobanteDePagoInput crearComprobanteDePagoInput;

 @Test
    public void crearComprobanteDePago_guardaCorrectamente_DevuelveTrue() throws ComprobanteExisteException, PersonaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException {
     ComprobanteDTO comprobanteDTO = factoryComprobante() ;
     CrearComprobantePagoAdapter crearComprobantePagoAdapter = new CrearComprobantePagoAdapter(crearComprobanteDePagoInput);
     when(crearComprobanteDePagoInput.crearComprobante(any(Comprobante.class))).thenReturn(true);
     boolean resultado = crearComprobantePagoAdapter.crearComprobantePago(comprobanteDTO);
     Assertions.assertTrue(resultado);

 }

    public ComprobanteDTO factoryComprobante(){
        return new ComprobanteDTO(1,"123",factoryAfiliado(),1500,LocalDate.of(2017,7,4),"contado",true,listaDePeriodosDePago());
    }

    private List<PeriodoPagoDTO> listaDePeriodosDePago(){
        List<PeriodoPagoDTO> periodosPago = new ArrayList<>();
        periodosPago.add(new PeriodoPagoDTO(1,2,2018));
        periodosPago.add(new PeriodoPagoDTO(2,3,2018));
        periodosPago.add(new PeriodoPagoDTO(3,4,2018));
        return periodosPago;
    }

    public AfiliadoDTO factoryAfiliado() {
        try {
            return new AfiliadoDTO(1,LocalDate.of(2018,5,1),"000001",factoryPersonaMiembros(),factoryPersonaTitular(),true,null,12,factoryPlan());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public PersonaDTO factoryPersonaTitular() {
        try {
            return new PersonaDTO(1, "Ruitti", "Javiel", LocalDate.of(1984, 1, 31), "25 de mayo", new TipoDocumentoDTO(1, "DNI"),
                    "30672405", new SangreDTO(1, "A", "RH+"), "3825674978", new ObraSocialDTO(1, "ASDA"), "",1 , factoryAntecedenteMedico());
        } catch (Exception e){
            return null;
        }

    }

    private List<PersonaDTO> factoryPersonaMiembros() {
        try {
            List<PersonaDTO> personas = new ArrayList<>();
            personas.add( new PersonaDTO(1, "Torres", "German Federico Nicolas", LocalDate.of(1982, 9, 12),
                    "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "14000001", new SangreDTO(1, "B", "RH+"), "3825672746",
                    new ObraSocialDTO(1, "OSFATUN"), "190000",1 , factoryAntecedenteMedico()));
            personas.add(new PersonaDTO(1, "Bazan", "Rodrigo Andres", LocalDate.of(1993, 5, 12),
                    "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "34215324", new SangreDTO(1, "B", "RH-"), "3825532112",
                    new ObraSocialDTO(1, "OSFATUN"), "190000",2, factoryAntecedenteMedico()));
            personas.add(new PersonaDTO(1, "Vega", "Romina del Valle de Antinaco", LocalDate.of(1987, 3, 12),
                    "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "33166401", new SangreDTO(1, "0", "RH+"), "3825423547",
                    new ObraSocialDTO(1, "OSFATUN"), "190000",3 , factoryAntecedenteMedico()));
            personas.add(new PersonaDTO(1, "Flores", "Eduardo Heriberto", LocalDate.of(1991, 11, 12),
                    "Sin Domicilio", new TipoDocumentoDTO(1, "DNI"), "32123457", new SangreDTO(1, "A", "RH+"), "382584521",
                    new ObraSocialDTO(1, "OSFATUN"), "190000", 4, factoryAntecedenteMedico()));
            return personas;
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    private Collection<AntecedenteMedicoDTO> factoryAntecedenteMedico() {
        AntecedenteMedicoDTO dislexia = new AntecedenteMedicoDTO(1, new AfeccionDTO(1, "Dislexia"), "Cronica");
        AntecedenteMedicoDTO gonorrea = new AntecedenteMedicoDTO(2, new AfeccionDTO(1, "gonorrea"), "Cronica Tambien");
        AntecedenteMedicoDTO diabetes = new AntecedenteMedicoDTO(3, new AfeccionDTO(1, "diabetes"), "nerviosa");

        Collection<AntecedenteMedicoDTO> listaAntecedentes = Arrays.asList(dislexia, gonorrea, diabetes);

        return listaAntecedentes;
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

        return  new PlanDTO(1, "Plan Basico", listaPrecios);
    }

}
