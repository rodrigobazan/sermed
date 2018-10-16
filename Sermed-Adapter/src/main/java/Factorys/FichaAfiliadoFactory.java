package Factorys;

import java.util.ArrayList;
import java.util.List;

import Modelo.Persona;
import ModeloApi.FichaAfiliadoDTO;
import ModeloApi.PersonaDTO;

public class FichaAfiliadoFactory {

	public FichaAfiliadoFactory() {		
	}

	public static FichaAfiliadoDTO mapeoCoreDTO(ModeloReporte.FichaAfiliadoDTO fichaReporte) {
		List<Persona> grupoFamiliar= (List<Persona>) fichaReporte.getMiembrosGrupoFamiliar();
		List<PersonaDTO> grupoFamiliarDTO = new ArrayList<>();
		if(!grupoFamiliar.isEmpty()) {
			grupoFamiliar.stream().forEach(persona->{
				grupoFamiliarDTO.add(PersonaFactory.mapeoCoreDTO(persona));
			});
		}
		return new FichaAfiliadoDTO(fichaReporte.getFechaAfiliacion(), fichaReporte.getNroAfiliado(), PersonaFactory.mapeoCoreDTO(fichaReporte.getTitular()), 
				grupoFamiliarDTO, PlanFactory.mapeoCoreDTO(fichaReporte.getPlanAfiliado()));
	}

	
}
