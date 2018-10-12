package Adaptadores;

import Excepciones.AfiliadoNoExisteException;
import Factorys.AfiliadoFactory;
import Inputs.ConsultarAfiliadoInput;
import ModeloApi.AfiliadoDTO;

import java.util.ArrayList;
import java.util.List;

public class ConsultarAfiliadoAdapter {
    private ConsultarAfiliadoInput consultarAfiliadoInput;

    public ConsultarAfiliadoAdapter(ConsultarAfiliadoInput consultarAfiliadoInput) {
        this.consultarAfiliadoInput = consultarAfiliadoInput;
    }

    public List<AfiliadoDTO> consultarAfiliados() {
        List<AfiliadoDTO> afiliados = new ArrayList<>();
        consultarAfiliadoInput.consultarAfiliados().forEach(afiliado -> afiliados.add(AfiliadoFactory.mapeoCoreDTO(afiliado)));
        return afiliados;
    }

    public List<AfiliadoDTO> consultarAfiliadosPorNumero(String numero) {
        List<AfiliadoDTO> afiliados = new ArrayList<>();
        consultarAfiliadoInput.consultarAfiliadosPorNumero(numero).forEach(afiliado -> afiliados.add(AfiliadoFactory.mapeoCoreDTO(afiliado)));
        return afiliados;
    }

    public AfiliadoDTO consultarAfiliadoPorNumero(String numero) throws AfiliadoNoExisteException {
        return AfiliadoFactory.mapeoCoreDTO(consultarAfiliadoInput.consultarAfiliadoPorNumero(numero));
    }
}
