/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

/**
 *
 * @author silvio
 */
public class Turno {
    
    // Atributos
    private int id;
    private String nome;

    // Construtores
    public Turno() {
		super();
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
    
    public Turno(String nome) {
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
    
    
    
}
