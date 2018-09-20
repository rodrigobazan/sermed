package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.*;

@Entity(name = "sangre")
@SequenceGenerator(name="sangre_idsangre_seq", initialValue = 1, sequenceName = "sangre_idsangre_seq", allocationSize = 1)
public class SangreEntity {

    @Id
    @Column(name = "idsangre", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sangre_idsangre_seq")
    private Integer idSangre;

    @Column(name = "grupo")
    private String grupo;

    @Column(name = "factor")
    private String factor;

    public SangreEntity() {
    }

    public SangreEntity(String grupo, String factor) {
        this.grupo = grupo;
        this.factor = factor;
    }

    public int getIdSangre() {
        return idSangre;
    }

    public void setIdSangre(Integer idSangre) {
        this.idSangre = idSangre;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFactor() {
        return factor;
    }

    public void setFactor(String factor) {
        this.factor = factor;
    }
}
