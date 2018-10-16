package Adaptadores;

import Excepciones.ComprobanteNoExisteException;
import Excepciones.FechaIncorrectaException;
import Factorys.ComprobanteFactory;
import Inputs.ConsultarComprobantesInput;
import ModeloApi.ComprobanteDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultarComprobantesAdapter {
    private ConsultarComprobantesInput consultarComprobantesInput;

    public ConsultarComprobantesAdapter(ConsultarComprobantesInput consultarComprobantesInput) {
        this.consultarComprobantesInput = consultarComprobantesInput;
    }

    public List<ComprobanteDTO> consultarComprobantes() {
        List<ComprobanteDTO> comprobanteDTOS = new ArrayList<>();
        consultarComprobantesInput.consultarComprobantes().forEach(comprobante -> comprobanteDTOS.add(ComprobanteFactory.mapeoCoreDTO(comprobante)));
        return comprobanteDTOS;
    }

    public ComprobanteDTO consultarComprobantePorNumero(String numeroComprobante) throws ComprobanteNoExisteException {
        return ComprobanteFactory.mapeoCoreDTO(consultarComprobantesInput.consultarComprobantePorNumero(numeroComprobante));
    }

    public List<ComprobanteDTO> consultarComprobantesPorFechas(LocalDate fechaDesde, LocalDate fechaHasta) throws FechaIncorrectaException {
        List<ComprobanteDTO> comprobantes = new ArrayList<>();
        consultarComprobantesInput.consultarComprobantesPorFechas(fechaDesde, fechaHasta).forEach(comprobante -> comprobantes.add(ComprobanteFactory.mapeoCoreDTO(comprobante)));
        return comprobantes;
    }
}
