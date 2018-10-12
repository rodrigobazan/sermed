package Factorys;

import Excepciones.*;
import Modelo.Afiliado;
import Modelo.Persona;
import ModeloApi.AfiliadoDTO;
import ModeloApi.PersonaDTO;

import java.util.ArrayList;
import java.util.Collection;

public class AfiliadoFactory {

    private AfiliadoFactory() {
    }

    public static Afiliado mapeoDTOCore(AfiliadoDTO afiliadoDTO) throws NumeroAfiliadoIncorrectoException, PersonaIncompletaException, DniConPuntosException, PlanIncompletoException, AfiliadoSinTitularException, AfiliadoSinPlanException {
        Collection<Persona> miembros = new ArrayList<>();
        afiliadoDTO.miembros.forEach(personaDTO -> {
            try {
                miembros.add(PersonaFactory.mapeoDTOCore(personaDTO));
            } catch (DniConPuntosException e) {
                e.printStackTrace();
            } catch (PersonaIncompletaException e) {
                e.printStackTrace();
            } catch (NumeroAfiliadoIncorrectoException e) {
                e.printStackTrace();
            }
        });
        return Afiliado.instancia(afiliadoDTO.idAfiliado, afiliadoDTO.fechaAfiliacion, afiliadoDTO.numeroAfiliado, PersonaFactory.mapeoDTOCore(afiliadoDTO.titular),
                miembros, afiliadoDTO.activo, afiliadoDTO.fechaDeBaja, afiliadoDTO.diaDelMesPagoAcordado, PlanFactory.mapeoDTOCore(afiliadoDTO.plan));
    }

    public static AfiliadoDTO mapeoCoreDTO(Afiliado afiliado){
        Collection<PersonaDTO> miembros = new ArrayList<>();
        afiliado.getMiembros().forEach(persona -> miembros.add(PersonaFactory.mapeoCoreDTO(persona)));
        return new AfiliadoDTO(afiliado.getIdAfiliado(), afiliado.getFechaAfiliacion(), afiliado.getNumeroAfiliado(), miembros, PersonaFactory.mapeoCoreDTO(afiliado.getTitular()),
                afiliado.getActivo(), afiliado.getFechaDeBaja(), afiliado.getDiaDelMesPagoAcordado(), PlanFactory.mapeoCoreDTO(afiliado.getPlan()));
    }
}
