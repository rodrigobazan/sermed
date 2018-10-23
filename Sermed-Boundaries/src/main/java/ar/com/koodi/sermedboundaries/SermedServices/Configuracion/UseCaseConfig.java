package ar.com.koodi.sermedboundaries.SermedServices.Configuracion;

import Adaptadores.CrearAfiliadoAdapter;
import Adaptadores.CrearComprobantePagoAdapter;
import Adaptadores.CrearMedicoAdapter;
import Adaptadores.CrearObraSocialAdapter;
import Interactor.*;
import Repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.management.MXBean;

@Configuration
public class UseCaseConfig {

    @Autowired
    private IAfeccionRepositorio iAfeccionRepositorio;

    @Autowired
    private IAfiliadoRepositorio iAfiliadoRepositorio;

    @Autowired
    private IComprobanteRepositorio iComprobanteRepositorio;

    @Autowired
    private IEnfermeroRepositorio iEnfermeroRepositorio;

    @Autowired
    private IMedicoRepositorio iMedicoRepositorio;

    @Autowired
    private IObraSocialRepositorio iObraSocialRepositorio;

    @Autowired
    private IPeriodoPagoRepositorio iPeriodoPagoRepositorio;

    @Autowired
    private IPersonaRepositorio iPersonaRepositorio;

    @Autowired
    private IPlanRepositorio iPlanRepositorio;

    @Autowired
    private ISangreRepositorio iSangreRepositorio;

    @Autowired
    private ITipoDocumentoRepositorio iTipoDocumentoRepositorio;

    @Autowired
    private IVisitaRepositorio iVisitaRepositorio;

    @Bean
    public AfiliarPersonaUseCase afiliarPersonaUseCase(){
        return new AfiliarPersonaUseCase(iAfiliadoRepositorio);
    }

    @Bean
    public AnularComprobantesUseCase anularComprobantesUseCase(){
        return new AnularComprobantesUseCase(iComprobanteRepositorio);
    }

    @Bean
    public BuscarPersonaEntreAfiliadosUseCase buscarPersonaEntreAfiliadosUseCase(){
        return new BuscarPersonaEntreAfiliadosUseCase(iAfiliadoRepositorio);
    }

    @Bean
    public BuscarPersonaEntreAfiliadosDeBajaUseCase buscarPersonaEntreAfiliadosDeBajaUseCase(){
        return new BuscarPersonaEntreAfiliadosDeBajaUseCase(iAfiliadoRepositorio);
    }

    @Bean
    public CambiarPlanAfiliadoUseCase cambiarPlanAfiliadoUseCase(){
        return new CambiarPlanAfiliadoUseCase(iAfiliadoRepositorio);
    }

    @Bean
    public ConsultarAfeccionUseCase consultarAfeccionUseCase(){
        return new ConsultarAfeccionUseCase(iAfeccionRepositorio);
    }

    @Bean
    public ConsultarAfiliadosMorososUseCase consultarAfiliadosMorososUseCase(){
        return new ConsultarAfiliadosMorososUseCase(iAfiliadoRepositorio, iComprobanteRepositorio);
    }

    @Bean
    public ConsultarAfiliadoUseCase consultarAfiliadoUseCase(){
        return new ConsultarAfiliadoUseCase(iAfiliadoRepositorio);
    }

    @Bean
    public ConsultarComprobanteDeAfiliadoUseCase consultarComprobanteDeAfiliadoUseCase(){
        return  new ConsultarComprobanteDeAfiliadoUseCase(iComprobanteRepositorio);
    }


    @Bean
    public ConsultarComprobantesUseCase consultarComprobantesUseCase(){
        return new ConsultarComprobantesUseCase(iComprobanteRepositorio);
    }

    @Bean
    public  ConsultarEnfermeroUseCase consultarEnfermeroUseCase(){
        return  new ConsultarEnfermeroUseCase(iEnfermeroRepositorio);
    }

    @Bean
    public ConsultarMedicoUseCase consultarMedicoUseCase(){
        return new ConsultarMedicoUseCase(iMedicoRepositorio);
    }

    @Bean
    public ConsultarObrasSocialesUseCase consultarObrasSociales(){
        return new ConsultarObrasSocialesUseCase(iObraSocialRepositorio);
    }

    @Bean
    public ConsultarPeriodoPagoUseCase consultarPeriodoPagoUseCase(){
        return new ConsultarPeriodoPagoUseCase(iPeriodoPagoRepositorio);
    }

