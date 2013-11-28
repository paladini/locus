/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;
import java.util.ArrayList;

import javax.persistence.Entity;
/**
 * Classe que define a entidade Dia.
 * @author fernando_paladini
 */
@Entity
public class Dia {
    
    // Vari�veis
    private int id;
    private String nome;
    
    /**
     * Apenas para facilitar o manuseio dos Dias e Turnos no banco de dados.
     * 
     * Ativo = true:
     *    Este turno faz parte do calendário escolar da instituição.
     *    
     * Ativo = false:
     *    Este turno não faz parte do calendário escolar da instituição
     */
    private boolean ativo;
    
    private ArrayList<String> listaTurnosSelecionados = new ArrayList<String>(); // apenas para o JSF
    private String[] vetorTurnosSelecionados; // teste
    private Turno t1;
	private Turno t2;
	private Turno t3;

    // Construtores
    public Dia() {
    	super();
    }
    
    public Dia(String nome, int id) {
		super();
		this.nome = nome;
		this.id = id;
	}
    
    public Dia(String nome, int id, Turno t1, Turno t2, Turno t3) {
		super();
		this.nome = nome;
		this.id = id;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
	}
    
    public Dia(String nome) {
    	super();
    	this.nome = nome;
    }
    
    public Dia(int id, String nome) {
    	super();
    	this.id = id;
        this.nome = nome;
    }
    
    // Métodos
    
    public String texto() {
		if (nome==null) {
			 return "null";
		}
    	return nome;
	}
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public Turno getT1() {
		return t1;
	}

	public void setT1(Turno t1) {
		this.t1 = t1;
		vetorTurnosSelecionados[0] = t1.getNome();
		listaTurnosSelecionados.add(0, t1.getNome());
	}

	public Turno getT2() {
		return t2;
	}

	public void setT2(Turno t2) {
		this.t2 = t2;
		vetorTurnosSelecionados[1] = t2.getNome();
		listaTurnosSelecionados.add(1, t2.getNome());
	}

	public Turno getT3() {
		return t3;
	}

	public void setT3(Turno t3) {
		this.t3 = t3;
		vetorTurnosSelecionados[2] = t3.getNome();
		listaTurnosSelecionados.add(2, t3.getNome());
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void instanciarTurnosSelecionados(int tamanho){
		this.vetorTurnosSelecionados = new String[tamanho];
	}
	
//	public ArrayList<Turno> getListaTurnos() {
//		return listaTurnos;
//	}
//
//	public void setListaTurnos(ArrayList<Turno> listaTurnos) {
//		this.listaTurnos = listaTurnos;
//	}

	public ArrayList<String> getListaTurnosSelecionados() {
		return listaTurnosSelecionados;
	}

	public void setListaTurnosSelecionados(ArrayList<String> listaTurnosSelecionados) {
		this.listaTurnosSelecionados = listaTurnosSelecionados;
	}

	public String[] getVetorTurnosSelecionados() {
		return vetorTurnosSelecionados;
	}

	public void setVetorTurnosSelecionados(String[] vetorTurnosSelecionados) {
		this.vetorTurnosSelecionados = vetorTurnosSelecionados;
	}

	
	
	
}
