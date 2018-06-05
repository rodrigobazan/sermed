package Repositorio;

import Modelo.Medico;

public interface IMedicoRepositorio {

    boolean persist(Medico unMedico);

    Medico findById(Integer id);

    Medico findByMatricula(Integer matricula);
}
