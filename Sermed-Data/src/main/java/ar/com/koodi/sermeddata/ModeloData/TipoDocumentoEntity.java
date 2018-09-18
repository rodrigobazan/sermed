package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.*;

@Entity(name = "TipoDocumento")
@SequenceGenerator(name="enfermero_idtipodocumento_seq", initialValue = 1, sequenceName = "enfermero_idtipodocumento_seq", allocationSize = 1)
public class TipoDocumentoEntity {

    @Id
    @Column(name = "idTipoDocumento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enfermero_idtipodocumento_seq")
    private Integer idTipoDocumento;

    @Column(name = "nombre")
    private String nombre;

    public TipoDocumentoEntity(String nombre) {
        this.nombre = nombre;
    }

    public TipoDocumentoEntity(Integer idTipoDocumento, String nombre) {
        this.nombre = nombre;
        this.idTipoDocumento = idTipoDocumento;
    }

    public TipoDocumentoEntity() {
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
