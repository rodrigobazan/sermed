package Interactor;

import Inputs.ConsultarPeriodoPagoInput;
import Modelo.PeriodoPago;
import Repositorio.IPeriodoPagoRepositorio;

import java.util.List;

public class ConsultarPeriodoPagoUseCase implements ConsultarPeriodoPagoInput {

    private IPeriodoPagoRepositorio iPeriodoPagoRepositorio;

    public ConsultarPeriodoPagoUseCase(IPeriodoPagoRepositorio iPeriodoPagoRepositorio) {
        this.iPeriodoPagoRepositorio = iPeriodoPagoRepositorio;
    }

    @Override
    public List<PeriodoPago> consultarPeriodoPago() {
        return (List<PeriodoPago>) this.iPeriodoPagoRepositorio.findAll();
    }

    @Override
    public List<PeriodoPago> consultarPeriodoPagoPorAnio(int anio) {
        return (List<PeriodoPago>) this.iPeriodoPagoRepositorio.findByAnio(anio);
    }

    @Override
    public List<PeriodoPago> consultarPeriodoPagoPorMes(int mes) {
        return (List<PeriodoPago>) this.iPeriodoPagoRepositorio.findByMes(mes);
    }

    @Override
    public PeriodoPago consultarPeriodoPagoPorMesAnio(int mes, int anio) {
        return this.iPeriodoPagoRepositorio.findByMesAnio(mes,anio);
    }

    @Override
    public List<PeriodoPago> consultarPeriodoPagoPorIntervalo(int mesDesde, int mesHasta, int anio) {
        return (List<PeriodoPago>) this.iPeriodoPagoRepositorio.findIntervalo(mesDesde, mesHasta, anio);
    }
}
