package Repositorio;

import Modelo.ObraSocial;

import java.util.Collection;

public interface IObraSocialRepositorio {
    ObraSocial findByNombreUnico(String nombre);

    boolean persist(ObraSocial obraSocial);

    Collection<ObraSocial> findAll();

    Collection<ObraSocial> findByNombre(String nombre);

    boolean update(ObraSocial obraSocial);

    ObraSocial findById(Integer idObraSocial);
}
