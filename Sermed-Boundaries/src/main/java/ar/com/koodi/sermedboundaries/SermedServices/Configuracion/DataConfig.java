package ar.com.koodi.sermedboundaries.SermedServices.Configuracion;

import ar.com.koodi.sermedboundaries.SermedData.RepositorioImplementacion.AfeccionRepositorioImplementacion;
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
}
