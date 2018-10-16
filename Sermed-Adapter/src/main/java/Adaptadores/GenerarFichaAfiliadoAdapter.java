package Adaptadores;

import Excepciones.AfiliadoNoExisteException;
import Factorys.FichaAfiliadoFactory;
import Inputs.GenerarFichaAfiliadoInput;
import ModeloApi.FichaAfiliadoDTO;

public class GenerarFichaAfiliadoAdapter {

	private GenerarFichaAfiliadoInput generarFichaAfiliadoInput;

	public GenerarFichaAfiliadoAdapter(GenerarFichaAfiliadoInput generarFichaAfiliadoInput) {
		super();
		this.generarFichaAfiliadoInput = generarFichaAfiliadoInput;
	}

	public FichaAfiliadoDTO generarFichaAfiliado(String numeroAfiliado) throws AfiliadoNoExisteException {
		return FichaAfiliadoFactory.mapeoCoreDTO(generarFichaAfiliadoInput.generarFichaAfiliadoParaReporte(numeroAfiliado));
	}
	
	
}
