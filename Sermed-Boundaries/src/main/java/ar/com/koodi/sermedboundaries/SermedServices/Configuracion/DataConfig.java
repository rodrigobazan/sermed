package ar.com.koodi.sermedboundaries.SermedServices.Configuracion;

import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DataConfig {

    @Bean
    public AfeccionRepositorioImplementacion afeccionRepositorioImplementacion(){
        return new AfeccionRepositorioImplementacion();
    }

    @Bean
    public AfiliadoRepositorioImplementacion afiliadoRepositorioImplementacion(){
        return new AfiliadoRepositorioImplementacion();
    }

    @Bean
    public ComprobanteRepositorioImplementacion comprobanteRepositorioImplementacion(){
        return new ComprobanteRepositorioImplementacion();
    }

    @Bean
    public EnfermeroRepositorioImplementacion enfermeroRepositorioImplementacion(){
        return new EnfermeroRepositorioImplementacion();
    }

    @Bean
    public MedicoRepositorioImplementacion medicoRepositorioImplementacion(){
        return new MedicoRepositorioImplementacion();
    }

    @Bean
    public ObraSocialRepositorioImplementacion obraSocialRepositorioImplementacion(){
        return new ObraSocialRepositorioImplementacion();
    }

    @Bean
    public PeriodoPagoRepositorioImplementacion periodoPagoRepositorioImplementacion(){
        return new PeriodoPagoRepositorioImplementacion();
    }

    @Bean
    public PersonaRepositorioImplementacion personaRepositorioImplementacion(){
        return new PersonaRepositorioImplementacion();
    }

    @Bean
    public PlanRepositorioImplementacion planRepositorioImplementacion(){
        return new PlanRepositorioImplementacion();
    }

    @Bean
    public SangreRepositorioImplementacion sangreRepositorioImplementacion(){
        return new SangreRepositorioImplementacion();
    }

    @Bean
    public TipoDocumentoRepositorioImplementacion tipoDocumentoRepositorioImplementacion(){
        return new TipoDocumentoRepositorioImplementacion();
    }

    @Bean
    public VisitaRepositorioImplementacion visitaRepositorioImplementacion(){
        return new VisitaRepositorioImplementacion();
    }
}
