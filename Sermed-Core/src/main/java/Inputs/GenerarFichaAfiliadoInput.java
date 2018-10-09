package Inputs;

import Excepciones.AfiliadoNoExisteException;
import ModeloReporte.FichaAfiliadoDTO;

public interface GenerarFichaAfiliadoInput {

	FichaAfiliadoDTO generarFichaAfiliadoParaReporte(String numeroAfiliado) throws AfiliadoNoExisteException;
}
