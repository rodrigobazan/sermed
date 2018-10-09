package Interactor;

import Inputs.BuscarPersonaEntreAfiliadosDeBajaInput;
import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;

public class BuscarPersonaEntreAfiliadosUseCase implements BuscarPersonaEntreAfiliadosDeBajaInput {
    private IAfiliadoRepositorio repositorioAfiliado;

    public BuscarPersonaEntreAfiliadosUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    @Override
    public boolean existePersona(Persona laPersona) {
        return repositorioAfiliado.findAllActivos().stream().anyMatch(a -> a.contienePersona(laPersona));
    }
}
