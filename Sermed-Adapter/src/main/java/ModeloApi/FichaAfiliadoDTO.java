package ModeloApi;

import java.time.LocalDate;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FichaAfiliadoDTO {

    @JsonProperty("fechaAfiliacion")
	 public final LocalDate fechaAfiliacion;
    
    @JsonProperty("nroAfiliado")
	 public final String nroAfiliado;
    
    @JsonProperty("titular")
	 public final PersonaDTO titular;
    
    @JsonProperty("miembrosGrupoFamiliar")
     public final Collection<PersonaDTO> miembrosGrupoFamiliar;
    
    @JsonProperty("planAfiliado")
	 public final PlanDTO planAfiliado;
	 
	 
	public FichaAfiliadoDTO(@JsonProperty("fechaAfiliacion")LocalDate fechaAfiliacion, @JsonProperty("nroAfiliado")String nroAfiliado, @JsonProperty("titular")PersonaDTO titular,
			@JsonProperty("miembrosGrupoFamiliar")Collection<PersonaDTO> miembrosGrupoFamiliar, @JsonProperty("planAfiliado")PlanDTO planAfiliado) {
		super();
		this.fechaAfiliacion = fechaAfiliacion;
		this.nroAfiliado = nroAfiliado;
		this.titular = titular;
		this.miembrosGrupoFamiliar = miembrosGrupoFamiliar;
		this.planAfiliado = planAfiliado;
	}

	 
}
