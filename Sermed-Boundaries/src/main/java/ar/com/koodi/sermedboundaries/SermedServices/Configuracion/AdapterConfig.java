package ar.com.koodi.sermedboundaries.SermedServices.Configuracion;

import Adaptadores.ConsultarAfeccionAdapter;
import Inputs.ConsultarAfeccionInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Autowired
    private ConsultarAfeccionInput consultarAfeccionInput;

    @Bean
    public ConsultarAfeccionAdapter consultarAfeccionAdapter(){
        return new ConsultarAfeccionAdapter(consultarAfeccionInput);
    }
}
