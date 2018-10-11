package Factorys;

import Modelo.TipoDocumento;
import ModeloApi.TipoDocumentoDTO;

public class TipoDocumentoFactory {

    private TipoDocumentoFactory() {
    }

    static public TipoDocumentoDTO mapeoCoreDTO(TipoDocumento tipo) {
        return new TipoDocumentoDTO(tipo.getIdTipoDocumento(), tipo.getNombre());
    }

}
