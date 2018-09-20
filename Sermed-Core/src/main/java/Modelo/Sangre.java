package Modelo;

import Excepciones.SangreIncompletoException;

public class Sangre {

    private int idSangre;
    private String grupo;
    private String factor;

    public Sangre(Integer idSangre, String grupo, String factor) {
        this.idSangre = idSangre;
        this.grupo = grupo;
        this.factor = factor;
    }

    public static Sangre instacia(Integer idSangre, String grupo, String factor) throws SangreIncompletoException {
        if(grupo == null || factor == null){
            throw new SangreIncompletoException();
        }
        return new Sangre(idSangre,grupo,factor);
    }

    public int getIdSangre() {
        return idSangre;
    }

    public void setIdSangre(int idSangre) {
        this.idSangre = idSangre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }
}
