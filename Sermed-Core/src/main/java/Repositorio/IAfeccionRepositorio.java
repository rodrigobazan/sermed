package Repositorio;

import java.util.List;

import Modelo.Afeccion;

public interface IAfeccionRepositorio {

	List<Afeccion> findAll();

	List<Afeccion> findByNombre(String afeccion);

	Afeccion findByNombreUnico(String afeccion);

}
