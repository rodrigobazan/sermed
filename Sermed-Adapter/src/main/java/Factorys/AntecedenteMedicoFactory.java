package Factorys;

import Modelo.AntecedenteMedico;
import ModeloApi.AntecedenteMedicoDTO;

import java.util.ArrayList;
import java.util.Collection;

public class AntecedenteMedicoFactory {

    private AntecedenteMedicoFactory() {
    }

    public static Collection<AntecedenteMedico> mapeoDTOCore(Collection<AntecedenteMedicoDTO> antecedentesMedico) {
        Collection<AntecedenteMedico> listaDeAntecedenteMedicos = new ArrayList<>();
        antecedentesMedico.forEach(antecedenteMedico -> {
            AntecedenteMedico a = new AntecedenteMedico(antecedenteMedico.idAntecedenteMedico, AfeccionFactory.mapeoDTOCore(antecedenteMedico.afeccion), antecedenteMedico.observacion);
            listaDeAntecedenteMedicos.add(a);
        });
        return listaDeAntecedenteMedicos;
    }

	public static Collection<AntecedenteMedicoDTO> mapeoCoreDTO(Collection<AntecedenteMedico> antecedentesMedico) {
		Collection<AntecedenteMedicoDTO> listaDeAntecedenteMedicos = new ArrayList<>();
        antecedentesMedico.forEach(antecedenteMedico -> {
        	AntecedenteMedicoDTO a = new AntecedenteMedicoDTO(antecedenteMedico.getIdAntecedenteMedico(), AfeccionFactory.mapeoCoreDTO(antecedenteMedico.getAfeccion()), antecedenteMedico.getObservacion());
            listaDeAntecedenteMedicos.add(a);
        });
        return listaDeAntecedenteMedicos;
	}
}
