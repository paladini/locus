/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;
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
    private Turno t1;
	private Turno t2;
	private Turno t3;

    // Construtores
    public Dia() {
    	Instituicao i = Instituicao.getInstance();
    	i.addListaDias(this);
    }
    
    public String texto() {
		if (nome==null) {
			 return "null";
		}
    	return nome;
	}
    
    public Dia(String nome, int id) {
		super();
		this.nome = nome;
		this.id = id;
		Instituicao i = Instituicao.getInstance();
		i.addListaDias(this);
	}
    
    public Dia(String nome, int id, Turno t1, Turno t2, Turno t3) {
		super();
		this.nome = nome;
		this.id = id;
		this.t1 = t1;
		this.t2 = t2;
		this.t3 = t3;
		Instituicao i = Instituicao.getInstance();
		i.addListaDias(this);
	}
    
    public Dia(String nome) {
        this.nome = nome;
    }
    
    public Dia(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // Métodos
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
	}

	public Turno getT2() {
		return t2;
	}

	public void setT2(Turno t2) {
		this.t2 = t2;
	}

	public Turno getT3() {
		return t3;
	}

	public void setT3(Turno t3) {
		this.t3 = t3;
	}
    
}
