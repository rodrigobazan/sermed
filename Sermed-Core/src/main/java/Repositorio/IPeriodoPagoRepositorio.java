package Repositorio;

import Modelo.PeriodoPago;

import java.util.Collection;

public interface IPeriodoPagoRepositorio {

    Collection<PeriodoPago> findAll();
}
