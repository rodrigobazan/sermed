package ar.com.koodi.sermedservices.Login;

public class ApiResponse {

    private Boolean correcto;
    private String mensaje;

    public ApiResponse(Boolean correcto, String mensaje) {
        this.correcto = correcto;
        this.mensaje = mensaje;
    }

    public Boolean getCorrecto() {
        return correcto;
    }

    public void setCorrecto(Boolean correcto) {
        this.correcto = correcto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
