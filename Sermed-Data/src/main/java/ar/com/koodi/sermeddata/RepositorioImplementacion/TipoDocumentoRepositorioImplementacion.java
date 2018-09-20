package ar.com.koodi.sermeddata.RepositorioImplementacion;

import Modelo.TipoDocumento;
import Repositorio.ITipoDocumentoRepositorio;
import ar.com.koodi.sermeddata.ModeloData.TipoDocumentoEntity;
import ar.com.koodi.sermeddata.RepositorioData.ITipoDocumentoRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TipoDocumentoRepositorioImplementacion implements ITipoDocumentoRepositorio {

    @Autowired
    ITipoDocumentoRepositorioCRUD iTipoDocumentoRepositorioCRUD;

    @Override
    public Collection<TipoDocumento> findAll() {
        List<TipoDocumento> tipoDocumentos = new ArrayList<>();
        this.iTipoDocumentoRepositorioCRUD.findAll().forEach(tipoDocumentoEntity -> tipoDocumentos.add(mapeoDataCore(tipoDocumentoEntity)));
        return tipoDocumentos;
    }

    @Override
    public Collection<TipoDocumento> findByNombre(String nombre) {
        List<TipoDocumento> tipoDocumentos = new ArrayList<>();
        this.iTipoDocumentoRepositorioCRUD.findByNombreContainingIgnoreCase(nombre).forEach(tipoDocumentoEntity -> tipoDocumentos.add(mapeoDataCore(tipoDocumentoEntity)));
        return tipoDocumentos;
    }

    @Override
    public TipoDocumento findByNombreUnico(String nombre) {
         return mapeoDataCore(this.iTipoDocumentoRepositorioCRUD.findByNombreEqualsIgnoreCase(nombre));
    }

    public TipoDocumento mapeoDataCore(TipoDocumentoEntity tipoDocumento) {
        if(tipoDocumento != null){
            return new TipoDocumento(tipoDocumento.getIdTipoDocumento(), tipoDocumento.getNombre());
        }
        return null;
    }

    public TipoDocumentoEntity mapeoCoreData(TipoDocumento tipoDocumento){
        TipoDocumentoEntity tipoDocumentoEntity = new TipoDocumentoEntity(tipoDocumento.getNombre());
        tipoDocumentoEntity.setIdTipoDocumento(tipoDocumento.getIdTipoDocumento());
        return tipoDocumentoEntity;
    }
}
