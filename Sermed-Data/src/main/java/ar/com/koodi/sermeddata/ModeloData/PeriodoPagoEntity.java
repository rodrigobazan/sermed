package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.*;

@Entity(name = "periodopago")
@SequenceGenerator(name="periodopago_idperiodopago_seq", initialValue = 1, sequenceName = "periodopago_idperiodopago_seq", allocationSize = 1)
public class PeriodoPagoEntity {
    
    @Id
    @Column(name = "idperiodopago", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "periodopago_idperiodopago_seq")
    private Integer idPeriodoPago;
    
    @Column(name = "mes")
    private int mes;

    @Column(name = "anio")
    private int anio;

    public PeriodoPagoEntity(int mes, int anio) {
        this.mes = mes;
        this.anio = anio;
    }

    public PeriodoPagoEntity() {
    }

    public Integer getIdPeriodoPago() {
        return idPeriodoPago;
    }

    public void setIdPeriodoPago(Integer idPeriodoPago) {
        this.idPeriodoPago = idPeriodoPago;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
