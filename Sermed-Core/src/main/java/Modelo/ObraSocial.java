package Modelo;

public class ObraSocial {
    private Integer idObraSocial;
    private String obraSocial;

    public ObraSocial(Integer idObraSocial, String obraSocial) {
        this.idObraSocial = idObraSocial;
        this.obraSocial = obraSocial;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }
}
