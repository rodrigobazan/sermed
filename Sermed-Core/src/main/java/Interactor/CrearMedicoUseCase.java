package Interactor;

import Excepciones.MedicoExisteException;
import Inputs.CrearMedicoInput;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;

public class CrearMedicoUseCase implements CrearMedicoInput{


    private IMedicoRepositorio repositorioMedico;

    public CrearMedicoUseCase(IMedicoRepositorio repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    public boolean crearMedico(Medico medico) throws MedicoExisteException {
        if (!validarMedicoExiste(medico)) {
            return repositorioMedico.persist(medico);
        }
        throw new MedicoExisteException();
    }

    public boolean validarMedicoExiste(Medico medico) {
        return repositorioMedico.findByMatricula(medico.getMatricula()) != null;
    }
}
