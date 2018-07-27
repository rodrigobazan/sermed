package ModeloReporte;

import Modelo.Persona;

import java.time.LocalDate;
import java.util.Collection;

public class FichaAfiliadoDTO {

    private LocalDate fechaGeneracion;
    private LocalDate fechaAfiliacion;
    private String nroAfiliado;
    private Persona titular;
    private Collection<Persona> miembrosGrupoFamiliar;

}
