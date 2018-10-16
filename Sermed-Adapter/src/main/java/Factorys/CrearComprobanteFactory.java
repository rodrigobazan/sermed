package Factorys;

import Excepciones.AfiliadoDeBajaException;
import Excepciones.ComprobanteIncompletoException;
import Excepciones.FechaIncorrectaException;
import Excepciones.NumeroComprobanteIncorrectoException;
import Modelo.Comprobante;
import ModeloApi.ComprobanteDTO;

public class CrearComprobanteFactory {
    public static Comprobante mapeoCoreDTO(ComprobanteDTO comprobante) throws ComprobanteIncompletoException, FechaIncorrectaException, NumeroComprobanteIncorrectoException, AfiliadoDeBajaException {
        return  Comprobante.instancia(comprobante.idComprobante,comprobante.numeroComprobante,comprobante.afiliado,comprobante.total,comprobante.fechaCreacion,comprobante.modoDePago,comprobante.activo,comprobante.periodosAbonados);
    }
}
