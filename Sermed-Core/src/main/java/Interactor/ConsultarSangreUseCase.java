package Interactor;

import Modelo.Sangre;
import Repositorio.ISangreRepositorio;

import java.util.Collection;
import java.util.List;

public class ConsultarSangreUseCase {
    private ISangreRepositorio iSangreRepositorio;

    public ConsultarSangreUseCase(ISangreRepositorio iSangreRepositorio) {
        this.iSangreRepositorio = iSangreRepositorio;
    }

    public Collection<Sangre> consultarSangre(){
        return iSangreRepositorio.findAll();
    }

    public List<Sangre> consultarSangrePorGrupo(String grupo){
        return (List<Sangre>) iSangreRepositorio.findByGrupo(grupo);
    }

    public List<Sangre> consultarSangrePorFactor(String factor) {
        return (List<Sangre>) iSangreRepositorio.findByFactor(factor);
    }

    public List<Sangre> consultarSangrePorGrupoFactor(String grupo, String factor) {
        return (List<Sangre>) iSangreRepositorio.findByGrupoFactor(grupo,factor);
    }
}
