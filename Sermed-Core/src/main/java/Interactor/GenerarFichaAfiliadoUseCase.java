package Interactor;

import Modelo.Afiliado;
import ModeloReporte.FichaAfiliadoDTO;
import Repositorio.IAfiliadoRepositorio;

public class GenerarFichaAfiliadoUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public GenerarFichaAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    /*public FichaAfiliadoDTO generarFichaAfiliadoParaReporte(Afiliado afiliado) {
    }*/
}
