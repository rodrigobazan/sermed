package Interactor;

import Modelo.Enfermero;
import Repositorio.IEnfermeroRepositorio;

public class CrearEnfermeroUseCase {


    private IEnfermeroRepositorio repositorioEnfermero;

    public CrearEnfermeroUseCase(IEnfermeroRepositorio repositorioEnfermero) {
        this.repositorioEnfermero = repositorioEnfermero;
    }

    public boolean crearEnfermero(Enfermero Enfermero) {
        if(!validarEnfermeroExiste(Enfermero)) {
            return repositorioEnfermero.persist(Enfermero);
        }
        return false;
    }

    public boolean validarEnfermeroExiste(Enfermero Enfermero) {
        if(repositorioEnfermero.findById(Enfermero.getIdEnfermero()) != null){
            return  true;
        }
        if(repositorioEnfermero.findByMatricula(Enfermero.getMatricula())!=null){
            return  true;
        }
        return  false;
    }
}
