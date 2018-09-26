package ar.com.koodi.sermeddata.RepositorioImplementacion;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import ar.com.koodi.sermeddata.ModeloData.MedicoEntity;
import ar.com.koodi.sermeddata.RepositorioData.IMedicoRepositorioCRUD;

@Service
public class MedicoRepositorioImplementacion implements IMedicoRepositorio {

    @Autowired
    IMedicoRepositorioCRUD iMedicoRepositorioCRUD;

    @Override
    @Transactional
    public boolean persist(Medico unMedico) {
        return this.iMedicoRepositorioCRUD.save(mapeoCoreData(unMedico)) != null;
    }

    @Override
    @Transactional(readOnly = true)
    public Medico findById(Integer id) {
        return mapeoDataCore(this.iMedicoRepositorioCRUD.findByIdMedico(id));
    }


    @Override
    @Transactional(readOnly = true)
    public Medico findByMatricula(Integer matricula) {
        MedicoEntity medicoEntity = this.iMedicoRepositorioCRUD.findByMatricula(matricula);
        if (medicoEntity != null) return mapeoDataCore(medicoEntity);
        return null;
    }

    @Override
    public List<Medico> findAll() {
        List<Medico> medicos = new ArrayList<>();
        this.iMedicoRepositorioCRUD.findAll().forEach(medico -> medicos.add(mapeoDataCore(medico)));
        return medicos;
    }

    @Override
    public List<Medico> findByApellido(String apellido) {
        List<Medico> medicos = new ArrayList<>();
        this.iMedicoRepositorioCRUD.findByApellidoContainingIgnoreCase(apellido).forEach(medico -> medicos.add(mapeoDataCore(medico)));
        ;
        return medicos;
    }

    @Override
    public boolean update(Medico medico) {
        MedicoEntity medicoEntity = new MedicoEntity(medico.getApellido(), medico.getNombre(), medico.getMatricula(), medico.getTelefono());
        medicoEntity.setIdMedico(medico.getIdMedico());
        return iMedicoRepositorioCRUD.save(medicoEntity) != null;
    }

    public MedicoEntity mapeoCoreData(Medico medico) {
        if(medico.getIdMedico() == null){
            return new MedicoEntity(medico.getApellido(), medico.getNombre(), medico.getMatricula(), medico.getTelefono());
        }
        return this.iMedicoRepositorioCRUD.findByIdMedico(medico.getIdMedico());

    }

    public Medico mapeoDataCore(MedicoEntity medicoEntity) {
        return new Medico(medicoEntity.getIdMedico(), medicoEntity.getApellido(), medicoEntity.getNombre(), medicoEntity.getMatricula(), medicoEntity.getTelefono());
    }


}
