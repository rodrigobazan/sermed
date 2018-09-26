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
        return (List<PeriodoPago>) iPeriodoPagoRepositorio.findAll();
    }
}
