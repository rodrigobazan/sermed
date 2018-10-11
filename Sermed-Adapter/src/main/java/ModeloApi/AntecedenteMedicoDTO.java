package ModeloApi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AntecedenteMedicoDTO {

    @JsonProperty("idAntecedenteMedico")
    public final Integer idAntecedenteMedico;

    @JsonProperty("afeccion")
    public final AfeccionDTO afeccion;

    @JsonProperty("observacion")
    public final String observacion;

    @JsonCreator
    public AntecedenteMedicoDTO(@JsonProperty("idAntecedenteMedico") Integer idAntecedenteMedico,
                                @JsonProperty("afeccion") AfeccionDTO afeccion,
                                @JsonProperty("observacion") String observacion) {
        this.idAntecedenteMedico = idAntecedenteMedico;
        this.afeccion = afeccion;
        this.observacion = observacion;
    }
}
