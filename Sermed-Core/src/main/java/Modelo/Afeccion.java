package Modelo;

public class Afeccion {
    private Integer idAfeccion;
    private String nombreAfeccion;

    public Afeccion(Integer idAfeccion, String nombreAfeccion) {
        this.idAfeccion = idAfeccion;
        this.nombreAfeccion = nombreAfeccion;
    }

    public Integer getIdAfeccion() {
        return idAfeccion;
    }

    public void setIdAfeccion(int idAfeccion) {
        this.idAfeccion = idAfeccion;
    }

    public String getNombreAfeccion() {
        return nombreAfeccion;
    }

    public void setNombreAfeccion(String nombreAfeccion) {
        this.nombreAfeccion = nombreAfeccion;
    }
}
