package Repositorio;

import Modelo.Plan;

public interface IPlanRepositorio {
    Plan findByNombre(String nombre);

    boolean persist(Plan plan);
}
