package Adaptadores;

import Excepciones.EnfermeroNoExisteException;
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
        consultarEnfermeroInput.consultarEnfermeros().forEach(e -> enfermeros.add(mapeoCoreDTO(e)));
        return enfermeros;
    }

    public List<EnfermeroDTO> consultarEnfermerosPorApellido(String apellido) {
        List<EnfermeroDTO> enfermeros = new ArrayList<>();
        consultarEnfermeroInput.consultarEnfermerosPorApellido(apellido).forEach(e->enfermeros.add(mapeoCoreDTO(e)));
        return enfermeros;
    }

    public EnfermeroDTO consultarEnfermeroPorMatricula(int matricula) throws EnfermeroNoExisteException {
        return mapeoCoreDTO(consultarEnfermeroInput.consultarEnfermeroPorMatricula(matricula));
    }

    private EnfermeroDTO mapeoCoreDTO(Enfermero enfermero) {
        return new EnfermeroDTO(enfermero.getIdEnfermero(), enfermero.getApellido(), enfermero.getNombre(), enfermero.getMatricula(), enfermero.getTelefono());
    }


}
