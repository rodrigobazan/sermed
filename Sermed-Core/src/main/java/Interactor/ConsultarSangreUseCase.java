package Interactor;

import Modelo.Sangre;
import Repositorio.ISangreRepositorio;

import java.util.Collection;
import java.util.List;

import Inputs.ConsultarSangreInput;

public class ConsultarSangreUseCase implements ConsultarSangreInput {
    private ISangreRepositorio iSangreRepositorio;

    public ConsultarSangreUseCase(ISangreRepositorio iSangreRepositorio) {
        this.iSangreRepositorio = iSangreRepositorio;
    }

    public List<Sangre> consultarSangre(){
        return (List<Sangre>) iSangreRepositorio.findAll();
    }

    public List<Sangre> consultarSangrePorGrupo(String grupo){
        return (List<Sangre>) iSangreRepositorio.findByGrupo(grupo);
    }

    public List<Sangre> consultarSangrePorFactor(String factor) {
        return (List<Sangre>) iSangreRepositorio.findByFactor(factor);
    }

    public Sangre consultarSangrePorGrupoFactor(String grupo, String factor) {
        return iSangreRepositorio.findByGrupoFactor(grupo,factor);
    }
}
