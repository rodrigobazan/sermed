package ModeloApi;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlanDTO {

	@JsonProperty("idPlan")
	public final Integer idPlan;
	
	@JsonProperty("nombrePlan")
	public final String nombrePlan;
	
	@JsonProperty("listaPrecios")
	public final HashMap<String, Double> listaPrecios;

	@JsonCreator
	public PlanDTO(@JsonProperty("idPlan") Integer idPlan, @JsonProperty("nombrePlan") String nombrePlan, @JsonProperty("listaPrecios") HashMap<String, Double> listaPrecios) {
		this.idPlan = idPlan;
		this.nombrePlan = nombrePlan;
		this.listaPrecios = listaPrecios;
	}


    public String mostrarPlan() {
		return this.nombrePlan;
    }
}
