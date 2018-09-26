package Modelo;

public class PeriodoPago {

    private int idPeriodoPago;
    private int mes;
    private int anio;

    public PeriodoPago(int idPeriodoPago, int mes, int anio) {
        this.idPeriodoPago = idPeriodoPago;
        this.mes = mes;
        this.anio = anio;
    }

    public boolean esDePeriodo(int mes, int anio) {
        return this.mes==mes && this.anio==anio;
    }
}
