package Repositorio;

import Modelo.Afiliado;

public interface IAfiliadoRepositorio {
    boolean persist(Afiliado afiliado);

    Afiliado findById(Integer idAfiliado);
}
