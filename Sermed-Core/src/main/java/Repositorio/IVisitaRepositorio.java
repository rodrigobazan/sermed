package Repositorio;

import Modelo.Afiliado;
import Modelo.Persona;
import Modelo.Visita;

import java.util.Collection;

public interface IVisitaRepositorio {
    boolean persist(Visita visita);

    Collection<Visita> findAll();

    Visita findbyNumero(int numeroVisita);

    Collection<Visita> findByPersona(Persona laPersona);


}
