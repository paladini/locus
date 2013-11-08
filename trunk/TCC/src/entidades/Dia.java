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
 * Classe que define a entidade Dia.
 * @author fernando_paladini
 */
@Entity
public class Dia {
    
    // Variáveis
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_CENTRO")
	@Column(name="idDia")
    private int id;
	
	// TODO: Fazer relacionamento ManyToOne
    private String nome;

    // Construtores
    public Dia() {
        
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
    
    
}
