package Interactor;

import Inputs.BuscarPersonaEntreAfiliadosInput;
import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;

public class BuscarPersonaEntreAfiliadosUseCase implements BuscarPersonaEntreAfiliadosInput {
    private IAfiliadoRepositorio repositorioAfiliado;

    public BuscarPersonaEntreAfiliadosUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

	@Override
	public boolean existePersonaPorDNI(Persona persona) {
		return repositorioAfiliado.findAllActivos().stream().anyMatch(a -> a.contienePersona(persona));
	}
}
