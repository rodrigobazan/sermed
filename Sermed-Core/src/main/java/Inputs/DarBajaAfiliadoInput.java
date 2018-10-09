package Inputs;

import Excepciones.AfiliadoDeBajaException;
import Modelo.Afiliado;

import java.time.LocalDate;

public interface DarBajaAfiliadoInput {

    boolean darBajaAfiliado(Afiliado afiliado, LocalDate fechaBaja) throws AfiliadoDeBajaException;
}
