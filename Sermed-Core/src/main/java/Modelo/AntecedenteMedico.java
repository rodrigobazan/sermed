package Modelo;

public class AntecedenteMedico {

    private int idAntecedenteMedico;
    private Afeccion afeccion;
    private String observacion;

    public AntecedenteMedico(int idAntecedenteMedico, Afeccion afeccion, String observacion) {
        this.idAntecedenteMedico = idAntecedenteMedico;
        this.afeccion = afeccion;
        this.observacion = observacion;
    }

    public AntecedenteMedico() {
    }

    public int getIdAntecedenteMedico() {
        return idAntecedenteMedico;
    }

    public void setIdAntecedenteMedico(int idAntecedenteMedico) {
        this.idAntecedenteMedico = idAntecedenteMedico;
    }

    public Afeccion getAfeccion() {
        return afeccion;
    }

    public void setAfeccion(Afeccion afeccion) {
        this.afeccion = afeccion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
