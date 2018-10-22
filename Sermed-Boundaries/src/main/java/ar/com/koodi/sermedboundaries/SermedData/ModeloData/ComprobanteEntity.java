package ar.com.koodi.sermedboundaries.SermedData.ModeloData;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;

@Entity(name = "comprobante")
@SequenceGenerator(name="comprobante_idcomprobante_seq", initialValue = 1, sequenceName = "comprobante_idcomprobante_seq", allocationSize = 1)
public class ComprobanteEntity {

    @Id
    @Column(name = "idcomprobante", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comprobante_idcomprobante_seq")
    private Integer idComprobante;

    @Column(name = "numerocomprobante")
    private String numeroComprobante;

    @ManyToOne
    @JoinColumn(name = "afiliado", referencedColumnName = "idafiliado")
    private AfiliadoEntity afiliado;

    @Column(name = "total")
    private Double total;

    @Column(name="fechacreacion")
    private LocalDate fechaCreacion;

    @Column(name = "mododepago")
    private String modoDePago;

    @Column(name = "activo")
    private boolean activo;

    @ManyToMany(cascade = CascadeType.ALL)
    private Collection<PeriodoPagoEntity> periodosAbonados;

    public ComprobanteEntity(String numeroComprobante, AfiliadoEntity afiliado, Double total, LocalDate fechaCreacion, String modoDePago, boolean activo, Collection<PeriodoPagoEntity> periodosAbonados) {
        this.numeroComprobante = numeroComprobante;
        this.afiliado = afiliado;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.modoDePago = modoDePago;
        this.activo = activo;
        this.periodosAbonados = periodosAbonados;
    }

    public ComprobanteEntity() {
    }

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public void setIdComprobante(Integer idComprobante) {
        this.idComprobante = idComprobante;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    public AfiliadoEntity getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(AfiliadoEntity afiliado) {
        this.afiliado = afiliado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getModoDePago() {
        return modoDePago;
    }

    public void setModoDePago(String modoDePago) {
        this.modoDePago = modoDePago;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Collection<PeriodoPagoEntity> getPeriodosAbonados() {
        return periodosAbonados;
    }

    public void setPeriodosAbonados(Collection<PeriodoPagoEntity> periodosAbonados) {
        this.periodosAbonados = periodosAbonados;
    }
}
