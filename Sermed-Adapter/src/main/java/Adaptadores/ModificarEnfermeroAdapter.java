package Adaptadores;

import Excepciones.EnfermeroIncompletoException;
import Excepciones.MatriculasIgualesException;
import Excepciones.UpdateEnfermeroException;
import Inputs.ModificarEnfermeroInput;
import Modelo.Enfermero;
import ModeloApi.EnfermeroDTO;

public class ModificarEnfermeroAdapter {
    private ModificarEnfermeroInput modificarEnfermeroInput;

    public ModificarEnfermeroAdapter(ModificarEnfermeroInput modificarEnfermeroInput) {
        this.modificarEnfermeroInput = modificarEnfermeroInput;
    }

    public EnfermeroDTO modificarEnfermero(EnfermeroDTO enfermero) throws MatriculasIgualesException, EnfermeroIncompletoException, UpdateEnfermeroException {
        Enfermero enfermeroModificado = modificarEnfermeroInput.modificarEnfermero(mapeoDTOCore(enfermero));
        return mapeoCoreDTO(enfermeroModificado);
    }

    private EnfermeroDTO mapeoCoreDTO(Enfermero enfermero) {
        return new EnfermeroDTO(enfermero.getIdEnfermero(), enfermero.getApellido(), enfermero.getNombre(), enfermero.getMatricula(), enfermero.getTelefono());
    }

    private Enfermero mapeoDTOCore(EnfermeroDTO enfermero) {
        return new Enfermero(enfermero.idEnfermero, enfermero.apellido, enfermero.nombre, enfermero.matricula, enfermero.telefono);
    }
}
