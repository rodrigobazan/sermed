package Inputs;

import Modelo.Afiliado;

import java.time.LocalDate;
import java.util.Collection;

public interface ConsultarAfiliadosMorososInput {

    Collection<Afiliado> consultarAfiliadosMorosos(LocalDate fecha);
}
