package Interactor;

import Modelo.Medico;
import Repositorio.IMedicoRepositorio;

public class CrearMedicoUseCase {


    private IMedicoRepositorio repositorioMedico;

    public CrearMedicoUseCase(IMedicoRepositorio repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    public boolean crearMedico(Medico medico) {
        if(!validarMedicoExiste(medico)) {
            return repositorioMedico.persist(medico);
        }
        return false;
    }

    public boolean validarMedicoExiste(Medico medico) {
        if(repositorioMedico.findById(medico.getIdMedico()) != null){
            return  true;
        }
        if(repositorioMedico.findByMatricula(medico.getMatricula())!=null){
            return  true;
        }
        return  false;
    }
}
