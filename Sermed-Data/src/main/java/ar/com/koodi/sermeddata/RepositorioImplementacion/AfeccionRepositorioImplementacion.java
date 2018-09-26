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

	public Afeccion mapeoDataCore(AfeccionEntity afeccionEntity) {
		if (afeccionEntity != null) {
			return new Afeccion(afeccionEntity.getIdAfeccion(), afeccionEntity.getNombreAfeccion());
		}
		return null;
	}

	public AfeccionEntity mapeoCoreData(Afeccion afeccion){
		try {
			if(afeccion.getIdAfeccion() == null){
				AfeccionEntity afeccionEntity = new AfeccionEntity(afeccion.getNombreAfeccion());
				return afeccionEntity;
			}
			return this.iAfeccionRepositorioCRUD.findByNombreAfeccionEquals(afeccion.getNombreAfeccion());
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

}
