/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 * Classe que define o objeto Professor.
 * @author fernando_paladini
 */
@Entity
public class Professor {
    
	// TODO: Verificar código do Hibernate.
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SEQ_CENTRO")
	@Column(name="idProfessor")
    private int id;
	
	
	
    private String nome;
    
    
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="Disciplina_has_Professor",
    		joinColumns = @JoinColumn(name="Professor_idProfessor"),
    		inverseJoinColumns = @JoinColumn(name="Disciplina_idDisciplina")
    		)
    private ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();

    
    
    
    // Construtores
    public Professor() {
    }
    
    public Professor(String nome) {
        this.nome = nome;
    }

    public Professor(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    // Métodos
    
    /**
     * Método para adicionar disciplina ao professor
     * @param disciplina 
     */
    public void adicionarDisciplina(Disciplina disciplina){
        getListaDisciplinas().add(disciplina);
    }
    
    /**
     * Método para remover disciplina do professor
     * @param disciplina 
     */
    public void removerDisciplina(Disciplina disciplina){
        getListaDisciplinas().remove(disciplina);
    }
    
    // Métodos
    public ArrayList<Disciplina> getListaDisciplinas() {
        return listaDisciplinas;
    }

    public void setListaDisciplinas(ArrayList<Disciplina> listaDisciplinas) {
        this.listaDisciplinas = listaDisciplinas;
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

    // ToString - NÃO ALTERAR UMA VIRGULA!
//    @Override
//    public String toString() {
//        return nome;
//    }
}
