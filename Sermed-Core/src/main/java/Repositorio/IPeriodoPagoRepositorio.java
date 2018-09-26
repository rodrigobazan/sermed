package Repositorio;

import Modelo.PeriodoPago;

import java.util.Collection;

public interface IPeriodoPagoRepositorio {

    Collection<PeriodoPago> findAll();

    Collection<PeriodoPago> findByAnio(int anio);

    Collection<PeriodoPago> findByMes(int mes);

    PeriodoPago findByMesAnio(int mes, int anio);

    Collection<PeriodoPago> findIntervalo(int mesDesde, int mesHasta, int anio);
}
