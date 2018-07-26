package Modelo;

import Excepciones.AfiliadoSinTitularException;
import Excepciones.NumeroAfiliadoIncorrectoException;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public class Afiliado {
    private Integer idAfiliado;
    private LocalDate fechaAfiliacion;
    private String numeroAfiliado;
    private Collection<Persona> miembros;
    private Persona titular;
    private boolean activo;
    private LocalDate fechaDeBaja;
    private LocalDate fechaPagoAcordada;

    private Afiliado(Integer idAfiliado, LocalDate fechaAfiliacion, String numeroAfiliado, Collection<Persona> miembros, boolean activo, LocalDate fechaDeBaja, LocalDate fechaPagoAcordada) {

        this.idAfiliado = idAfiliado;
        this.fechaAfiliacion = fechaAfiliacion;
        this.numeroAfiliado = numeroAfiliado;
        this.miembros = miembros;
        this.activo = activo;
        this.fechaDeBaja = fechaDeBaja;
        this.fechaPagoAcordada = fechaPagoAcordada;
    }

    public Integer getIdAfiliado() {
        return this.idAfiliado;
    }


    public static Afiliado instancia(Integer idAfiliado, LocalDate fechaAfiliacion, String numeroAfiliado, Persona titular, Collection<Persona> miembros, boolean activo, LocalDate fechaDeBaja, LocalDate fechaPagoAcordada) throws AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException {
        if (titular == null) {
            throw new AfiliadoSinTitularException();
        }
        if (numeroAfiliado.length() != 6)
            throw new NumeroAfiliadoIncorrectoException();

        Afiliado elNuevo = new Afiliado(idAfiliado, fechaAfiliacion, numeroAfiliado, miembros, activo, fechaDeBaja, fechaPagoAcordada);
        elNuevo.asignarTitular(titular);
        return elNuevo;

    }

    public void asignarTitular(Persona titular) {
        this.titular = titular;
        this.titular.setNumeroAfiliado(this.numeroAfiliado);
        this.titular.setNroOrden(0);
    }

    public String getNumeroAfiliado() {
        return this.numeroAfiliado;
    }

    public Persona getTitular() {
        return this.titular;
    }

    public String mostrarAfiliado() {
        return this.numeroAfiliado + ". Titular: " + this.titular.mostrarTitular();
    }

    public boolean contienePersona(Persona laPersona) {
        return laPersona.obtenerDocumentoCompleto().equals(this.titular.obtenerDocumentoCompleto()) || this.miembros.stream().anyMatch(a -> a.obtenerDocumentoCompleto().equals(laPersona.obtenerDocumentoCompleto()));
    }

    public void agregarPersona(Persona persona) {
        persona.setNumeroAfiliado(this.numeroAfiliado);
        persona.setNroOrden(this.miembros.stream().max(Comparator.comparingInt(Persona::getNroOrden)).get().getNroOrden() + 1);

        this.miembros.add(persona);
    }

    public boolean quitarPersona(Persona persona) {
        Persona personaAQuitar = this.getMiembros().stream().filter(m -> m.obtenerDocumentoCompleto().equals(persona.obtenerDocumentoCompleto())).findAny().orElse(null);
        if (personaAQuitar != null) {
            this.getMiembros().remove(personaAQuitar);
            return true;
        }
        return false;

    }

    public List<Persona> getMiembros() {
        return (List<Persona>) this.miembros;
    }


    public boolean getActivo() {
        return this.activo;
    }

    public Persona devolverPersona(Persona laPersona) {
        if (laPersona.obtenerDocumentoCompleto().equals(this.titular.obtenerDocumentoCompleto())) {
            return this.titular;
        }
        return this.miembros.stream().filter(a -> a.obtenerDocumentoCompleto().equals(laPersona.obtenerDocumentoCompleto())).findAny().orElse(null);
    }
}
