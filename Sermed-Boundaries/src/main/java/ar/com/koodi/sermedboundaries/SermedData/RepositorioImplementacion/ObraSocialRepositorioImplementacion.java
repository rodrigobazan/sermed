package ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Modelo.ObraSocial;
import Repositorio.IObraSocialRepositorio;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.ObraSocialEntity;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioData.IObraSocialRepositorioCRUD;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ObraSocialRepositorioImplementacion implements IObraSocialRepositorio{

	@Autowired
	IObraSocialRepositorioCRUD iObraSocialRepositorioCRUD;

	@Override
	@Transactional(readOnly = true)
	public ObraSocial findByNombreUnico(String nombre) {
		if(iObraSocialRepositorioCRUD.findByObraSocial(nombre)!=null) return mapeoDataCore(iObraSocialRepositorioCRUD.findByObraSocial(nombre));
		return null;
	}

	@Override
	@Transactional
	public boolean persist(ObraSocial obraSocial) {
		if(iObraSocialRepositorioCRUD.save(mapeoCoreData(obraSocial))!=null) return true;
		return false;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<ObraSocial> findAll() {
		List<ObraSocial> obrasSociales = new ArrayList<>();
		iObraSocialRepositorioCRUD.findAll().forEach(obraSocial-> obrasSociales.add(mapeoDataCore(obraSocial)));
		return obrasSociales;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<ObraSocial> findByNombre(String nombre) {
		List<ObraSocial> obrasSociales = new ArrayList<>();
		iObraSocialRepositorioCRUD.findByObraSocialContainingIgnoreCase(nombre).forEach(obraSocial-> obrasSociales.add(mapeoDataCore(obraSocial)));
		return obrasSociales;
	}

	@Override
	@Transactional
	public boolean update(ObraSocial obraSocial) {
		ObraSocialEntity obraSocialEntity = new ObraSocialEntity(obraSocial.getNombre());
		obraSocialEntity.setIdObraSocial(obraSocial.getIdObraSocial());
		return iObraSocialRepositorioCRUD.save(obraSocialEntity) !=null;
	}

	@Override
	@Transactional(readOnly = true)
	public ObraSocial findById(Integer idObraSocial) {
		return mapeoDataCore(iObraSocialRepositorioCRUD.findByIdObraSocial(idObraSocial));
	}
	
	public ObraSocialEntity mapeoCoreData(ObraSocial obraSocial) {
		try {
			if(obraSocial.getIdObraSocial() == null){
				return new ObraSocialEntity(obraSocial.getNombre());
			}
			return this.iObraSocialRepositorioCRUD.findByIdObraSocial(obraSocial.getIdObraSocial());
		}catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

    public ObraSocial mapeoDataCore(ObraSocialEntity obraSocialEntity) {
		if(obraSocialEntity != null) return new ObraSocial(obraSocialEntity.getIdObraSocial(), obraSocialEntity.getNombre());
		return null;
	}
	
}
