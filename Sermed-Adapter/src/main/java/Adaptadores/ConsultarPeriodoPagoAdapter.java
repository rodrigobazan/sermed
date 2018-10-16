package Adaptadores;

import Factorys.PeriodoPagoFactory;
import Inputs.ConsultarPeriodoPagoInput;
import ModeloApi.PeriodoPagoDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultarPeriodoPagoAdapter {
    private ConsultarPeriodoPagoInput consultarPeriodoPagoInput;

    public ConsultarPeriodoPagoAdapter(ConsultarPeriodoPagoInput consultarPeriodoPagoInput) {
        this.consultarPeriodoPagoInput = consultarPeriodoPagoInput;
    }

    public List<PeriodoPagoDTO> consultarPeriodoPago() {
        List<PeriodoPagoDTO> periodoPagoDTOList = new ArrayList<>();
        consultarPeriodoPagoInput.consultarPeriodoPago().forEach(periodoPago -> periodoPagoDTOList.add(PeriodoPagoFactory.maperoCoreDTO(periodoPago)));
        return periodoPagoDTOList;
    }

    public List<PeriodoPagoDTO> consultarPeriodoPagoPorAnio(int anio){
        List<PeriodoPagoDTO> periodoPagoDTOList = new ArrayList<>();
        consultarPeriodoPagoInput.consultarPeriodoPagoPorAnio(anio).forEach(periodoPago -> periodoPagoDTOList.add(PeriodoPagoFactory.maperoCoreDTO(periodoPago)));
        return periodoPagoDTOList;
    }

    public List<PeriodoPagoDTO> consultarPeriodoPagoPorMes(int mes){
        List<PeriodoPagoDTO> periodoPagoDTOList = new ArrayList<>();
        consultarPeriodoPagoInput.consultarPeriodoPagoPorMes(mes).forEach(periodoPago -> periodoPagoDTOList.add(PeriodoPagoFactory.maperoCoreDTO(periodoPago)));
        return periodoPagoDTOList;
    }

    public PeriodoPagoDTO consultarPeriodoPagoPorMesAnio(int mes, int anio){
        return PeriodoPagoFactory.maperoCoreDTO(consultarPeriodoPagoInput.consultarPeriodoPagoPorMesAnio(mes, anio));
    }

    public List<PeriodoPagoDTO> consultarPeriodoPagoPorIntervalo(int desde, int hasta, int anio){
        List<PeriodoPagoDTO> periodoPagoDTOList = new ArrayList<>();
        consultarPeriodoPagoInput.consultarPeriodoPagoPorIntervalo(desde,hasta,anio).forEach(periodoPago -> periodoPagoDTOList.add(PeriodoPagoFactory.maperoCoreDTO(periodoPago)));
        return periodoPagoDTOList;
    }

}
