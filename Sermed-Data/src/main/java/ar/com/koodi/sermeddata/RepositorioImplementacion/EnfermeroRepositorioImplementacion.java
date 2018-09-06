package ar.com.koodi.sermeddata.RepositorioImplementacion;

import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;
import ar.com.koodi.sermeddata.ModeloData.EnfermeroEntity;
import ar.com.koodi.sermeddata.RepositorioData.IEnfermeroRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfermeroRepositorioImplementacion implements IEnfermeroRepositorio {

    @Autowired
    IEnfermeroRepositorioCRUD iEnfermeroRepositorioCRUD;

    @Override
    public boolean persist(Enfermero unEnfermero) {
        return this.iEnfermeroRepositorioCRUD.save(mapeoCoreData(unEnfermero)) != null;
    }

    @Override
    public Enfermero findById(Integer id) {
        return null;
    }

    @Override
    public Enfermero findByMatricula(Integer matricula) {
        return null;
    }

    @Override
    public List<Enfermero> findAll() {
        return null;
    }

    @Override
    public List<Enfermero> findByApellido(String apellido) {
        return null;
    }

    @Override
    public boolean update(Enfermero Enfermero) {
        return false;
    }

    private EnfermeroEntity mapeoCoreData(Enfermero enfermero){
        return new EnfermeroEntity(enfermero.getApellido(), enfermero.getNombre(), enfermero.getMatricula(), enfermero.getTelefono());
    }
}
