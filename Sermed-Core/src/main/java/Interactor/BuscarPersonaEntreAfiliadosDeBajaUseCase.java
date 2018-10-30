package Interactor;

import Inputs.BuscarPersonaEntreAfiliadosDeBajaInput;
import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;

public class BuscarPersonaEntreAfiliadosDeBajaUseCase implements BuscarPersonaEntreAfiliadosDeBajaInput {
    private IAfiliadoRepositorio repositorioAfiliado;

    public BuscarPersonaEntreAfiliadosDeBajaUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    
	@Override
	public boolean existePersona(Persona persona) {
		return repositorioAfiliado.findAllInactivos().stream().anyMatch(a -> a.contienePersona(persona));
	}
}
