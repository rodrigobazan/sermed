package Factorys;

import Modelo.TipoDocumento;
import ModeloApi.TipoDocumentoDTO;

public class TipoDocumentoFactory {

    private TipoDocumentoFactory() {
    }

    public static TipoDocumentoDTO mapeoCoreDTO(TipoDocumento tipo) {
        return new TipoDocumentoDTO(tipo.getIdTipoDocumento(), tipo.getNombre());
    }

    public static TipoDocumento mapeoDTOCore(TipoDocumentoDTO tipoDocumento){
        return new TipoDocumento(tipoDocumento.idTipoDocumento, tipoDocumento.nombre);
    }

}
