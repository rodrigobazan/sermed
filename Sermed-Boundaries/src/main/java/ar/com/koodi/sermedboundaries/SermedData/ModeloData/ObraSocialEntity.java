package ar.com.koodi.sermedboundaries.SermedData.ModeloData;

import javax.persistence.*;

@Entity(name = "obrasocial")
@SequenceGenerator(name="obrasocial_idobrasocial_seq", initialValue = 1, sequenceName = "obrasocial_idobrasocial_seq", allocationSize = 1)
public class ObraSocialEntity {

    @Id
    @Column(name = "idobrasocial", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "obrasocial_idobrasocial_seq")
    private Integer idObraSocial;

    @Column(name = "obrasocial")
    private String obraSocial;
    
    public ObraSocialEntity() {}

    public ObraSocialEntity(String obraSocial) {
        this.obraSocial = obraSocial;
    }
//    public ObraSocialEntity(Integer idObraSocial, String obraSocial) {
//    	this.idObraSocial = idObraSocial;
//        this.obraSocial = obraSocial;
//    }

    public Integer getIdObraSocial() {
        return idObraSocial;
    }

    public void setIdObraSocial(Integer idObraSocial) {
        this.idObraSocial = idObraSocial;
    }

    public String getNombre() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }
}
