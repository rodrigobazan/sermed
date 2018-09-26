package Interactor;

import Modelo.PeriodoPago;
import Repositorio.IPeriodoPagoRepositorio;

import java.util.List;

public class ConsultarPeriodoPagoUseCase {

    private IPeriodoPagoRepositorio iPeriodoPagoRepositorio;

    public ConsultarPeriodoPagoUseCase(IPeriodoPagoRepositorio iPeriodoPagoRepositorio) {
        this.iPeriodoPagoRepositorio = iPeriodoPagoRepositorio;
    }

    public List<PeriodoPago> consultarPeriodoPago() {
        return (List<PeriodoPago>) this.iPeriodoPagoRepositorio.findAll();
    }

    public List<PeriodoPago> consultarPeriodoPagoPorAnio(int anio) {
        return (List<PeriodoPago>) this.iPeriodoPagoRepositorio.findByAnio(anio);
    }

    public List<PeriodoPago> consultarPeriodoPagoPorMes(int mes) {
        return (List<PeriodoPago>) this.iPeriodoPagoRepositorio.findByMes(mes);
    }

    public PeriodoPago consultarPeriodoPagoPorMesAnio(int mes, int anio) {
        return this.iPeriodoPagoRepositorio.findByMesAnio(mes,anio);
    }

    public List<PeriodoPago> consultarPeriodoPagoPorIntervalo(int mesDesde, int mesHasta, int anio) {
        return (List<PeriodoPago>) this.iPeriodoPagoRepositorio.findIntervalo(mesDesde, mesHasta, anio);
    }
}
