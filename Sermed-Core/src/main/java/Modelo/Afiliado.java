package Modelo;

import Excepciones.AfiliadoSinTitularException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import com.sun.deploy.util.StringUtils;

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

    private Afiliado(Integer idAfiliado, LocalDate fechaAfiliacion, String numeroAfiliado, Collection<Persona> miembros, boolean activo) {

        this.idAfiliado = idAfiliado;
        this.fechaAfiliacion = fechaAfiliacion;
        this.numeroAfiliado = numeroAfiliado;
        this.miembros = miembros;
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

        Afiliado elNuevo=new Afiliado(idAfiliado, fechaAfiliacion, numeroAfiliado,miembros, activo);
        elNuevo.asignarTitular(titular);
        return elNuevo;

    }

    private void asignarTitular(Persona titular) {
        this.titular=titular;
        this.titular.setNroOrden(0);
    }

    public String getNumeroAfiliado() {
        return this.numeroAfiliado;
    }

    public Persona getTitular() {
        return this.titular;
    }

    public String mostrarAfiliado(){
        return this.numeroAfiliado +". Titular: "+ this.titular.mostrarTitular();
    }

    public boolean contienePersona(Persona laPersona) {
        return laPersona.getDocumento().equals(this.titular.getDocumento()) || this.miembros.stream().anyMatch(a -> a.getDocumento().equals(laPersona.getDocumento()));
    }

    public void agregarPersona(Persona persona) {
        persona.setNumeroAfiliado(this.numeroAfiliado);
        persona.setNroOrden(this.miembros.stream().max(Comparator.comparingInt(Persona::getNroOrden)).get().getNroOrden()+1);

        this.miembros.add(persona);
    }

    public List<Persona> getMiembros() {
        return (List<Persona>) this.miembros;
    }
}
