package Modelo;

public class PeriodoPago {

    private int mes;
    private int anio;

    public PeriodoPago(int mes, int anio) {
        this.mes = mes;
        this.anio = anio;
    }

    public boolean esDePeriodo(int mes, int anio) {
        return this.mes==mes && this.anio==anio;
    }
}
