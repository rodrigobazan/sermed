package Repositorio;

import Modelo.Medico;

public interface IMedicoRepositorio {

    boolean persist(Medico unMedico);

    Medico find(Medico medico);
}
