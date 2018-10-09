package Interactor;

import Excepciones.AfiliadoNoExisteException;
import Inputs.GenerarFichaAfiliadoInput;
import Modelo.Afiliado;
import ModeloReporte.FichaAfiliadoDTO;
import Repositorio.IAfiliadoRepositorio;

public class GenerarFichaAfiliadoUseCase implements GenerarFichaAfiliadoInput{
    private IAfiliadoRepositorio repositorioAfiliado;

    public GenerarFichaAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public FichaAfiliadoDTO generarFichaAfiliadoParaReporte(String numeroAfiliado) throws AfiliadoNoExisteException {
        Afiliado afiliado = repositorioAfiliado.findUnicoByNumero(numeroAfiliado);
        if(afiliado != null){
            FichaAfiliadoDTO fichaGenerada=new FichaAfiliadoDTO();
            fichaGenerada.armarFicha(afiliado);
            return fichaGenerada;
        }
        throw new AfiliadoNoExisteException();

    }


}
