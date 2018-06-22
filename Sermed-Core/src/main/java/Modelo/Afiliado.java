package Modelo;

import Excepciones.AfiliadoSinPersonaException;
import Excepciones.NumeroAfiliadoIncorrectoException;

import java.time.LocalDate;

public class Afiliado {
    private Integer idAfiliado;
    private LocalDate fechaAfiliacion;
    private String numeroAfiliado;
    private Persona persona;
    private boolean activo;

    public Afiliado(Integer idAfiliado, LocalDate fechaAfiliacion, String numeroAfiliado, Persona persona, boolean activo) {

        this.idAfiliado = idAfiliado;
        this.fechaAfiliacion = fechaAfiliacion;
        this.numeroAfiliado = numeroAfiliado;
        this.persona = persona;
        this.activo = activo;
    }

    public Integer getIdAfiliado() {
        return this.idAfiliado;
    }


    public static Afiliado instancia(Integer idAfiliado, LocalDate fechaAfiliacion, String numeroAfiliado, Persona persona, boolean activo) throws AfiliadoSinPersonaException, NumeroAfiliadoIncorrectoException {
        if (persona == null) {
            throw new AfiliadoSinPersonaException();
        }
        if(!numeroAfiliado.contains("-"))
            throw new NumeroAfiliadoIncorrectoException();

        String[] array = numeroAfiliado.split("-");
        if(array[0].length() != 6 || array[1].length() != 2)
            throw new NumeroAfiliadoIncorrectoException();

        return new Afiliado(idAfiliado, fechaAfiliacion, numeroAfiliado, persona, activo);

    }


}
