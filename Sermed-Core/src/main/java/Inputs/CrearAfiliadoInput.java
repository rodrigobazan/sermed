package Inputs;

import Excepciones.AfiliadoExisteException;
import Excepciones.TitularEnAfiliadoActivoException;
import Modelo.Afiliado;

public interface CrearAfiliadoInput {

    boolean crearAfiliado(Afiliado afiliadoNuevo) throws TitularEnAfiliadoActivoException, AfiliadoExisteException;

}
