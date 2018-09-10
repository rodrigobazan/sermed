package ar.com.koodi.sermeddata.ModeloData;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Entity(name = "Plan")
@SequenceGenerator(name="plan_idplan_seq", initialValue = 1, sequenceName = "plan_idplan_seq", allocationSize = 1)
public class PlanEntity {

    @Id
    @Column(name = "idPlan", nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plan_idplan_seq")
    private Integer idPlan;

    @Column(name = "nombrePlan")
    private String nombrePlan;

    @Column(name = "listaPrecios")
    @ElementCollection
    private Map<String, Double> listaPrecios = new HashMap<>();

    public PlanEntity(String nombrePlan, Map<String, Double> listaPrecios) {
        this.nombrePlan = nombrePlan;
        this.listaPrecios = listaPrecios;
    }

    public PlanEntity() {
    }

    public Integer getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Integer idPlan) {
        this.idPlan = idPlan;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public Map<String, Double> getListaPrecios() {
        return listaPrecios;
    }

    public void setListaPrecios(Map<String, Double> listaPrecios) {
        this.listaPrecios = listaPrecios;
    }
}