    @Bean
    public ConsultarPersonaUseCase consultarPersonaUseCase(){
        return new ConsultarPersonaUseCase(iPersonaRepositorio);
    }

    @Bean
    public ConsultarPlanUseCase consultarPlanUseCase(){
        return new ConsultarPlanUseCase(iPlanRepositorio);
    }

    @Bean
    public ConsultarSangreUseCase consultarSangreUseCase(){
        return  new ConsultarSangreUseCase(iSangreRepositorio);
    }

    @Bean
    public ConsultarTipoDocumentoUseCase consultarTipoDocumentoUseCase (){
        return new ConsultarTipoDocumentoUseCase(iTipoDocumentoRepositorio);
    }

    @Bean
    public ConsultarVisitasDePersonaUseCase consultarVisitasDePersonaUseCase(){
        return new ConsultarVisitasDePersonaUseCase(iVisitaRepositorio);
    }

    @Bean
    public ConsultarVisitaUseCase consultarVisitaUseCase(){
        return new ConsultarVisitaUseCase(iVisitaRepositorio);
    }
    @Bean
    public CrearAfiliadoUseCase crearAfiliadoUseCase(){
        return new CrearAfiliadoUseCase(iAfiliadoRepositorio);
    }
    @Bean
    public CrearComprobantePagoUseCase crearComprobantePagoUseCase(){
        return new CrearComprobantePagoUseCase(iComprobanteRepositorio);
    }
    @Bean
    public CrearEnfermeroUseCase crearEnfermeroUseCase(){
        return new CrearEnfermeroUseCase(iEnfermeroRepositorio);
    }
    @Bean
    public CrearMedicoUseCase crearMedicoUseCase(){
        return  new CrearMedicoUseCase(iMedicoRepositorio);
    }
    @Bean
    public CrearObraSocialUseCase crearObraSocialUseCase(){
        return new CrearObraSocialUseCase(iObraSocialRepositorio);
    }

    @Bean
    public CrearPersonaUseCase crearPersonaUseCase(){
        return new CrearPersonaUseCase(iPersonaRepositorio);
    }

    @Bean
    public CrearPlanUseCase crearPlanUseCase(){
        return new CrearPlanUseCase(iPlanRepositorio);
    }

    @Bean
    public CrearVisitaUseCase crearVisitaUseCase(){
        return new CrearVisitaUseCase(iVisitaRepositorio);
    }

    @Bean
    public DarBajaAfiliadoUseCase darBajaAfiliadoUseCase(){
        return new DarBajaAfiliadoUseCase(iAfiliadoRepositorio);
    }

    @Bean
    public DesafiliarPersonaUseCase desafiliarPersonaUseCase(){
        return new DesafiliarPersonaUseCase(iAfiliadoRepositorio,iPersonaRepositorio);
    }

    @Bean
    public GenerarComprobanteAfiliadoUseCase generarComprobanteAfiliadoUseCase(){
        return new GenerarComprobanteAfiliadoUseCase(iComprobanteRepositorio);
    }

    @Bean
    public GenerarFichaAfiliadoUseCase generarFichaAfiliadoUseCase(){
        return new GenerarFichaAfiliadoUseCase(iAfiliadoRepositorio);
    }

    @Bean
    public GenerarHistoriaClinicaPersonaUseCase generarHistoriaClinicaPersonaUseCase(){
        return new GenerarHistoriaClinicaPersonaUseCase(iPersonaRepositorio, iVisitaRepositorio);
    }

    @Bean
    public ModificarEnfermeroUseCase modificarEnfermeroUseCase(){
        return new ModificarEnfermeroUseCase(iEnfermeroRepositorio);
    }

    @Bean
    public ModificarMedicoUseCase modificarMedicoUseCase(){
        return new ModificarMedicoUseCase(iMedicoRepositorio);
    }

    @Bean
    public ModificarObraSocialUseCase modificarObraSocialUseCase(){
        return new ModificarObraSocialUseCase(iObraSocialRepositorio);
    }

    @Bean
    public ModificarPersonaUseCase modificarPersonaUseCase(){
        return new ModificarPersonaUseCase(iPersonaRepositorio);
    }

    @Bean
    public ModificarPlanUseCase modificarPlanUseCase(){
        return new ModificarPlanUseCase(iPlanRepositorio);
    }
}

