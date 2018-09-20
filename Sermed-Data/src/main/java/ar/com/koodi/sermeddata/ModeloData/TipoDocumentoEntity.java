package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.*;

@Entity(name = "tipodocumento")
@SequenceGenerator(name="tipodocumento_idtipodocumento_seq", initialValue = 1, sequenceName = "tipodocumento_idtipodocumento_seq", allocationSize = 1)
public class TipoDocumentoEntity {

    @Id
    @Column(name = "idtipodocumento")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipodocumento_idtipodocumento_seq")
    private Integer idTipoDocumento;

    @Column(name = "nombre")
    private String nombre;

    public TipoDocumentoEntity(String nombre) {
        this.nombre = nombre;
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
