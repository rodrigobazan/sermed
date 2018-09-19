package ar.com.koodi.sermeddata.RepositorioImplementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Modelo.Afeccion;
import Repositorio.IAfeccionRepositorio;
import ar.com.koodi.sermeddata.ModeloData.AfeccionEntity;
import ar.com.koodi.sermeddata.RepositorioData.IAfeccionRepositorioCRUD;

@Service
public class AfeccionRepositorioImplementacion implements IAfeccionRepositorio {

	@Autowired
	IAfeccionRepositorioCRUD iAfeccionRepositorioCRUD;

	@Override
	@Transactional(readOnly=true)
	public List<Afeccion> findAll() {
		List<Afeccion> afecciones = new ArrayList<>();
		this.iAfeccionRepositorioCRUD.findAll().forEach(afeccion -> afecciones.add(mapeoDataCore(afeccion)));
		return afecciones;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Afeccion> findByNombre(String nombre) {
		List<Afeccion> afecciones = new ArrayList<>();
		this.iAfeccionRepositorioCRUD.findByNombreAfeccionContains(nombre)
				.forEach(afeccion -> afecciones.add(mapeoDataCore(afeccion)));
		return afecciones;
	}

	@Override
	@Transactional(readOnly=true)
	public Afeccion findByNombreUnico(String afeccion) {
		return mapeoDataCore(this.iAfeccionRepositorioCRUD.findByNombreAfeccionEquals(afeccion));
	}

	private Afeccion mapeoDataCore(AfeccionEntity afeccionEntity) {
		if (afeccionEntity != null) {
			return new Afeccion(afeccionEntity.getIdAfeccion(), afeccionEntity.getNombreAfeccion());
		}
		return null;
	}

}