package InteractorTest;

import Interactor.CrearMedicoUseCase;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class CrearMedicoUnitTest {


    @Test
    public void crearMedico_MedicoNoExiste_GuardarMedico() {
        //arrange
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.respuestaPersist = true;
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        Medico medico = crearUnMedico();

        //Act
        boolean resultado = crearMedicoUseCase.crearMedico(medico);

        //Assert
        Assertions.assertEquals(true, resultado);
    }

    @Test
    public void crearMedico_MedicoSiExiste_NoGuardaMedico() {
        //arrange
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.MedicoFindById = crearUnMedico();
        repositorioMedico.respuestaPersist = false;

        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        Medico medico = new Medico(1, "Torres", "German", 190202, "674678");

        //Act
        boolean resultado = crearMedicoUseCase.crearMedico(medico);

        //Assert
        Assertions.assertEquals(false, resultado);
    }

    @Test
    public void crearMedico_MatriculaSiExiste_NoGuardaMedico() {
        //arrange
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.MedicoFindByMatricula = crearUnMedico();
        repositorioMedico.respuestaPersist = false;

        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);
        Medico medico = new Medico(45, "Torres", "German", 190202, "674678");

        //Act
        boolean resultado = crearMedicoUseCase.crearMedico(medico);

        //Assert
        Assertions.assertEquals(false, resultado);
    }

    @Test
    void validarMedicoExiste_MedicoExisteID_ReturnTrue() {
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.MedicoFindById = crearUnMedico();
        repositorioMedico.MedicoFindByMatricula = null;
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);

        boolean respuestaValidar = crearMedicoUseCase.validarMedicoExiste(crearUnMedico());

        Assertions.assertEquals(true, respuestaValidar);
    }

    @Test
    void validarMedicoExiste_MedicoExisteMatricula_ReturnTrue() {
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.MedicoFindById = null;
        repositorioMedico.MedicoFindByMatricula = crearUnMedico();
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);

        boolean respuestaValidar = crearMedicoUseCase.validarMedicoExiste(crearUnMedico());

        Assertions.assertEquals(true, respuestaValidar);
    }

    @Test
    void validarMedicoExiste_MedicoNoExiste_ReturnFalse() {
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.MedicoFindById = null;
        repositorioMedico.MedicoFindByMatricula = null;
        CrearMedicoUseCase crearMedicoUseCase = new CrearMedicoUseCase(repositorioMedico);

        boolean respuestaValidar = crearMedicoUseCase.validarMedicoExiste(crearUnMedico());

        Assertions.assertEquals(false, respuestaValidar);
    }


    //Factories
    protected Medico crearUnMedico() {
        return new Medico(1, "Torres", "German", 190202, "674678");
    }


    private class FakeMedicoRepositorio implements IMedicoRepositorio {
        boolean respuestaPersist;
        Medico MedicoFindById;
        Medico MedicoFindByMatricula;

        public boolean persist(Medico unMedico) {
            return respuestaPersist;
        }

        public Medico findById(Integer id) {
            return MedicoFindById;
        }

        public Medico findByMatricula(Integer matricula) {
            return MedicoFindByMatricula;
        }

        public List<Medico> findAll() {
            return null;
        }

        public List<Medico> findByApellido(String apellido) {
            return null;
        }

        @Override
        public boolean update(Medico medico) {
            return false;
        }


    }
}




