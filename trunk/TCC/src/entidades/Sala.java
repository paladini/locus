/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe que define o objeto Sala.
 * @author fernando_paladini
 */
@Entity
public class Sala {
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_CENTRO")
	@Column(name="idSala")
    private int id;
    private String nome;
    
    // Construtores
    public Sala() {
    
    }
    
    public Sala(String nome){
        this.nome = nome;
    }
    
    public Sala(int id, String nome) {
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

    // ToString - NÃO ALTERAR UMA VIRGULA!
    @Override
    public String toString() {
        return nome;
    }
    
    
    
}
