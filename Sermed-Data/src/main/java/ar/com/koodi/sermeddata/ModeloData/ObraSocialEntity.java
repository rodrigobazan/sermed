package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.*;

@Entity(name = "ObraSocial")
@SequenceGenerator(name="obrasocial_idobrasocial_seq", initialValue = 1, sequenceName = "obrasocial_idobrasocial_seq", allocationSize = 1)
public class ObraSocialEntity {

    @Id
    @Column(name = "idObraSocial", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obrasocial_idobrasocial_seq")
    private Integer idObraSocial;

    @Column(name = "obrasocial")
    private String obraSocial;

    public ObraSocialEntity(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public Integer getIdObraSocial() {
        return idObraSocial;
    }

    public void setIdObraSocial(Integer idObraSocial) {
        this.idObraSocial = idObraSocial;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }
}
