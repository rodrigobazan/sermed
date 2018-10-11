package Factorys;

import Modelo.Enfermero;
import ModeloApi.EnfermeroDTO;

public class EnfermeroFactory {

    private EnfermeroFactory() {
    }

    public static Enfermero mapeoDTOCore(EnfermeroDTO enfermeroDTO) {
        return new Enfermero(enfermeroDTO.idEnfermero, enfermeroDTO.apellido, enfermeroDTO.nombre, enfermeroDTO.matricula,
                enfermeroDTO.telefono);
    }

    public static EnfermeroDTO mapeoCoreDTO(Enfermero enfermero) {
        return new EnfermeroDTO(enfermero.getIdEnfermero(), enfermero.getApellido(), enfermero.getNombre(), enfermero.getMatricula(), enfermero.getTelefono());
    }
}
