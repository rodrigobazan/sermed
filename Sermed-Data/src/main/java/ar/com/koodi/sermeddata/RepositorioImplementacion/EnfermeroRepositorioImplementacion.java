package ar.com.koodi.sermeddata.RepositorioImplementacion;

import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;
import ar.com.koodi.sermeddata.ModeloData.EnfermeroEntity;
import ar.com.koodi.sermeddata.RepositorioData.IEnfermeroRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnfermeroRepositorioImplementacion implements IEnfermeroRepositorio {

    @Autowired
    IEnfermeroRepositorioCRUD iEnfermeroRepositorioCRUD;

    @Override
    @Transactional
    public boolean persist(Enfermero unEnfermero) {
        return this.iEnfermeroRepositorioCRUD.save(mapeoCoreData(unEnfermero)) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public Enfermero findById(Integer id) {
        return mapeoDataCore(this.iEnfermeroRepositorioCRUD.findByIdEnfermero(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Enfermero findByMatricula(Integer matricula) {
        EnfermeroEntity enfermeroEntity = this.iEnfermeroRepositorioCRUD.findByMatricula(matricula);
        if( enfermeroEntity != null) return mapeoDataCore(enfermeroEntity);
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enfermero> findAll() {
        List<Enfermero> enfermeros = new ArrayList<>();
        this.iEnfermeroRepositorioCRUD.findAll().forEach(e -> enfermeros.add(mapeoDataCore(e)));
        return enfermeros;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Enfermero> findByApellido(String apellido) {
        List<Enfermero> enfermeros = new ArrayList<>();
        this.iEnfermeroRepositorioCRUD.findByApellidoContains(apellido).forEach(e -> enfermeros.add(mapeoDataCore(e)));
        return enfermeros;
    }

    @Override
    @Transactional
    public boolean update(Enfermero enfermero) {
        EnfermeroEntity enfermeroEntity = mapeoCoreData(enfermero);
        enfermeroEntity.setIdEnfermero(enfermero.getIdEnfermero());
        return iEnfermeroRepositorioCRUD.save(enfermeroEntity) != null;
    }


    public EnfermeroEntity mapeoCoreData(Enfermero enfermero){
        return new EnfermeroEntity(enfermero.getApellido(), enfermero.getNombre(), enfermero.getMatricula(), enfermero.getTelefono());
    }

    public Enfermero mapeoDataCore(EnfermeroEntity enfermeroEntity){
        return new Enfermero(enfermeroEntity.getIdEnfermero(),enfermeroEntity.getApellido(),enfermeroEntity.getNombre(),enfermeroEntity.getMatricula(),enfermeroEntity.getTelefono());
    }
}
