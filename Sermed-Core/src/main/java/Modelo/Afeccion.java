package Modelo;

public class Afeccion {
    private int idAfeccion;
    private String nombreAfeccion;

    public Afeccion(int idAfeccion, String nombreAfeccion) {
        this.idAfeccion = idAfeccion;
        this.nombreAfeccion = nombreAfeccion;
    }

    public int getIdAfeccion() {
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
