package ModeloReporte;

import Modelo.Afiliado;
import Modelo.Persona;
import Modelo.Plan;

import java.time.LocalDate;
import java.util.Collection;

public class FichaAfiliadoDTO {

    private LocalDate fechaAfiliacion;
    private String nroAfiliado;
    private Persona titular;
    private Collection<Persona> miembrosGrupoFamiliar;
    private Plan planAfiliado;

    public void armarFicha(Afiliado afiliado) {

        fechaAfiliacion=afiliado.getFechaAfiliacion();
        nroAfiliado=afiliado.getNumeroAfiliado();
        titular=afiliado.getTitular();
        miembrosGrupoFamiliar=afiliado.getMiembros();
        planAfiliado=afiliado.getPlan();

    }   

	public FichaAfiliadoDTO(LocalDate fechaAfiliacion, String nroAfiliado, Persona titular,
			Collection<Persona> miembrosGrupoFamiliar, Plan planAfiliado) {
		super();
		this.fechaAfiliacion = fechaAfiliacion;
		this.nroAfiliado = nroAfiliado;
		this.titular = titular;
		this.miembrosGrupoFamiliar = miembrosGrupoFamiliar;
		this.planAfiliado = planAfiliado;
	}

	public LocalDate getFechaAfiliacion() {
		return fechaAfiliacion;
	}

	public String getNroAfiliado() {
		return nroAfiliado;
	}

	public Persona getTitular() {
		return titular;
	}

	public Collection<Persona> getMiembrosGrupoFamiliar() {
		return miembrosGrupoFamiliar;
	}

	public Plan getPlanAfiliado() {
		return planAfiliado;
	}
    
    
}
