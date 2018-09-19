package ar.com.koodi.sermeddata.RepositorioImplementacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Modelo.ObraSocial;
import Repositorio.IObraSocialRepositorio;
import ar.com.koodi.sermeddata.ModeloData.ObraSocialEntity;
import ar.com.koodi.sermeddata.RepositorioData.IObraSocialRepositorioCRUD;

@Service
public class ObraSocialRepositorioImplementacion implements IObraSocialRepositorio{

	@Autowired
	IObraSocialRepositorioCRUD iObraSocialRepositorioCRUD;

	@Override
	public ObraSocial findByNombreUnico(String nombre) {
		return mapeoDataCore(iObraSocialRepositorioCRUD.findByObraSocial(nombre));
	}

	@Override
	public boolean persist(ObraSocial obraSocial) {
		if(iObraSocialRepositorioCRUD.save(mapeoCoreData(obraSocial))!=null) return true;
		return false;
	}

	@Override
	public Collection<ObraSocial> findAll() {
		List<ObraSocial> obrasSociales = new ArrayList<>();
		iObraSocialRepositorioCRUD.findAll().forEach(obraSocial-> obrasSociales.add(mapeoDataCore(obraSocial)));
		return obrasSociales;
	}

	@Override
	public Collection<ObraSocial> findByNombre(String nombre) {
		List<ObraSocial> obrasSociales = new ArrayList<>();
		iObraSocialRepositorioCRUD.findByObraSocialContainingIgnoreCase(nombre).forEach(obraSocial-> obrasSociales.add(mapeoDataCore(obraSocial)));
		return obrasSociales;
	}

	@Override
	public boolean update(ObraSocial obraSocial) {
		ObraSocialEntity obraSocialEntity = mapeoCoreData(obraSocial);
		obraSocialEntity.setIdObraSocial(obraSocial.getIdObraSocial());
		return iObraSocialRepositorioCRUD.save(obraSocialEntity) !=null;
	}

	@Override
	public ObraSocial findById(Integer idObraSocial) {
		return mapeoDataCore(iObraSocialRepositorioCRUD.findByIdObraSocial(idObraSocial));
	}
	
	private ObraSocialEntity mapeoCoreData(ObraSocial obraSocial) {
		return new ObraSocialEntity(obraSocial.getNombre());
	}
	
	private ObraSocial mapeoDataCore(ObraSocialEntity obraSocialEntity) {
		return new ObraSocial(obraSocialEntity.getIdObraSocial(), obraSocialEntity.getObraSocial());
	}
	
}
