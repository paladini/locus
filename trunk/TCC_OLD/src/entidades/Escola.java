/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * Classe que define o objeto Escola.
 * 
 * @author fernando_paladini
 */
@Entity
public class Escola {

	// TODO: Revisar código do Hibernate.
	
	// Atributos
	private String nomeEscola;

	@OneToMany(mappedBy="centro", cascade=CascadeType.ALL)
	private ArrayList<Turno> turnos = new ArrayList<Turno>();
	
	@OneToMany(mappedBy="centro", cascade=CascadeType.ALL)
	private ArrayList<Dia> dias = new ArrayList<Dia>();

	// Construtores
	public Escola() {
	}

	public Escola(String nomeEscola) {
		this.nomeEscola = nomeEscola;

	}

	// Métodos
	public void adicionarTurno(Turno turno) {
		getTurnos().add(turno);
	}

	public void adicionarDia(Dia dia) {
		getDias().add(dia);
	}

	public ArrayList<Dia> getDias() {
		return dias;
	}

	public void setDias(ArrayList<Dia> dias) {
		this.dias = dias;
	}

	public String getNomeEscola() {
		return nomeEscola;
	}

	public void setNomeEscola(String nomeEscola) {
		this.nomeEscola = nomeEscola;
	}

	public ArrayList<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(ArrayList<Turno> turnos) {
		this.turnos = turnos;
	}

}
