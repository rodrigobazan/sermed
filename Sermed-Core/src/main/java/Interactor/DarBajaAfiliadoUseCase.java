package Interactor;

import Excepciones.AfiliadoDeBajaException;
import Inputs.DarBajaAfiliadoInput;
import Modelo.Afiliado;
import Repositorio.IAfiliadoRepositorio;

import java.time.LocalDate;

public class DarBajaAfiliadoUseCase implements DarBajaAfiliadoInput {
    private IAfiliadoRepositorio repositorioAfiliado;

    public DarBajaAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    public boolean darBajaAfiliado(Afiliado afiliado, LocalDate fechaBaja) throws AfiliadoDeBajaException {
        if (afiliado.getActivo()) {
            afiliado.darDeBaja(fechaBaja);
            return repositorioAfiliado.update(afiliado);
        }
        throw new AfiliadoDeBajaException();

    }
}
