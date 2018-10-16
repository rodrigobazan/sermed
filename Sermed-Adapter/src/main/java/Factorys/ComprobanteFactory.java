package Factorys;

import Modelo.Comprobante;
import ModeloApi.ComprobanteDTO;

public class ComprobanteFactory {

    private ComprobanteFactory() {
    }

    public static ComprobanteDTO mapeoCoreDTO(Comprobante comprobante){
        return new ComprobanteDTO(comprobante.getIdComprobante(), comprobante.getNumero(), AfiliadoFactory.mapeoCoreDTO(comprobante.getAfiliado()), comprobante.getTotal(), comprobante.getFechaCreacion(),
                comprobante.getModoPago(), comprobante.getActivo(), comprobante.getPeriodosAbonados());
    }
}
