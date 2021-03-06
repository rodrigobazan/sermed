package Modelo;

import Excepciones.AfiliadoDeBajaException;
import Excepciones.ComprobanteIncompletoException;
import Excepciones.FechaIncorrectaException;
import Excepciones.NumeroComprobanteIncorrectoException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class Comprobante {

    private Integer idComprobante;
    private String numeroComprobante;
    private Afiliado afiliado;
    private double total;
    private LocalDate fechaCreacion;
    private String modoDePago;
    private boolean activo;
    private List<PeriodoPago> periodosAbonados;

    public Comprobante(Integer idComprobante, String numeroComprobante, Afiliado afiliado, double total, LocalDate fechaCreacion, String modoDePago, boolean activo, List<PeriodoPago> periodosAbonados) {
        this.idComprobante = idComprobante;
        this.numeroComprobante = numeroComprobante;
        this.afiliado = afiliado;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.modoDePago = modoDePago;
        this.activo = activo;
        this.periodosAbonados = periodosAbonados;
    }

    public static Comprobante instancia(Integer idComprobante, String numeroComprobante, Afiliado afiliado, double total, LocalDate fechaCreacion, String modoDePago, boolean activo, Collection<PeriodoPago> periodosAbonados) throws ComprobanteIncompletoException, AfiliadoDeBajaException, FechaIncorrectaException, NumeroComprobanteIncorrectoException {
        if (numeroComprobante.equals("") || afiliado == null || total == 0.0 || fechaCreacion == null || modoDePago.equals("") || periodosAbonados.isEmpty()) {
            throw new ComprobanteIncompletoException();
        }
        if (numeroComprobanteIncorrecto(numeroComprobante)) throw new NumeroComprobanteIncorrectoException();

        if (!afiliado.afiliadoEstaActivo()) throw new AfiliadoDeBajaException();
        if (fechaCreacion.isAfter(LocalDate.now()) && !fechaCreacion.equals(LocalDate.now()))
            throw new FechaIncorrectaException();
        return new Comprobante(idComprobante, numeroComprobante, afiliado, total, fechaCreacion, modoDePago, activo, (List<PeriodoPago>) periodosAbonados);
    }

    private static boolean numeroComprobanteIncorrecto(String numeroComprobante) {
        if (numeroComprobante.length() != 11) return true;
        String[] arrayNumeroComprobante = numeroComprobante.split("-");
        if (arrayNumeroComprobante.length != 2) return true;
        if (arrayNumeroComprobante[0].length() != 4) return true;
        if (arrayNumeroComprobante[1].length() != 6) return true;

        return false;
    }

    public Integer getIdComprobante() {
        return idComprobante;
    }

    public Afiliado getAfiliado() {
        return afiliado;
    }

    public String getNumero() {
        return this.numeroComprobante;
    }

    public boolean getActivo() {
        return this.activo;
    }

    public void anularComprobante() {
        this.activo = false;
    }

    public LocalDate getFechaCreacion() {
        return this.fechaCreacion;
    }

    public String nombreApellidoTitularAfiliado() {
        return this.afiliado.nombreApellidoTitular();
    }

    public String domicilioTitularAfiliado() {
        return this.afiliado.domicilioTitular();
    }

    public Double getTotal() {
        return this.total;
    }

    public String getModoPago() {
        return this.modoDePago;
    }

    public List<PeriodoPago> getPeriodosAbonados() {
        return periodosAbonados;
    }

    public boolean contienePeriodo(int mes, int anio) {
        return periodosAbonados.stream().anyMatch(periodoPago -> periodoPago.esDePeriodo(mes, anio));
    }
}
