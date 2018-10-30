package Interactor;

import Excepciones.AfiliadoExisteException;
import Excepciones.TitularEnAfiliadoActivoException;
import Inputs.CrearAfiliadoInput;
import Modelo.Afiliado;
import Modelo.Persona;
import Repositorio.IAfiliadoRepositorio;

public class CrearAfiliadoUseCase implements CrearAfiliadoInput {
    private IAfiliadoRepositorio repositorioAfiliado;

    public CrearAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {
        this.repositorioAfiliado = repositorioAfiliado;
    }

    @Override
    public boolean crearAfiliado(Afiliado afiliadoNuevo) throws TitularEnAfiliadoActivoException, AfiliadoExisteException {
        if (!validarAfiliadoExiste(afiliadoNuevo)) {
            Afiliado afiliadoActivoQueContieneAlTitularNuevo = buscarAfiliadoQueContienePersona(afiliadoNuevo.getTitular());
            if (afiliadoActivoQueContieneAlTitularNuevo != null) throw new TitularEnAfiliadoActivoException();
            return repositorioAfiliado.persist(afiliadoNuevo);
        }
        throw new AfiliadoExisteException();
    }

    private Afiliado buscarAfiliadoQueContienePersona(Persona titular) {
        final Afiliado afiliado = repositorioAfiliado.findAll().stream().filter(a -> a.contienePersona(titular) && a.getActivo()).findAny().orElse(null);
        return afiliado;
    }

    private boolean validarAfiliadoExiste(Afiliado afiliado) {
        return repositorioAfiliado.findUnicoByNumero(afiliado.getNumeroAfiliado()) != null;
    }

}
