package ModelTest;

import Modelo.Medico;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicoUnitTest {

    @Test
    void mostrarMedico_MedicoCompleto_MuestraFormateado(){
        Medico elMedico=new Medico(1,"Vega", "Romina", 1044, "4813148");

        String mostrarMedico=elMedico.mostrarMedico();

        Assertions.assertEquals("Vega, Romina. Matricula 1044. Tel. 4813148",mostrarMedico);

    }
}
