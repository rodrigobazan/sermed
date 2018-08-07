package Repositorio;

import Modelo.Visita;

import java.util.Collection;

public interface IVisitaRepositorio {
    boolean persist(Visita visita);

    Collection<Visita> findAll();

    Visita findbyNumero(int numeroVisita);
}
