package Adaptadores;

import Excepciones.*;
import Factorys.AfiliadoFactory;
import Factorys.ComprobanteFactory;
import Inputs.ConsultarComprobanteDeAfiliadoInput;
import ModeloApi.AfiliadoDTO;
import ModeloApi.ComprobanteDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultarComprobanteDeAfiliadoAdapter {
    private ConsultarComprobanteDeAfiliadoInput consultarComprobanteDeAfiliadoInput;

    public ConsultarComprobanteDeAfiliadoAdapter(ConsultarComprobanteDeAfiliadoInput consultarComprobanteDeAfiliadoInput) {
        this.consultarComprobanteDeAfiliadoInput = consultarComprobanteDeAfiliadoInput;
    }


    public List<ComprobanteDTO> consultarTodosLosComprobantes(AfiliadoDTO afiliadoDTO) throws PersonaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException {
        List<ComprobanteDTO> comprobantes = new ArrayList<>();
        consultarComprobanteDeAfiliadoInput.consultarTodosLosComprobantes(AfiliadoFactory.mapeoDTOCore(afiliadoDTO)).forEach(comprobante -> comprobantes.add(ComprobanteFactory.mapeoCoreDTO(comprobante)));
        return comprobantes;
    }

    public List<ComprobanteDTO> consultarComprobantesAfiliadoPorFechas(AfiliadoDTO afiliado, LocalDate fechaDesde, LocalDate fechaHasta) throws PersonaIncompletaException, PlanIncompletoException, AfiliadoSinPlanException, NumeroAfiliadoIncorrectoException, AfiliadoSinTitularException, DniConPuntosException, FechaIncorrectaException {
        List<ComprobanteDTO> comprobantes = new ArrayList<>();
        consultarComprobanteDeAfiliadoInput.consultarComprobantesAfiliadoPorFechas(AfiliadoFactory.mapeoDTOCore(afiliado), fechaDesde, fechaHasta).forEach(comprobante ->
                comprobantes.add(ComprobanteFactory.mapeoCoreDTO(comprobante)));
        return comprobantes;
    }
}
