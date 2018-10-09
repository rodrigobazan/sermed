package Inputs;

import Excepciones.AfiliadoNoExisteException;
import Modelo.Afiliado;

import java.util.Collection;

public interface ConsultarAfiliadoInput {

    Collection<Afiliado> consultarAfiliados();

    Collection<Afiliado> consultarAfiliadosPorNumero(String numero);

    Afiliado consultarAfiliadoPorNumero(String numeroAfiliado) throws AfiliadoNoExisteException;
}
