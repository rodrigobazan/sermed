package Modelo;

public class Sangre {

    private int idSangre;
    private String grupo;
    private String factor;

    public Sangre(int idSangre, String grupo, String factor) {
        this.idSangre = idSangre;
        this.grupo = grupo;
        this.factor = factor;
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
