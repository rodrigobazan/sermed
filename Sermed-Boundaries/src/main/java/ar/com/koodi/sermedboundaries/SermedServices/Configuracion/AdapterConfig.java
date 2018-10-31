package ar.com.koodi.sermedboundaries.SermedServices.Configuracion;

import Adaptadores.*;
import Inputs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdapterConfig {

    @Autowired
    private AfiliarPersonaInput afiliarPersonaInput;

    @Autowired
    private AnularComprobanteInput anularComprobanteInput;

    @Autowired
    private BuscarPersonaEntreAfiliadosDeBajaInput buscarPersonaEntreAfiliadosDeBajaInput;

    @Autowired
    private BuscarPersonaEntreAfiliadosInput buscarPersonaEntreAfiliadosInput;

    @Autowired
    private CambiarPlanAfiliadoInput cambiarPlanAfiliadoInput;

    @Autowired
    private ConsultarAfeccionInput consultarAfeccionInput;

    @Autowired
    private ConsultarAfiliadoInput consultarAfiliadoInput;

    @Autowired
    private ConsultarAfiliadosMorososInput consultarAfiliadosMorososInput;

    @Autowired
    private ConsultarComprobanteDeAfiliadoInput consultarComprobanteDeAfiliadoInput;

    @Autowired
    private ConsultarComprobantesInput consultarComprobantesInput;

    @Autowired
    private ConsultarEnfermeroInput consultarEnfermeroInput;

    @Autowired
    private ConsultarMedicoInput consultarMedicoInput;

    @Autowired
    private ConsultarObrasSocialesInput consultarObrasSocialesInput;

    @Autowired
    private ConsultarPeriodoPagoInput consultarPeriodoPagoInput;

    @Autowired
    private ConsultarPersonaInput consultarPersonaInput;

    @Autowired
    private ConsultarPlanInput consultarPlanInput;

    @Autowired
    private ConsultarSangreInput consultarSangreInput;

    @Autowired
    private ConsultarTipoDocumentoInput consultarTipoDocumentoInput;

    @Autowired
    private ConsultarVisitaInput consultarVisitaInput;

    @Autowired
    private ConsultarVisitasDePersonaInput consultarVisitasDePersonaInput;

    @Autowired
    private CrearAfiliadoInput crearAfiliadoInput;

    @Autowired
    private CrearComprobanteDePagoInput crearComprobanteDePagoInput;

    @Autowired
    private CrearEnfermeroInput crearEnfermeroInput;

    @Autowired
    private CrearMedicoInput crearMedicoInput;

    @Autowired
    private CrearObraSocialInput crearObraSocialInput;

    @Autowired
    private CrearPersonaInput crearPersonaInput;

    @Autowired
    private CrearPlanInput crearPlanInput;

    @Autowired
    private CrearVisitaInput crearVisitaInput;

    @Autowired
    private DarBajaAfiliadoInput darBajaAfiliadoInput;

    @Autowired
    private DesafiliarPersonaInput desafiliarPersonaInput;

    @Autowired
    private GenerarComprobanteAfiliadoInput generarComprobanteAfiliadoInput;

    @Autowired
    private GenerarHistoriaClinicaPersonaInput generarHistoriaClinicaPersonaInput;
    
    @Autowired
    private GenerarFichaAfiliadoInput generarFichaAfiliadoInput;

    @Autowired
    private ModificarEnfermeroInput modificarEnfermeroInput;

    @Autowired
    private ModificarMedicoInput modificarMedicoInput;

    @Autowired
    private ModificarObraSocialInput modificarObraSocialInput;

    @Autowired
    private ModificarPersonaInput modificarPersonaInput;

    @Autowired
    private ModificarPlanInput modificarPlanInput;

    @Bean
    public GenerarFichaAfiliadoAdapter GenerarFichaAfiliadoAdapter() {
    	return new GenerarFichaAfiliadoAdapter(generarFichaAfiliadoInput);
    }

    @Bean
    public AfiliarPersonaAdapter afiliarPersonaAdapter(){
        return new AfiliarPersonaAdapter(afiliarPersonaInput);
    }

    @Bean
    public AnularComprobanteAdapter anularComprobanteAdapter(){
        return new AnularComprobanteAdapter(anularComprobanteInput);
    }

    @Bean
    public BuscarPersonaEntreAfiliadosDeBajaAdapter buscarPersonaEntreAfiliadosDeBajaAdapter(){
        return new BuscarPersonaEntreAfiliadosDeBajaAdapter(buscarPersonaEntreAfiliadosDeBajaInput);
    }

    @Bean
    public BuscarPersonaEntreAfiliadosAdapter buscarPersonaEntreAfiliadosAdapter(){
        return new BuscarPersonaEntreAfiliadosAdapter(buscarPersonaEntreAfiliadosInput);
    }

    @Bean
    public CambiarPlanAfiliadoAdapter cambiarPlanAfiliadoAdapter(){
        return new CambiarPlanAfiliadoAdapter(cambiarPlanAfiliadoInput);
    }

    @Bean
    public ConsultarAfeccionAdapter consultarAfeccionAdapter(){
        return new ConsultarAfeccionAdapter(consultarAfeccionInput);
    }

    @Bean
    public ConsultarAfiliadoAdapter consultarAfiliadoAdapter(){
        return new ConsultarAfiliadoAdapter(consultarAfiliadoInput);
    }

    @Bean
    public ConsultarAfiliadosMorososAdapter consultarAfiliadosMorososAdapter(){
        return new ConsultarAfiliadosMorososAdapter(consultarAfiliadosMorososInput);
    }

    @Bean
    public ConsultarComprobanteDeAfiliadoAdapter consultarComprobanteDeAfiliadoAdapter(){
        return new ConsultarComprobanteDeAfiliadoAdapter(consultarComprobanteDeAfiliadoInput);
    }

    @Bean
    public ConsultarComprobantesAdapter consultarComprobantesAdapter(){
        return new ConsultarComprobantesAdapter(consultarComprobantesInput);
    }

    @Bean
    public ConsultarEnfermeroAdapter consultarEnfermeroAdapter(){
        return new ConsultarEnfermeroAdapter(consultarEnfermeroInput);
    }

    @Bean
    public ConsultarMedicoAdapter consultarMedicoAdapter(){
        return new ConsultarMedicoAdapter(consultarMedicoInput);
    }

    @Bean
    public ConsultarObrasSocialesAdapter consultarObrasSocialesAdapter(){
        return new ConsultarObrasSocialesAdapter(consultarObrasSocialesInput);
    }

    @Bean
    public ConsultarPeriodoPagoAdapter consultarPeriodoPagoAdapter(){
        return new ConsultarPeriodoPagoAdapter(consultarPeriodoPagoInput);
    }

    @Bean
    public ConsultarPersonaAdapter consultarPersonaAdapter(){
        return new ConsultarPersonaAdapter(consultarPersonaInput);
    }

    @Bean
    public ConsultarPlanAdapter consultarPlanAdapter(){
        return new ConsultarPlanAdapter(consultarPlanInput);
    }

    @Bean
    public ConsultarSangreAdapter consultarSangreAdapter(){
        return new ConsultarSangreAdapter(consultarSangreInput);
    }

    @Bean
    public ConsultarTipoDocumentoAdapter consultarTipoDocumentoAdapter(){
        return new ConsultarTipoDocumentoAdapter(consultarTipoDocumentoInput);
    }

    @Bean
    public ConsultarVisitaAdapter consultarVisitaAdapter(){
        return new ConsultarVisitaAdapter(consultarVisitaInput);
    }

    @Bean
    public ConsultarVisitasDePersonaAdapter consultarVisitasDePersonaAdapter(){
        return new ConsultarVisitasDePersonaAdapter(consultarVisitasDePersonaInput);
    }

    @Bean
    public CrearAfiliadoAdapter crearAfiliadoAdapter(){
        return new CrearAfiliadoAdapter(crearAfiliadoInput);
    }

    @Bean
    public CrearComprobantePagoAdapter crearComprobantePagoAdapter(){
        return new CrearComprobantePagoAdapter(crearComprobanteDePagoInput);
    }

    @Bean
    public CrearEnfermeroAdapter crearEnfermeroAdapter(){
        return new CrearEnfermeroAdapter(crearEnfermeroInput);
    }

    @Bean
    public CrearMedicoAdapter crearMedicoAdapter(){
        return new CrearMedicoAdapter(crearMedicoInput);
    }

    @Bean
    public CrearObraSocialAdapter crearObraSocialAdapter(){
        return new CrearObraSocialAdapter(crearObraSocialInput);
    }

    @Bean
    public CrearPersonaAdapter crearPersonaAdapter(){
        return new CrearPersonaAdapter(crearPersonaInput);
    }

    @Bean
    public CrearPlanAdapter crearPlanAdapter(){
        return new CrearPlanAdapter(crearPlanInput);
    }

    @Bean
    public CrearVisitaAdapter crearVisitaAdapter(){
        return new CrearVisitaAdapter(crearVisitaInput);
    }

    @Bean
    public DarBajaAfiliadoAdapter darBajaAfiliadoAdapter(){
        return new DarBajaAfiliadoAdapter(darBajaAfiliadoInput);
    }

    @Bean
    public DesafiliarPersonaAdapter desafiliarPersonaAdapter(){
        return new DesafiliarPersonaAdapter(desafiliarPersonaInput);
    }

    @Bean
    public GenerarComprobanteAfiliadoAdapter generarComprobanteAfiliadoAdapter(){
        return new GenerarComprobanteAfiliadoAdapter(generarComprobanteAfiliadoInput);
    }

    @Bean
    public GenerarHistoriaClinicaPersonaAdapter generarHistoriaClinicaPersonaAdapter(){
        return new GenerarHistoriaClinicaPersonaAdapter(generarHistoriaClinicaPersonaInput);
    }

    @Bean
    public ModificarEnfermeroAdapter modificarEnfermeroAdapter(){
        return new ModificarEnfermeroAdapter(modificarEnfermeroInput);
    }

    @Bean
    public ModificarMedicoAdapter modificarMedicoAdapter(){
        return new ModificarMedicoAdapter(modificarMedicoInput);
    }

    @Bean
    public ModificarObraSocialAdapter modificarObraSocialAdapter(){
        return new ModificarObraSocialAdapter(modificarObraSocialInput);
    }

    @Bean
    public ModificarPersonaAdapter modificarPersonaAdapter(){
        return new ModificarPersonaAdapter(modificarPersonaInput);
    }

    @Bean
    public ModificarPlanAdapter modificarPlanAdapter(){
        return new ModificarPlanAdapter(modificarPlanInput);
    }

}
