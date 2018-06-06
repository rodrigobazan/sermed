package InteractorTest;

import Interactor.ConsultarMedicoUseCase;
import Modelo.Medico;
import Repositorio.IMedicoRepositorio;
import org.hamcrest.collection.IsEmptyCollection;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class ConsultarMedicoUnitTest {


    @Test
    public void consultarMedicos_ExistenDatos_AssertTrue() {
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.arrayMedicoOrigen=crearMedicosArray();
        repositorioMedico.arrayMedicosDevuelto=repositorioMedico.arrayMedicoOrigen;

        ConsultarMedicoUseCase consultarMedicoUseCase = new ConsultarMedicoUseCase(repositorioMedico);

        List<Medico> medicos = consultarMedicoUseCase.consultarMedicos();

        assertThat(medicos, not(IsEmptyCollection.empty()));
    }

    @Test
    public void consultarMedicos_NoExistenDatos_AssertFalse() {
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.arrayMedicosDevuelto=new ArrayList<Medico>();

        ConsultarMedicoUseCase consultarMedicoUseCase = new ConsultarMedicoUseCase(repositorioMedico);

        List<Medico> medicos = consultarMedicoUseCase.consultarMedicos();

        assertThat(medicos, IsEmptyCollection.empty());
    }

    @Test
    public void consultarMedicosPorApellido_CriterioCadenaVacia_DevolverTodos(){
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.arrayMedicosDevuelto=crearMedicosArray();

        ConsultarMedicoUseCase consultarMedicoUseCase = new ConsultarMedicoUseCase(repositorioMedico);

        List<Medico> medicos = consultarMedicoUseCase.consultarMedicosPorApellido("");

        assertEquals(repositorioMedico.arrayMedicosDevuelto, medicos);
    }

    @Test void consultarMedicosPorApellido_CriterioCadenaConDatos_DevolverAlgunos(){
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.arrayMedicoFiltro=crearMedicoFiltroArray();

        ConsultarMedicoUseCase consultarMedicoUseCase = new ConsultarMedicoUseCase(repositorioMedico);

        List<Medico> medicos = consultarMedicoUseCase.consultarMedicosPorApellido("ve");

        assertThat(medicos, hasSize(1));

    }
    @Test
    void consultarMedicoMatricula_MatriculaExiste_RetornaMedico()
    {
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.arrayMedicoOrigen=crearMedicosArray();
        repositorioMedico.matriculaExiste=true;

        ConsultarMedicoUseCase consultarMedicoUseCase=new ConsultarMedicoUseCase(repositorioMedico);

        Medico medicoBuscado=consultarMedicoUseCase.consultarMedicoPorMatricula(190252);

        assertEquals(2,medicoBuscado.getIdMedico());

    }

    @Test
    void consultarMedicoMatricula_MatriculaNoExiste_RetornaMedicoVacio()
    {
        FakeMedicoRepositorio repositorioMedico = new FakeMedicoRepositorio();
        repositorioMedico.arrayMedicoOrigen=crearMedicosArray();
        repositorioMedico.matriculaExiste=false;

        ConsultarMedicoUseCase consultarMedicoUseCase=new ConsultarMedicoUseCase(repositorioMedico);

        Medico medicoBuscado=consultarMedicoUseCase.consultarMedicoPorMatricula(190123);

        assertEquals(0,medicoBuscado.getIdMedico());

    }


    private List<Medico> crearMedicosArray() {
        List<Medico> losMedicos=new ArrayList<Medico>();
        losMedicos.add(new Medico(1,"Vega", "Romina", 1044, "4813148"));
        losMedicos.add(new Medico(2,"Torres", "German", 190252, "674678"));
        losMedicos.add(new Medico(3,"Ruitti", "Javier", 192256, "679414"));
        return losMedicos;
    }

    private List<Medico> crearMedicoFiltroArray(){
        List<Medico> losMedicos=new ArrayList<Medico>();
        losMedicos.add(new Medico(1,"Vega", "Romina", 1044, "4813148"));
        return losMedicos;
    }

    private class FakeMedicoRepositorio implements IMedicoRepositorio {
        List<Medico> arrayMedicosDevuelto;
        List<Medico> arrayMedicoFiltro;
        List<Medico> arrayMedicoOrigen;
        boolean matriculaExiste;


        public boolean persist(Medico unMedico) {
            return false;
        }

        public Medico findById(Integer id) {
            return null;
        }

        public Medico findByMatricula(final Integer matricula) {
            if(matriculaExiste){
                return arrayMedicoOrigen.get(1);
            }
            else{
                return new Medico(0,"","",0,"");
            }

        }

        public List<Medico> findAll() {
            return arrayMedicosDevuelto;
        }

        public List<Medico> findByApellido(String apellido) {
            if(apellido == "")
                return arrayMedicosDevuelto;
            return arrayMedicoFiltro;
        }

        @Override
        public boolean update(Medico medico) {
            return false;
        }
    }
}
