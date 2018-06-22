package Interactor;

import Modelo.Afiliado;
import Repositorio.IAfiliadoRepositorio;

public class CrearAfiliadoUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public CrearAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public boolean crearAfiliado(Afiliado afiliado) {
        if(!validarAfiliadoExiste(afiliado)){
            return repositorioAfiliado.persist(afiliado);
        }
        return false;
    }

    private boolean validarAfiliadoExiste(Afiliado afiliado) {
        if(repositorioAfiliado.findById(afiliado.getIdAfiliado()) != null){
            return true;
        }
        return false;
    }
}
