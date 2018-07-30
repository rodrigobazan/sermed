package Interactor;

import Modelo.Afiliado;
import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;

public class AfiliarPersonaUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public AfiliarPersonaUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public boolean afiliarPersona(Persona persona, Afiliado afiliado) {
        BuscarPersonaEntreAfiliadosUseCase buscarPersonaEntreAfiliadosUseCase = new BuscarPersonaEntreAfiliadosUseCase(repositorioAfiliado);
        if(buscarPersonaEntreAfiliadosUseCase.existePersona(persona)) return false;
        else {
            afiliado.agregarPersona(persona);
            return repositorioAfiliado.update(afiliado);
        }
    }
}
