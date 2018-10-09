package Interactor;

import Inputs.BuscarPersonaEntreAfiliadosInput;
import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;

public class BuscarPersonaEntreAfiliadosDeBajaUseCase implements BuscarPersonaEntreAfiliadosInput {
    private IAfiliadoRepositorio repositorioAfiliado;

    public BuscarPersonaEntreAfiliadosDeBajaUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    @Override
    public boolean existePersonaPorDNI(Persona laPersona) {
        return repositorioAfiliado.findAllInactivos().stream().anyMatch(a -> a.contienePersona(laPersona));
    }
}
