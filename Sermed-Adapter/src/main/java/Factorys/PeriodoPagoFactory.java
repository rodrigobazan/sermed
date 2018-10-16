package Factorys;

import Modelo.PeriodoPago;
import ModeloApi.PeriodoPagoDTO;

public class PeriodoPagoFactory {

    public PeriodoPagoFactory() {
    }

    public static PeriodoPagoDTO maperoCoreDTO(PeriodoPago periodoPago) {
        return new PeriodoPagoDTO(periodoPago.getIdPeriodoPago(), periodoPago.getMes(), periodoPago.getAnio());
    }

    public static PeriodoPago mapeoDTOCore(PeriodoPagoDTO periodoPagoDTO){
        return  new PeriodoPago(PeriodoPagoDTO.idPeriodo, PeriodoPagoDTO.mes, PeriodoPagoDTO.anio);
    }
}
