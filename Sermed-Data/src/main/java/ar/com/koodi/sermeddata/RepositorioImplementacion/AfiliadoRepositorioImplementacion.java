package ar.com.koodi.sermeddata.RepositorioImplementacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Modelo.Persona;
import ar.com.koodi.sermeddata.ModeloData.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Modelo.Afiliado;
import Modelo.Plan;
import Repositorio.IAfiliadoRepositorio;
import ar.com.koodi.sermeddata.ModeloData.AfiliadoEntity;
import ar.com.koodi.sermeddata.ModeloData.PlanEntity;
import ar.com.koodi.sermeddata.RepositorioData.IAfiliadoRepositorioCRUD;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AfiliadoRepositorioImplementacion implements IAfiliadoRepositorio {

    @Autowired
    IAfiliadoRepositorioCRUD iAfiliadoRepositorioCRUD;

    @Autowired
    PlanRepositorioImplementacion planRepositorioImplementacion;

    @Autowired
    PersonaRepositorioImplementacion personaRepositorioImplementacion;

    @Override
    @Transactional
    public boolean persist(Afiliado afiliado) {
        return iAfiliadoRepositorioCRUD.save(mapeoCoreData(afiliado)) != null;
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
        PlanEntity planEntity = planRepositorioImplementacion.mapeoCoreData(afiliado.getPlan());
        planEntity.setIdPlan(afiliado.getPlan().getIdPlan());
        PersonaEntity titular = personaRepositorioImplementacion.mapeoCoreData(afiliado.getTitular());
        if(afiliado.getTitular().getIdPersona()!=null){
            titular.setIdPersona(afiliado.getTitular().getIdPersona());
        }
        List<PersonaEntity> miembros = new ArrayList<>();
        return new AfiliadoEntity(afiliado.getFechaAfiliacion(), afiliado.getNumeroAfiliado(), miembros,
                titular, afiliado.getActivo(), afiliado.getFechaDeBaja(), afiliado.getDiaDelMesPagoAcordado(),
                planEntity);
    }

}
