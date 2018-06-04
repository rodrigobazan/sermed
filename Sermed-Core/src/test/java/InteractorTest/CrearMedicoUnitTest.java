package InteractorTest;

import Interactor.CrearMedicoUseCase;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CrearMedicoUnitTest {

    @Test
    public void crearMedico_MedicoNoExiste_GuardarMedico(){
        //arrange
        FakeMedicoRepositorio repositorioMedico=new FakeMedicoRepositorio();
        repositorioMedico.respuesta=true;
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        Medico medico = new Medico(1,"Torres","German",190202,"674678");

        //Act
        boolean resultado = crearMedicoUseCase.crearMedico(medico);

        //Assert
        Assertions.assertEquals(true,resultado);
    }

    @Test
    public void crearMedico_MedicoSiExiste_NoGuardaMedico(){
        //arrange
        FakeMedicoRepositorio repositorioMedico=new FakeMedicoRepositorio();
        repositorioMedico.MedicoFind=new Medico(1,"Torres","German",190202,"674678");
        repositorioMedico.respuesta=false;

        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        Medico medico = new Medico(1,"Torres","German",190202,"674678");

        //Act
        boolean resultado = crearMedicoUseCase.crearMedico(medico);

        //Assert
        Assertions.assertEquals(false,resultado);
    }

}


class FakeMedicoRepositorio implements IMedicoRepositorio{

    boolean respuesta;
    Medico MedicoFind;

    public boolean persist(Medico unMedico) {
        return respuesta;
    }

    public Medico find(Medico medico) {
        return MedicoFind;
    }
}
