package ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion;

import Excepciones.SangreIncompletoException;
import Modelo.Sangre;
import Repositorio.ISangreRepositorio;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.SangreEntity;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioData.ISangreRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class SangreRepositorioImplementacion implements ISangreRepositorio {

    @Autowired
    ISangreRepositorioCRUD ISangreRepositorioCRUD;

    @Override
    @Transactional(readOnly = true)
    public Collection<Sangre> findAll() {
        Collection<Sangre> sangres = new ArrayList<>();
        this.ISangreRepositorioCRUD.findAll().forEach(sangreEntity -> sangres.add(mapeoDataCore(sangreEntity)));
        return sangres;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Sangre> findByGrupo(String grupo) {
        Collection<Sangre> sangres = new ArrayList<>();
        this.ISangreRepositorioCRUD.findByGrupo(grupo).forEach(sangreEntity -> sangres.add(mapeoDataCore(sangreEntity)));
        return sangres;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Sangre> findByFactor(String factor) {
        Collection<Sangre> sangres = new ArrayList<>();
        this.ISangreRepositorioCRUD.findByFactor(factor).forEach(sangreEntity -> sangres.add(mapeoDataCore(sangreEntity)));
        return sangres;
    }

    @Override
    @Transactional(readOnly = true)
    public Sangre findByGrupoFactor(String grupo, String factor) {
        SangreEntity sangre = this.ISangreRepositorioCRUD.findByGrupoAndFactor(grupo, factor);
        if (sangre != null) return mapeoDataCore(sangre);
        return null;
    }


    public Sangre mapeoDataCore(SangreEntity sangreEntity) {
        try {
            return Sangre.instacia(sangreEntity.getIdSangre(), sangreEntity.getGrupo(), sangreEntity.getFactor());
        } catch (SangreIncompletoException e) {
            e.printStackTrace();
            return null;
        }
    }

    public SangreEntity mapeoCoreData(Sangre sangre) {
        try {
            if (sangre.getIdSangre() == null) {
                return new SangreEntity(sangre.getGrupo(), sangre.getFactor());
            }
            return this.ISangreRepositorioCRUD.findByGrupoAndFactor(sangre.getGrupo(), sangre.getFactor());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
