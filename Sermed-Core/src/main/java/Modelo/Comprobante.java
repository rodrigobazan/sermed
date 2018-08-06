package Modelo;

import Excepciones.AfiliadoDeBajaException;
import Excepciones.ComprobanteIncompletoException;
import Excepciones.FechaIncorrectaException;

import java.time.LocalDate;

public class Comprobante {

    private Integer idComprobante;
    private String numeroComprobante;
    private Afiliado afiliado;
    private double total;
    private LocalDate fechaCreacion;
    private String modoDePago;
    private boolean activo;

    public Comprobante(Integer idComprobante, String numeroComprobante, Afiliado afiliado, double total, LocalDate fechaCreacion, String modoDePago, boolean activo) {
        this.idComprobante = idComprobante;
        this.numeroComprobante = numeroComprobante;
        this.afiliado = afiliado;
        this.total = total;
        this.fechaCreacion = fechaCreacion;
        this.modoDePago = modoDePago;
        this.activo = activo;
    }

    public static Comprobante instancia(Integer idComprobante, String numeroComprobante, Afiliado afiliado, double total, LocalDate fechaCreacion, String modoDePago, boolean activo) throws ComprobanteIncompletoException, AfiliadoDeBajaException, FechaIncorrectaException {
        if(numeroComprobante.equals("") || afiliado == null || total == 0.0 || fechaCreacion == null || modoDePago.equals("")){
            throw new ComprobanteIncompletoException();
        }
        if(!afiliado.afiliadoEstaActivo()) throw new AfiliadoDeBajaException();
        if(fechaCreacion.isAfter(LocalDate.now()) && !fechaCreacion.equals(LocalDate.now())) throw new FechaIncorrectaException();
        return new Comprobante(idComprobante, numeroComprobante, afiliado, total, fechaCreacion, modoDePago, activo);
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
}
