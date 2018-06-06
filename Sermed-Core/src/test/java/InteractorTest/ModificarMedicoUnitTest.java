package InteractorTest;

import Interactor.ModificarMedicoUseCase;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ModificarMedicoUnitTest {

    @Test
    public void modificarMedico_DatosMedico_AssertTrue() {
        FakeMedicoRepository repositorioMedico = new FakeMedicoRepository();
        ModificarMedicoUseCase modificarMedicoUseCase = new ModificarMedicoUseCase(repositorioMedico);

        Medico medicoAModificar = repositorioMedico.findById(1);

        Medico nuevosDatos = new Medico(1, "Vega", "Romina", 123, "123123");


        boolean resultado = modificarMedicoUseCase.modificarMedico(nuevosDatos);

        Assertions.assertTrue(resultado);
        Assertions.assertEquals(nuevosDatos.mostrarMedico(),medicoAModificar.mostrarMedico());
        Assertions.assertEquals(1,medicoAModificar.getIdMedico());

    }

    @Test
    void modificarMedico__MatriculaExiste_NoActualiza(){

        FakeMedicoRepository repositorioMedico = new FakeMedicoRepository();
        ModificarMedicoUseCase modificarMedicoUseCase = new ModificarMedicoUseCase(repositorioMedico);

        Medico romi = repositorioMedico.findByMatricula(109106);
        Medico romiNueva = new Medico(1,"Vega", "Romina", 192256, "4813148");

        boolean resultado = modificarMedicoUseCase.modificarMedico(romiNueva);

        Assertions.assertFalse(resultado);

    }


    //FAKE
    private class FakeMedicoRepository implements IMedicoRepositorio {
        private List<Medico> crearMedicosArray() {
            List<Medico> losMedicos=new ArrayList<Medico>();
            losMedicos.add(new Medico(1,"Vega", "Romina", 190106, "4813148"));
            losMedicos.add(new Medico(2,"Torres", "German", 190252, "674678"));
            losMedicos.add(new Medico(3,"Ruitti", "Javier", 192256, "679414"));
            return losMedicos;
        }

        List<Medico> losMedicos=crearMedicosArray();

        @Override
        public boolean persist(Medico unMedico) {
            return false;
        }

        @Override
        public Medico findById(Integer id) {
            for(Medico m : losMedicos){
                if(m.getIdMedico() == id){
                    return m;
                }
            }
            return null;
        }

        @Override
        public Medico findByMatricula(Integer matricula) {
            for(Medico m : losMedicos){
                if(m.getMatricula() == matricula){
                    return m;
                }
            }
            return null;
        }

        @Override
        public List<Medico> findAll() {
            return null;
        }

        @Override
        public List<Medico> findByApellido(String apellido) {
            return null;
        }

        @Override
        public boolean update(Medico medico) {
            return true;
        }

    }
}
