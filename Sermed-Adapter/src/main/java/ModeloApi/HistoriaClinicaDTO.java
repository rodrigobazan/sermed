package ModeloApi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HistoriaClinicaDTO {
	
	@JsonProperty("persona")
	public final PersonaDTO persona;
	
	@JsonProperty("visitas")
	public final List<VisitaDTO> visitas;
	
	@JsonProperty("antecedentesMedicos")
	public final List<AntecedenteMedicoDTO> antecedentesMedicos;
	
	public HistoriaClinicaDTO(@JsonProperty("persona") PersonaDTO persona, @JsonProperty("visitas") List<VisitaDTO> visitas,
			@JsonProperty("antecedentesMedicos")List<AntecedenteMedicoDTO>antecedentesMedicos) {
		super();
		this.persona = persona;
		this.visitas = visitas;
		this.antecedentesMedicos = antecedentesMedicos;
	}

	
}
