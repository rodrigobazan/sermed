package Modelo;

public class PeriodoPago {

    private Integer idPeriodoPago;
    private int mes;
    private int anio;

    public PeriodoPago(Integer idPeriodoPago, int mes, int anio) {
        this.idPeriodoPago = idPeriodoPago;
        this.mes = mes;
        this.anio = anio;
    }

    public Integer getIdPeriodoPago() {
        return idPeriodoPago;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public boolean esDePeriodo(int mes, int anio) {
        return this.mes==mes && this.anio==anio;
    }
}
