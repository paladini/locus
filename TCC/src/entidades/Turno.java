/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;

/**
 *
 * @author silvio
 */
public class Turno {
    
    // Atributos
    private int id;
    private String nome;
    private ArrayList<Dia> listaDias = new ArrayList<Dia>();

    // Construtores
    public Turno() {
		super();
		Instituicao i = Instituicao.getInstance();
		i.addListaPeriodos(this);
	}
    
    public Turno(String nome) {
    	super();
    	this.nome = nome;
    	Instituicao i = Instituicao.getInstance();
		i.addListaPeriodos(this);
    }
    
    public Turno(String nome, int id) {
		super();
		this.nome = nome;
		this.id = id;
		Instituicao i = Instituicao.getInstance();
		i.addListaPeriodos(this);
	}
    
	public Turno(int id, String nome, ArrayList<Dia> listaDias) {
		super();
		this.id = id;
		this.nome = nome;
		this.listaDias = listaDias;
		Instituicao i = Instituicao.getInstance();
		i.addListaPeriodos(this);
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
    
    public ArrayList<Dia> getListaDias() {
		return listaDias;
	}

	public void setListaDias(ArrayList<Dia> listaDias) {
		this.listaDias = listaDias;
	}
    
    
}
