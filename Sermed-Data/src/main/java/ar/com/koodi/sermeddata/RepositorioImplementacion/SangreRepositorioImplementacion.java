package ar.com.koodi.sermeddata.RepositorioImplementacion;

import Excepciones.SangreIncompletoException;
import Modelo.Sangre;
import Repositorio.ISangreRepositorio;
import ar.com.koodi.sermeddata.ModeloData.SangreEntity;
import ar.com.koodi.sermeddata.RepositorioData.ISangreRepositorioCRUD;
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
    public Collection<Sangre> findByGrupo(String grupo) {
        Collection<Sangre> sangres = new ArrayList<>();
        this.ISangreRepositorioCRUD.findByGrupo(grupo).forEach(sangreEntity -> sangres.add(mapeoDataCore(sangreEntity)));
        return sangres;
    }

    @Override
    public Collection<Sangre> findByFactor(String factor) {
        Collection<Sangre> sangres = new ArrayList<>();
        this.ISangreRepositorioCRUD.findByFactor(factor).forEach(sangreEntity -> sangres.add(mapeoDataCore(sangreEntity)));
        return sangres;
    }

    @Override
    public Sangre findByGrupoFactor(String grupo, String factor) {
        SangreEntity sangre = this.ISangreRepositorioCRUD.findByGrupoAndFactor(grupo,factor);
        if(sangre != null) return mapeoDataCore(sangre);
        return null;
    }


    public Sangre mapeoDataCore(SangreEntity sangreEntity){
        try{
            return Sangre.instacia(sangreEntity.getIdSangre(),sangreEntity.getGrupo(),sangreEntity.getFactor());
        } catch (SangreIncompletoException e) {
            e.printStackTrace();
            return null;
        }
    }
}
