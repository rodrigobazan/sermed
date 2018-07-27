package Interactor;

import Modelo.Afiliado;
import Repositorio.IAfiliadoRepositorio;

import java.time.LocalDate;

public class DarBajaAfiliadoUseCase {
    private IAfiliadoRepositorio repositorioAfiliado;

    public DarBajaAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public boolean darBajaAfiliado(Afiliado afiliado, LocalDate fechaBaja) {
        if (afiliado.getActivo()) {
            afiliado.darDeBaja(fechaBaja);
            return repositorioAfiliado.update(afiliado);
        }
        return false;

    }
}
