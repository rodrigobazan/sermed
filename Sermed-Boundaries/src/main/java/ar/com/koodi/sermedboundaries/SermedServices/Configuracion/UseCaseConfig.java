package ar.com.koodi.sermedboundaries.SermedServices.Configuracion;

import Interactor.ConsultarAfeccionUseCase;
import Repositorio.IAfeccionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Autowired
    private IAfeccionRepositorio iAfeccionRepositorio;

    @Bean
    public ConsultarAfeccionUseCase consultarAfeccionUseCase(){
        return new ConsultarAfeccionUseCase(iAfeccionRepositorio);
    }
}
