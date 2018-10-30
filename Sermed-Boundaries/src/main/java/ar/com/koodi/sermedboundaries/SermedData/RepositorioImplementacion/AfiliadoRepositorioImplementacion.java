package ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import Excepciones.AfiliadoSinTitularException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Modelo.Persona;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.PersonaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Modelo.Afiliado;
import Modelo.Plan;
import Repositorio.IAfiliadoRepositorio;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.AfiliadoEntity;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.PlanEntity;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioData.IAfiliadoRepositorioCRUD;
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
        AfiliadoEntity afiliadoEntity = mapeoCoreData(afiliado);
        afiliadoEntity.getTitular().setNroAfiliado(afiliado.getNumeroAfiliado());
        afiliadoEntity.getTitular().setNroOrden(0);
        return iAfiliadoRepositorioCRUD.save(afiliadoEntity) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public Afiliado findById(Integer idAfiliado) {
        return mapeoDataCore(this.iAfiliadoRepositorioCRUD.findByIdAfiliado(idAfiliado));
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Afiliado> findAll() {
        List<Afiliado> afiliados = new ArrayList<>();
        this.iAfiliadoRepositorioCRUD.findAll().forEach(a -> afiliados.add(mapeoDataCore(a)));
        return afiliados;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Afiliado> findByNumero(String numero) {
        List<Afiliado> afiliados = new ArrayList<>();
        this.iAfiliadoRepositorioCRUD.findByNumeroAfiliadoContainsAndActivoTrue(numero).forEach(afiliado -> afiliados.add(mapeoDataCore(afiliado)));
        return afiliados;
    }

    @Override
    @Transactional(readOnly = true)
    public Afiliado findUnicoByNumero(String numero) {
        AfiliadoEntity afiliadoEntity = this.iAfiliadoRepositorioCRUD.findByNumeroAfiliadoEqualsAndActivoTrue(numero);
        if(afiliadoEntity != null) return mapeoDataCore(afiliadoEntity);
        return null;
    }

    @Override
    @Transactional
    public boolean update(Afiliado afiliado) {
        PlanEntity planEntity = planRepositorioImplementacion.mapeoCoreData(afiliado.getPlan());
        planEntity.setIdPlan(afiliado.getPlan().getIdPlan());
        PersonaEntity titular = personaRepositorioImplementacion.mapeoCoreData(afiliado.getTitular());
        titular.setIdPersona(afiliado.getTitular().getIdPersona());
        List<PersonaEntity> miembros = new ArrayList<>();
        if(afiliado.getMiembros() != null && !afiliado.getMiembros().isEmpty()){
            afiliado.getMiembros().forEach(m -> miembros.add(personaRepositorioImplementacion.mapeoCoreData(m)));
        }
        AfiliadoEntity afiliadoEntity = new AfiliadoEntity(afiliado.getFechaAfiliacion(), afiliado.getNumeroAfiliado(), miembros, titular, afiliado.getActivo(),
                afiliado.getFechaDeBaja(), afiliado.getDiaDelMesPagoAcordado(), planEntity);
        afiliadoEntity.setIdAfiliado(afiliado.getIdAfiliado());
        return this.iAfiliadoRepositorioCRUD.save(afiliadoEntity) != null;

    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Afiliado> findAllActivos() {
        List<Afiliado> afiliados = new ArrayList<>();
        this.iAfiliadoRepositorioCRUD.findByActivoIsTrue().forEach(afiliado -> afiliados.add(mapeoDataCore(afiliado)));
        return afiliados;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Afiliado> findAllInactivos() {
        List<Afiliado> afiliados = new ArrayList<>();
        this.iAfiliadoRepositorioCRUD.findByActivoIsFalse().forEach(afiliadoEntity -> afiliados.add(mapeoDataCore(afiliadoEntity)));
        return afiliados;
    }

    public AfiliadoEntity mapeoCoreData(Afiliado afiliado) {
        if (afiliado.getIdAfiliado() == null) {
            PlanEntity planEntity = planRepositorioImplementacion.mapeoCoreData(afiliado.getPlan());
            planEntity.setIdPlan(afiliado.getPlan().getIdPlan());
            PersonaEntity titular = personaRepositorioImplementacion.mapeoCoreData(afiliado.getTitular());

            if (afiliado.getTitular().getIdPersona() != null) {
                titular.setIdPersona(afiliado.getTitular().getIdPersona());
            }
            List<PersonaEntity> miembros = new ArrayList<>();
            return new AfiliadoEntity(afiliado.getFechaAfiliacion(), afiliado.getNumeroAfiliado(), miembros,
                    titular, afiliado.getActivo(), afiliado.getFechaDeBaja(), afiliado.getDiaDelMesPagoAcordado(),
                    planEntity);
        } else {
            return this.iAfiliadoRepositorioCRUD.findByIdAfiliado(afiliado.getIdAfiliado());
        }

    }

    public Afiliado mapeoDataCore(AfiliadoEntity afiliadoEntity) {
        try {
            Plan plan = planRepositorioImplementacion.mapeoDataCore(afiliadoEntity.getPlan());
            Persona titular = personaRepositorioImplementacion.mapeoDataCore(afiliadoEntity.getTitular());
            List<Persona> miembros = new ArrayList<>();
            if (afiliadoEntity.getMiembros() != null && !afiliadoEntity.getMiembros().isEmpty()) {
                afiliadoEntity.getMiembros().forEach(m -> {
                    miembros.add(personaRepositorioImplementacion.mapeoDataCore(m));
                });
            }
            return Afiliado.instancia(afiliadoEntity.getIdAfiliado(), afiliadoEntity.getFechaAfiliacion(), afiliadoEntity.getNumeroAfiliado(), titular, miembros, afiliadoEntity.isActivo(),
                    afiliadoEntity.getFechaDeBaja(), afiliadoEntity.getDiaDelMesPagoAcordado(), plan);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } catch (AfiliadoSinTitularException e) {
            e.printStackTrace();
            return null;
        } catch (NumeroAfiliadoIncorrectoException e) {
            e.printStackTrace();
            return null;
        }
    }

}
