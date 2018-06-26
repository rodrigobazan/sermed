package Modelo;

import Excepciones.AfiliadoSinTitularException;
import Excepciones.NumeroAfiliadoIncorrectoException;

import java.time.LocalDate;
import java.util.Collection;

public class Afiliado {
    private Integer idAfiliado;
    private LocalDate fechaAfiliacion;
    private String numeroAfiliado;
    private Collection<Persona> miembros;
    private Persona titular;
    private boolean activo;

    private Afiliado(Integer idAfiliado, LocalDate fechaAfiliacion, String numeroAfiliado, Persona titular, Collection<Persona> miembros, boolean activo) {

        this.idAfiliado = idAfiliado;
        this.fechaAfiliacion = fechaAfiliacion;
        this.numeroAfiliado = numeroAfiliado;
        this.miembros = miembros;
        this.titular = titular;
        this.activo = activo;
    }

    public Integer getIdAfiliado() {
        return this.idAfiliado;
    }


    public static Afiliado instancia(Integer idAfiliado, LocalDate fechaAfiliacion, String numeroAfiliado, Persona titular, Collection<Persona> miembros, boolean activo) throws AfiliadoSinTitularException, NumeroAfiliadoIncorrectoException {
        if (titular == null) {
            throw new AfiliadoSinTitularException();
        }
        if(numeroAfiliado.length() != 6)
            throw new NumeroAfiliadoIncorrectoException();

        return new Afiliado(idAfiliado, fechaAfiliacion, numeroAfiliado, titular, miembros, activo);

    }


}
