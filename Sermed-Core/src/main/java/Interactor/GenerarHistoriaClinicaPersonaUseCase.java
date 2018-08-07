package Interactor;

import ModeloReporte.HistoriaClinicaPersonaDTO;
import Repositorio.IPersonaRepositorio;
import Repositorio.IVisitaRepositorio;

public class GenerarHistoriaClinicaPersonaUseCase {
    private IPersonaRepositorio repositorioPersona;
    private IVisitaRepositorio repositorioVisita;

    public GenerarHistoriaClinicaPersonaUseCase(IPersonaRepositorio repositorioPersona, IVisitaRepositorio repositorioVisita) {
        this.repositorioPersona = repositorioPersona;
        this.repositorioVisita = repositorioVisita;
    }

    public HistoriaClinicaPersonaDTO generarHistoriaClinicaPersona(String numeroAfiliado, Integer orden){
//        ConsultarPersonaUseCase consultarPersona=
//        ConsultarVisitaUseCase consultarVisitas= new ConsultarVisitaUseCase(repositorioVisita);
//
//        HistoriaClinicaPersonaDTO historiaClinica=new HistoriaClinicaPersonaDTO(consultarAfiliado.consultarAfiliadoPorNumero(numeroAfiliado).mostrarAntecedentes());
        return null;
    }
}
