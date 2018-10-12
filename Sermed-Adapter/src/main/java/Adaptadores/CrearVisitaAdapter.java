package Adaptadores;

import Excepciones.DniConPuntosException;
import Excepciones.NumeroAfiliadoIncorrectoException;
import Excepciones.PersonaIncompletaException;
import Factorys.VisitaFactory;
import Inputs.CrearVisitaInput;
import Modelo.Visita;
import ModeloApi.VisitaDTO;

public class CrearVisitaAdapter {

	private CrearVisitaInput crearVisitaInput;

	public CrearVisitaAdapter(CrearVisitaInput crearVisitaInput) {
		super();
		this.crearVisitaInput = crearVisitaInput;
	}

	public boolean crearVisita(VisitaDTO visita) throws PersonaIncompletaException, DniConPuntosException, NumeroAfiliadoIncorrectoException {
		return crearVisitaInput.crearVisita(VisitaFactory.mapeoDTOCore(visita));
	}
	
	
}
