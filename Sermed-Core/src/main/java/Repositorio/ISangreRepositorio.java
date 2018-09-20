package Repositorio;

import Modelo.Sangre;

import java.util.Collection;

public interface ISangreRepositorio {

    Collection<Sangre> findAll();

    Collection<Sangre> findByGrupo(String grupo);

    Collection<Sangre> findByFactor(String factor);

    Sangre findByGrupoFactor(String grupo, String factor);

}
