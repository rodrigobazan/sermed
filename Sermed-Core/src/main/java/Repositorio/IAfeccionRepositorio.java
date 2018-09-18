package Repositorio;

import java.util.Collection;
import java.util.List;

import Modelo.Afeccion;

public interface IAfeccionRepositorio {

	Collection<Afeccion> findAll();

	Collection<Afeccion> findByNombre(String afeccion);

	Afeccion findByNombreUnico(String afeccion);

}
