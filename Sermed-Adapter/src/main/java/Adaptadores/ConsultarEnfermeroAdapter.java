package Adaptadores;

import Excepciones.EnfermeroNoExisteException;
import Factorys.EnfermeroFactory;
import Factorys.MedicoFactory;
import Inputs.ConsultarEnfermeroInput;
import Modelo.Enfermero;
import ModeloApi.EnfermeroDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultarEnfermeroAdapter {
    private ConsultarEnfermeroInput consultarEnfermeroInput;

    public ConsultarEnfermeroAdapter(ConsultarEnfermeroInput consultarEnfermeroInput) {
        this.consultarEnfermeroInput = consultarEnfermeroInput;
    }


    public List<EnfermeroDTO> consultarEnfermeros() {
        List<EnfermeroDTO> enfermeros = new ArrayList<>();
        consultarEnfermeroInput.consultarEnfermeros().forEach(e -> enfermeros.add(EnfermeroFactory.mapeoCoreDTO(e)));
        return enfermeros;
    }

    public List<EnfermeroDTO> consultarEnfermerosPorApellido(String apellido) {
        List<EnfermeroDTO> enfermeros = new ArrayList<>();
        consultarEnfermeroInput.consultarEnfermerosPorApellido(apellido).forEach(e->enfermeros.add(EnfermeroFactory.mapeoCoreDTO(e)));
        return enfermeros;
    }

    public EnfermeroDTO consultarEnfermeroPorMatricula(int matricula) throws EnfermeroNoExisteException {
        return EnfermeroFactory.mapeoCoreDTO(consultarEnfermeroInput.consultarEnfermeroPorMatricula(matricula));
    }


}
