/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.Entity;

/**
 * Classe que define o objeto Sala.
 * @author fernando_paladini
 */
@Entity
public class Sala {
    
    private int id;
    private String nome;
    private boolean usar1 = false;
	private boolean usar2 = false;
	private boolean usar3 = false;
    
    // Construtores
	public Sala() {
		super();
	}
	
	public Sala(String nome, int id) {
		super();
		this.nome = nome;
		this.id = id;
	}
    
    public Sala(String nome){
        this.nome = nome;
    }
    
    public Sala(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // M�todos
    
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

	public boolean isUsar1() {
		return usar1;
	}

	public void setUsar1(boolean usar1) {
		this.usar1 = usar1;
	}

	public boolean isUsar2() {
		return usar2;
	}

	public void setUsar2(boolean usar2) {
		this.usar2 = usar2;
	}

	public boolean isUsar3() {
		return usar3;
	}

	public void setUsar3(boolean usar3) {
		this.usar3 = usar3;
	}

    
    
    
}
