package ar.com.koodi.sermeddata.RepositorioImplementacion;

import Modelo.Afiliado;
import Modelo.Comprobante;
import Repositorio.IComprobanteRepositorio;
import ar.com.koodi.sermeddata.ModeloData.ComprobanteEntity;
import ar.com.koodi.sermeddata.ModeloData.PeriodoPagoEntity;
import ar.com.koodi.sermeddata.RepositorioData.IComprobanteRepositorioCRUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class ComprobanteImplementacion implements IComprobanteRepositorio {

    @Autowired
    IComprobanteRepositorioCRUD iComprobanteRepositorioCRUD;

    @Autowired
    AfiliadoRepositorioImplementacion afiliadoRepositorioImplementacion;

    @Override
    public Comprobante findByNumero(String numero) {
        return null;
    }

    @Override
    public boolean persist(Comprobante comprobante) {
        //return this.iComprobanteRepositorioCRUD.save(mapeoCoreData(comprobante));
        return false;
    }

    @Override
    public Collection<Comprobante> findAll() {
        return null;
    }

    @Override
    public boolean update(Comprobante comprobanteAAnular) {
        return false;
    }

    @Override
    public Collection<Comprobante> findByFechas(LocalDate fechaDesde, LocalDate fechaHasta) {
        return null;
    }

    @Override
    public Collection<Comprobante> findByAfiliado(Afiliado afiliado) {
        return null;
    }


    /*public ComprobanteEntity mapeoCoreData(Comprobante comprobante) {
        if(comprobante.getIdComprobante() == null){
            List<PeriodoPagoEntity> periodoPagoEntities = new ArrayList<>();

            return new ComprobanteEntity(comprobante.getNumero(), afiliadoRepositorioImplementacion.mapeoCoreData(comprobante.getAfiliado()),
                    comprobante.getTotal(), comprobante.getFechaCreacion(), comprobante.getModoPago(), comprobante.getActivo(), )
        }

    }*/

}
