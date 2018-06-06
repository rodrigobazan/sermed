package Interactor;

import Modelo.Medico;
import Repositorio.IMedicoRepositorio;

public class ModificarMedicoUseCase {


    private IMedicoRepositorio repositorioMedico;

    public ModificarMedicoUseCase(IMedicoRepositorio repositorioMedico) {
        this.repositorioMedico = repositorioMedico;
    }

    public boolean modificarMedico(Medico nuevosDatos) {
        try {
            Medico elMedicoOriginal=repositorioMedico.findById(nuevosDatos.getIdMedico());

            if(elMedicoOriginal.getMatricula() == nuevosDatos.getMatricula()){
                elMedicoOriginal.modificarDatos(nuevosDatos);
                return this.repositorioMedico.update(elMedicoOriginal);
            }else{
                if(repositorioMedico.findByMatricula(nuevosDatos.getMatricula()) == null){
                    elMedicoOriginal.modificarDatos(nuevosDatos);
                    return this.repositorioMedico.update(elMedicoOriginal);
                }else{
                    return false;
                }
            }
        }catch (Exception e){
            return false;
        }
    }




}
