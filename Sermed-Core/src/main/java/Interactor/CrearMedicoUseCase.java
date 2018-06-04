package Interactor;

import Modelo.Medico;
import Repositorio.IMedicoRepositorio;

public class CrearMedicoUseCase {


    private IMedicoRepositorio repositorioMedico;

    public CrearMedicoUseCase(IMedicoRepositorio repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    public boolean crearMedico(Medico medico) {
        Medico elMedico=repositorioMedico.find(medico);
        if(null == elMedico) {
            return repositorioMedico.persist(medico);
        }
        return false;
    }
}
