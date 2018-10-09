package Interactor;

import Excepciones.AfiliadoNoExisteException;
import Inputs.ConsultarAfiliadoInput;
import Modelo.Afiliado;
import Repositorio.IAfiliadoRepositorio;

import java.util.List;

public class ConsultarAfiliadoUseCase implements ConsultarAfiliadoInput {
    private IAfiliadoRepositorio repositorioAfiliado;

    public ConsultarAfiliadoUseCase(IAfiliadoRepositorio repositorioAfiliado) {

        this.repositorioAfiliado = repositorioAfiliado;
    }

    @Override
    public List<Afiliado> consultarAfiliados() {
        return (List<Afiliado>) repositorioAfiliado.findAll();
    }

    @Override
    public List<Afiliado> consultarAfiliadosPorNumero(String numero) {
        return (List<Afiliado>) repositorioAfiliado.findByNumero(numero);
    }

    @Override
    public Afiliado consultarAfiliadoPorNumero(String numeroAfiliado) throws AfiliadoNoExisteException {
        Afiliado buscado = repositorioAfiliado.findUnicoByNumero(numeroAfiliado);
        if(buscado == null)
            throw new AfiliadoNoExisteException();
        else
            return buscado;
    }
}
