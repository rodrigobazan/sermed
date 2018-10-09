package Interactor;

import java.util.List;

import Excepciones.AfeccionNoExisteException;
import Inputs.ConsultarAfeccionIput;
import Modelo.Afeccion;
import Repositorio.IAfeccionRepositorio;

public class ConsultarAfeccionUseCase implements ConsultarAfeccionIput {

	private IAfeccionRepositorio repositorioAfeccion;

	public ConsultarAfeccionUseCase(IAfeccionRepositorio repositorioAfeccion) {
		this.repositorioAfeccion = repositorioAfeccion;
	}

	public List<Afeccion> consultarAfecciones() {
		return (List<Afeccion>) this.repositorioAfeccion.findAll();
	}

	public List<Afeccion> consultarAfeccionesPorNombre(String afeccion) {
		return (List<Afeccion>) this.repositorioAfeccion.findByNombre(afeccion);
	}

	public Afeccion consultarAfeccionPorNombre(String afeccion) throws AfeccionNoExisteException {
		Afeccion resultado = this.repositorioAfeccion.findByNombreUnico(afeccion);
		if (resultado != null) {
			return resultado;
		}
		throw new AfeccionNoExisteException();
	}

}
