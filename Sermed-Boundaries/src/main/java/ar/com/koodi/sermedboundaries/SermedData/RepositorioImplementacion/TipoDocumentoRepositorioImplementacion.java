package ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion;

import Modelo.TipoDocumento;
import Repositorio.ITipoDocumentoRepositorio;
import ar.com.koodi.sermedboundaries.SermedData.ModeloData.TipoDocumentoEntity;
import ar.com.koodi.sermedboundaries.SermedData.RepositorioData.ITipoDocumentoRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class TipoDocumentoRepositorioImplementacion implements ITipoDocumentoRepositorio {

    @Autowired
    ITipoDocumentoRepositorioCRUD iTipoDocumentoRepositorioCRUD;

    @Override
    @Transactional(readOnly = true)
    public Collection<TipoDocumento> findAll() {
        List<TipoDocumento> tipoDocumentos = new ArrayList<>();
        this.iTipoDocumentoRepositorioCRUD.findAll().forEach(tipoDocumentoEntity -> tipoDocumentos.add(mapeoDataCore(tipoDocumentoEntity)));
        return tipoDocumentos;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<TipoDocumento> findByNombre(String nombre) {
        List<TipoDocumento> tipoDocumentos = new ArrayList<>();
        this.iTipoDocumentoRepositorioCRUD.findByNombreContainingIgnoreCase(nombre).forEach(tipoDocumentoEntity -> tipoDocumentos.add(mapeoDataCore(tipoDocumentoEntity)));
        return tipoDocumentos;
    }

    @Override
    @Transactional(readOnly = true)
    public TipoDocumento findByNombreUnico(String nombre) {
        return mapeoDataCore(this.iTipoDocumentoRepositorioCRUD.findByNombreEqualsIgnoreCase(nombre));
    }

    public TipoDocumento mapeoDataCore(TipoDocumentoEntity tipoDocumento) {
        if (tipoDocumento != null) {
            return new TipoDocumento(tipoDocumento.getIdTipoDocumento(), tipoDocumento.getNombre());
        }
        return null;
    }

    public TipoDocumentoEntity mapeoCoreData(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getIdTipoDocumento() == null) {
            return new TipoDocumentoEntity(tipoDocumento.getNombre());
        }
        return this.iTipoDocumentoRepositorioCRUD.findByNombreEqualsIgnoreCase(tipoDocumento.getNombre());

    }
}
