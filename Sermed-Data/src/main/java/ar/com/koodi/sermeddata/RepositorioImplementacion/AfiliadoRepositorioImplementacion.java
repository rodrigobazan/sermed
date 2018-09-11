package ar.com.koodi.sermeddata.RepositorioImplementacion;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Modelo.Afiliado;
import Repositorio.IAfiliadoRepositorio;
import ar.com.koodi.sermeddata.ModeloData.AfiliadoEntity;
import ar.com.koodi.sermeddata.ModeloData.PlanEntity;
import ar.com.koodi.sermeddata.RepositorioData.IAfiliadoRepositorioCRUD;

@Service
public class AfiliadoRepositorioImplementacion implements IAfiliadoRepositorio {
	
	@Autowired
	IAfiliadoRepositorioCRUD iAfiliadoRepositorioCRUD;
	
	@Autowired
	PlanRepositorioImplementacion planRepositorioImplementacion; 

	@Override
	public boolean persist(Afiliado afiliado) {
//		return iAfiliadoRepositorioCRUD.save(afiliado)!=null;
		return true;
	}

	@Override
	public Afiliado findById(Integer idAfiliado) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Afiliado> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Afiliado> findByNumero(String numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Afiliado findUnicoByNumero(String numero) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Afiliado afiliado) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Afiliado> findAllActivos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Collection<Afiliado> findAllInactivos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public AfiliadoEntity mapeoCoreData(Afiliado afiliado) {
		PlanEntity plan = planRepositorioImplementacion.mapeoCoreData(afiliado.getPlan());
		plan.setIdPlan(afiliado.getPlan().getIdPlan());
		return new AfiliadoEntity(afiliado.getFechaAfiliacion(),afiliado.getNumeroAfiliado(),afiliado.getMiembros(),
				afiliado.getTitular(),afiliado.getActivo(),afiliado.getFechaDeBaja(),afiliado.getDiaDelMesPagoAcordado(),
				plan);
	}

}
