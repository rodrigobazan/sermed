package Inputs;

import Modelo.PeriodoPago;

import java.util.Collection;

public interface ConsultarPeriodoPagoInput {

    Collection<PeriodoPago> consultarPeriodoPago();

    Collection<PeriodoPago> consultarPeriodoPagoPorAnio(int anio);

    Collection<PeriodoPago> consultarPeriodoPagoPorMes(int mes);

    PeriodoPago consultarPeriodoPagoPorMesAnio(int mes, int anio);

    Collection<PeriodoPago> consultarPeriodoPagoPorIntervalo(int mesDesde, int mesHasta, int anio);
}
