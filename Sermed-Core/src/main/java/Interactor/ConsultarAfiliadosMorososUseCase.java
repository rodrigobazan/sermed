package Interactor;

import Inputs.ConsultarAfiliadosMorososInput;
import Modelo.Afiliado;
import Modelo.Comprobante;
import Repositorio.IAfiliadoRepositorio;
import Repositorio.IComprobanteRepositorio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultarAfiliadosMorososUseCase implements ConsultarAfiliadosMorososInput {

    private IAfiliadoRepositorio repositoriosAfiliado;
    private IComprobanteRepositorio repositorioComprobante;


    public ConsultarAfiliadosMorososUseCase(IAfiliadoRepositorio repositoriosAfiliado, IComprobanteRepositorio repositoriosComprobante) {
        this.repositoriosAfiliado = repositoriosAfiliado;
        this.repositorioComprobante = repositoriosComprobante;
    }

    @Override
    public List<Afiliado> consultarAfiliadosMorosos(LocalDate fecha) {
        ConsultarComprobanteDeAfiliadoUseCase consultarComprobanteDeAfiliadoUseCase = new ConsultarComprobanteDeAfiliadoUseCase(repositorioComprobante);
        List<Afiliado> afiliados = (List<Afiliado>) this.repositoriosAfiliado.findAllActivos();
        List<Afiliado> morosos = new ArrayList<>();
        if (!afiliados.isEmpty()) {
            for (Afiliado afiliado : afiliados) {
                List<Comprobante> comprobantesDeAfiliado = consultarComprobanteDeAfiliadoUseCase.consultarTodosLosComprobantes(afiliado);
                if (comprobantesDeAfiliado.isEmpty())
                    morosos.add(afiliado);
                else {
                    if (!verificarPagoDePeriodo(comprobantesDeAfiliado, fecha.getMonthValue(), fecha.getDayOfMonth()) && afiliado.vencioPlazoPago(fecha.getDayOfMonth()))
                        morosos.add(afiliado);
                }
            }
        }
        return morosos;
    }

    private boolean verificarPagoDePeriodo(List<Comprobante> comprobantes, int mes, int anio) {
        return comprobantes.stream().anyMatch(comprobante -> comprobante.contienePeriodo(mes, anio));
    }
}
